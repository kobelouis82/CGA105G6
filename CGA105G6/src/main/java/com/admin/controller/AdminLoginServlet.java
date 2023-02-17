package com.admin.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mailService.model.*;

import com.admin.model.*;

@WebServlet("/adminLogin.do")
public class AdminLoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			String adminAccount = req.getParameter("adminAccount");
			String adminPassword = req.getParameter("adminPassword");
			String adminMail = req.getParameter("adminMail");
			HttpSession session = req.getSession();
			
//			========================================錯誤訊息=====================================================
			if ("textForLogin".equals(action)) {
				
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				if (((adminPassword == null || (adminPassword.trim()).length() == 0))
						&& ((adminAccount == null) || (adminAccount.trim()).length() == 0)) {
					errorMsgs.add("請輸入帳號和密碼!");
				}
		
				if ((adminAccount == null || (adminAccount.trim()).length() == 0) && (adminPassword != null)) {
					errorMsgs.add("請輸入帳號");
				}

			
				if ((adminPassword == null || (adminPassword.trim()).length() == 0) && (adminAccount != null)) {
					errorMsgs.add("請輸入密碼");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/admin/adminLogin.jsp");
					failureView.forward(req, res);
					return;
				}

//			===========================================依帳號密碼驗證==================================================
				AdminService adminSvc = new AdminService();
				AdminVO adminVO = adminSvc.findByAcAndPwd(adminAccount, adminPassword);
				if (adminVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/admin/adminLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				
//	 			=======================================登入後以session存取所有管理員的資訊=================================================
				session.setAttribute("adminVO", adminVO); // 設定VO物件
				session.setAttribute("adminNo", adminVO.getAdminNo());
//				=========================================資料驗證跳轉(Send the Success view) ================
				String url1 = "/back-end/main.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url1);
				successView.forward(req, res);
			}

//			========================================忘記密碼寄送錯誤訊息====================================================
			if ("passwordForgotten".equals(action)) {
				List<String> sendMsgs = new LinkedList<String>();
				req.setAttribute("sendMsgs", sendMsgs);

				if (((adminAccount == null || (adminAccount.trim()).length() == 0))
						&& ((adminMail == null) || (adminMail.trim()).length() == 0)) {
					sendMsgs.add("請輸入帳號和電子郵件!");
				}
				if (!sendMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adminForgetPassword/getPassword.jsp");
					failureView.forward(req, res);
					return;
				}

				if ((adminAccount == null || (adminAccount.trim()).length() == 0) && (adminMail != null)) {
					sendMsgs.add("請輸入帳號");
				}
//				 Send the use back to the form, if there were errors
				if (!sendMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adminForgetPassword/getPassword.jsp");
					failureView.forward(req, res);
					return;
				}

				if ((adminMail == null || (adminMail.trim()).length() == 0) && (adminAccount != null)) {
					sendMsgs.add("請輸入Email");
				}
				if (!sendMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adminForgetPassword/getPassword.jsp");
					failureView.forward(req, res);
					return;
				}
//			=============================================================================================
				AdminService adminSvc = new AdminService();
				AdminVO adminVO = adminSvc.findByAcAndEmail(adminAccount,adminMail);//用帳號和密碼找對應VO
				if (adminVO == null) {
					sendMsgs.add("查無此帳號和電子郵件!");
					System.out.println("找不到");
				}
				if (!sendMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adminForgetPassword/getPassword.jsp");
					failureView.forward(req, res);
					return;
				}
//				=========================================驗證碼產生==============================================
				GetVerified randomCode = new GetVerified();
				String code = randomCode.getRandomPassword();
//				=========================================寄含有驗證碼的信===================================================
				String password=adminVO.getPassword();
				SendMail mailService = new SendMail();
				String to = adminMail;
				String subject = "忘記密碼驗證信";
				String ch_name = adminVO.getAdminName();
				String alert = "請盡速輸入驗證瑪!";
				String messageText = "Hello! " + ch_name + " 驗整瑪為: " + code + "\n" + alert;
				mailService.sendMail(to, subject, messageText);
				System.out.println(code);
				session.setAttribute("password", password);
				req.setAttribute("code", code);
//			=========================================資料驗證跳轉(Send the Success view) ================
				session.setAttribute("adminVO", adminVO);
				String url = "/back-end/adminForgetPassword/confirmationResend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			
		}
//			=======================================================================
			if ("sendConfirmation".equals(action)) {
				List<String> sendMsgs = new LinkedList<String>();
				req.setAttribute("sendMsgs", sendMsgs);
				String confirmCode = req.getParameter("confirmCode");
				if ((confirmCode == null || (confirmCode.trim()).length() == 0)) {
					sendMsgs.add("請輸入驗證碼!");
				}
				
				String code = req.getParameter("code");
//				
				if (!(code.equals(confirmCode))) {
					sendMsgs.add("輸入的驗證碼錯誤!");	
				}else {
					System.out.println("驗證成功");
				}
				
				if (!sendMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adminForgetPassword/confirmationResend.jsp");
					failureView.forward(req, res);
					return;
				}
//			=========================================資料驗證跳轉(Send the Success view) ================
				String url = "/back-end/adminForgetPassword/confirmedSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

//			=========================================登出 =================================================
			if ("LogOut".equals(action)) {
				session.invalidate();
				String url = "/back-end/admin/adminLogin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

