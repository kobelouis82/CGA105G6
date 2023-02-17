package com.admin.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.admin.model.*;
import com.mysql.cj.Session;
@MultipartConfig
@WebServlet("/admin.do")
public class AdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String sss = req.getParameter("state");

		if ("getOne_For_Display".equals(action)) { // select_page.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String str = req.getParameter("adminNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入管理員編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer adminNo = null;
			try {
				adminNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("請輸入管理員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			AdminService adminSvc = new AdminService();
			AdminVO adminVO = adminSvc.getOneAdmin(adminNo);
			if (adminVO == null) {
				errorMsgs.add("請輸入管理員編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			req.setAttribute("adminVO", adminVO); 
			String url = "/back-end/admin/listOneAdmin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // listAllEmp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));

			AdminService adminSvc = new AdminService();
			AdminVO adminVO = adminSvc.getOneAdmin(adminNo);

			req.setAttribute("adminVO", adminVO); 
			String url = "/back-end/admin/update_Admin_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer adminNo = Integer.valueOf(req.getParameter("adminNo").trim());

			String adminName = req.getParameter("adminName").trim();
			String adminnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (adminName == null || adminName.trim().length() == 0) {
				errorMsgs.add("請輸入中文");
			} else if (!adminName.trim().matches(adminnameReg)) { 
				errorMsgs.add("請輸入2-10字的中英文數字");
			}

			Integer adminTitleNo = null;
			try {
			adminTitleNo = Integer.valueOf(req.getParameter("adminTitleNo").trim());
			} catch (IllegalArgumentException e) {
				adminTitleNo = 1;
				errorMsgs.add("請輸入職稱編號");
			}

			String phone = req.getParameter("phone").trim();
			String phoneReg = "^[09(0-9)]{10}$";
			if (phone == null || phone.trim().length() == 0) {
				errorMsgs.add("請輸入手機號碼");
			} else if (!phone.trim().matches(phoneReg)) {
				errorMsgs.add("請輸入正確手機格式");
			}
			String mail = req.getParameter("mail").trim();
			String mailReg = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";
			if (mail == null || mail.trim().length() == 0) {
				errorMsgs.add("請輸入信箱");
			} else if (!mail.trim().matches(mailReg)) { 
				errorMsgs.add("請輸入正確信箱格式");
			}
			String account = req.getParameter("account").trim();
			String accountReg = "[a-zA-Z0-9_]{6,12}";
			if (account == null || account.trim().length() == 0) {
				errorMsgs.add("請輸入帳號");
			} 
//			else if (!account.trim().matches(accountReg)) { 
//				errorMsgs.add("請輸入6-12位帳號");
//			}

			String password = req.getParameter("password").trim();
			String passwordReg = "[a-zA-Z0-9_]{6,12}";
			if (password == null || password.trim().length() == 0) {
				errorMsgs.add("請輸入密碼");
			} 
//			else if (!password.trim().matches(passwordReg)) { 
//				errorMsgs.add("請輸入6-12位密碼");
//			}
			Integer state = null;
			try {
			 state = Integer.valueOf(req.getParameter("state").trim());
			} catch (IllegalArgumentException e) {
				state = 0;
				errorMsgs.add("請輸入在職狀態");
			}

			Part part =req.getPart("upfile1");
			InputStream in =part.getInputStream();
			byte[] photo=new byte[in.available()];
				if(photo.length==0) {
//					AdminService adminSvc = new AdminService();
					photo = new AdminService().getOneAdmin(adminNo).getPhoto();
			}
			in.read(photo);
			in.close();
	

			AdminVO adminVO = new AdminVO();
			adminVO.setAdminNo(adminNo);
			adminVO.setAdminName(adminName);
			adminVO.setAdminTitleNo(adminTitleNo);
			adminVO.setPhone(phone);
			adminVO.setMail(mail);
			adminVO.setAccount(account);
			adminVO.setPassword(password);
			adminVO.setPhoto(photo);
			adminVO.setState(state);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("adminVO", adminVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/update_Admin_input.jsp");
				failureView.forward(req, res);
				return; 
			}

			AdminService adminSvc = new AdminService();
			adminVO = adminSvc.updateAdmin(adminNo, adminName, adminTitleNo, phone, mail, account, password, photo,
					state);

			req.setAttribute("adminVO", adminVO); 
			AdminVO adminVO2 = (AdminVO)session.getAttribute("adminVO"); 
//			AdminService adminSvc2 = new AdminService();
			AdminVO adminVO3 = new AdminService().getOneAdmin(adminVO2.getAdminNo());
//			session.getAttribute("adminVO3");
			session.setAttribute("adminVO", adminVO3); 
			String url = "/back-end/admin/listOneAdmin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			
			String adminName = req.getParameter("adminName").trim();
			String adminnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (adminName == null || adminName.trim().length() == 0) {
				errorMsgs.add("請輸入名字");
			} else if (!adminName.trim().matches(adminnameReg)) { 
				errorMsgs.add("請輸入正確名字格式");
			}

			Integer adminTitleNo = null;
			try {
				adminTitleNo = Integer.valueOf(req.getParameter("adminTitleNo").trim());
			} catch (Exception e) {
				adminTitleNo = 0;
				errorMsgs.add("請輸入職稱編號");
			}

			String phone = req.getParameter("phone").trim();
			String phoneReg = "^[09(0-9)]{10}$";
			if (phone == null || phone.trim().length() == 0) {
				errorMsgs.add("請輸入手機");
			} else if (!phone.trim().matches(phoneReg)) { 
				errorMsgs.add("請輸入正確手機格式");
			}
			String mail = req.getParameter("mail").trim();
			String mailReg = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";
			if (mail == null || mail.trim().length() == 0) {
				errorMsgs.add("請輸入信箱");
			} else if (!mail.trim().matches(mailReg)) { 
				errorMsgs.add("請輸入正確信箱格式");
			}
			String account = req.getParameter("account");
			String accountReg = "[a-zA-Z0-9_]{6,12}";
			if (account == null || account.trim().length() == 0) {
				errorMsgs.add("請輸入帳號");
			} 
			

			String password = req.getParameter("password");
			String passwordReg = "[a-zA-Z0-9_]{6,12}";
			if (password == null || password.trim().length() == 0) {
				errorMsgs.add("請輸入密碼");
			} 
//			else if (!password.trim().matches(passwordReg)) {
//				errorMsgs.add("請輸入正確密碼格式");
//			}
			Integer state = null;
			try {
				state = Integer.valueOf(req.getParameter("state").trim());
			} catch (Exception e) {
				state = 0;
				errorMsgs.add("請輸入在職狀態");
			}

			Part part =req.getPart("upfile1");
			InputStream in =part.getInputStream();
			byte[] photo=new byte[in.available()];
			if (photo == null ) {
				errorMsgs.add("請加入照片");
			}
			if (!errorMsgs.isEmpty()) { 
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/addAdmin.jsp");
				failureView.forward(req, res);
				return;
			}
			in.read(photo);
			in.close();
			
			AdminVO adminVO = new AdminVO();
		
			adminVO.setAdminName(adminName);
			adminVO.setAdminTitleNo(adminTitleNo);
			adminVO.setPhone(phone);
			adminVO.setMail(mail);
			adminVO.setAccount(account);
			adminVO.setPassword(password);
			adminVO.setPhoto(photo);
			adminVO.setState(state);
		

			if (!errorMsgs.isEmpty()) { 
				req.setAttribute("adminVO", adminVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/addAdmin.jsp");
				failureView.forward(req, res);
				return;
			}

			AdminService adminSvc = new AdminService();
			adminVO = adminSvc.addAdmin( adminName, adminTitleNo, phone, mail, account, password, 
					photo,state);

			String url = "/back-end/admin/listAllAdmin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { //如果被關聯到，應該將刪除按鈕隱藏

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));

			AdminService adminSvc = new AdminService();
			try {
				adminSvc.deleteAdmin(adminNo);
			} catch (Exception e) {
				req.setAttribute("fail", "fa");
				String url = "/back-end/admin/listAllAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);//
			}
			

			String url = "/back-end/admin/listAllAdmin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 
			successView.forward(req, res);
		}
		if ("getPhoto".equals(action)) {  //if新增byte長度為0，要使用原圖
			OutputStream out =res.getOutputStream();
			String photoID=req.getParameter("adminNo");
			AdminService adminSvc= new AdminService();
			AdminVO adminVO= adminSvc.getOneAdmin(Integer.parseInt(photoID));
			res.setContentType("image/jpg");
			res.setContentLength(adminVO.getPhoto().length);
			
			out.write(adminVO.getPhoto());
			out.close();
		}
		if ("listAdmin_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
//				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				
				// 以下的 if 區塊只對第一次執行時有效
				if (req.getParameter("whichPage") == null){
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				AdminService adminSvc = new AdminService();
				List<AdminVO> list  = adminSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listAdmin_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/admin/listAdmin_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}
	}
}
