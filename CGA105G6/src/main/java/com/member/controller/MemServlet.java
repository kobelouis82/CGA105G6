package com.member.controller;

import java.io.*;

import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.admin.model.AdminService;
import com.admin.model.AdminVO;
import com.member.model.*;
import org.json.JSONObject;

@MultipartConfig
@WebServlet("/memServlet1.do")
public class MemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		if ("getOne_For_Display".equals(action)) { // 搜尋

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>(); // Store this set in the request scope,
																					// in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String str = req.getParameter("memNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("memNo", "請輸入會員編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("back-end/member/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer memNo = null;
			try {
				memNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("memNo", "會員編號格式錯誤");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("back-end/member/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMem(memNo);
			if (memVO == null) {
				errorMsgs.put("memNo", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("back-end/member/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			req.setAttribute("memVO", memVO);
			String url = "/back-end/member/listOneMem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>(); // Store this set in the request scope,
																					// in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			Integer memNo = Integer.valueOf(req.getParameter("memNo"));

			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMem(memNo);

			req.setAttribute("memVO", memVO);
			String url = "/back-end/member/update_member_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());

			String memName = req.getParameter("memName");
			String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memName == null || memName.trim().length() == 0) {
				errorMsgs.put("memName", "請輸入姓名");
			} else if (!memName.trim().matches(memNameReg)) {
				errorMsgs.put("memName", "姓名欄位: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
			}

			String memTel = req.getParameter("memTel").trim();
			if (memTel == null || memTel.trim().length() == 0) {
				errorMsgs.put("memTel", "請輸入電話");
			}

			String memCity = req.getParameter("memCity").trim();
			String memCityReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memCity == null || memCity.trim().length() == 0) {
				errorMsgs.put("memCity", "請輸入所在地");
			} else if (!memCity.trim().matches(memCityReg)) {
				errorMsgs.put("memCity", "請輸入正確格式");
			}
			java.sql.Date memBirth = null;
			try {
				memBirth = java.sql.Date.valueOf(req.getParameter("memBirth"));
			} catch (IllegalArgumentException e) {
				errorMsgs.put("memBirth", "請輸入日期");
			}
			String memMail = req.getParameter("memMail").trim();
			String memMailReg = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
			if (memMail == null || memMail.trim().length() == 0) {
				errorMsgs.put("memMail", "email欄位請勿空白");
			} else if (!memMail.trim().matches(memMailReg)) {
				errorMsgs.put("memMail", "請填入正確的email格式");
			}
			String memAccount = req.getParameter("memAccount");
			String memAccountReg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$";
			if (memAccount == null || memAccount.trim().length() == 0) {
				errorMsgs.put("memAccount", "帳號欄位請勿空白");
			} 
//			else if (!memAccount.trim().matches(memAccountReg)) {
//				errorMsgs.put("memAccount", "帳號：至少一個字母和一個數字 , 且長度必需在6到20之間");
//			}

			String memPassword = req.getParameter("memPassword").trim();
			String memPasswordReg = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$";
			if (memPassword == null || memPassword.trim().length() == 0) {
				errorMsgs.put("memPassword", "密碼欄位請勿空白");
			}
//			else if (!memPassword.trim().matches(memPasswordReg)) {
//				errorMsgs.put("memPassword", "密碼：至少一個大寫字母、和一個數字, 且長度必需在6到20之間：");
//			}
			String memDist = req.getParameter("memPassword").trim();

			if (memDist == null || memDist.trim().length() == 0) {
				errorMsgs.put("memDist", "區域請勿空白");
			}
			String memAdd = req.getParameter("memAdd").trim();
			String memAddReg = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$";
			if (memAdd == null || memAdd.trim().length() == 0) {
				errorMsgs.put("memAdd", "地址請勿空白");
			} 
//			else if (!memAdd.trim().matches(memAddReg)) {
//				errorMsgs.put("memAdd", "地址格式錯誤");
//			}
			Integer memAccess = null;
			try {
				memAccess = Integer.valueOf(req.getParameter("memAccess"));
			} catch (NumberFormatException e) {
				memAccess = 0;
				errorMsgs.put("memAccess", "只能輸入數字");
			}
			Integer articleReport = Integer.valueOf(req.getParameter("articleReport"));
			Integer messageReport = Integer.valueOf(req.getParameter("messageReport"));

//			Part part = req.getPart("upfile1");
//			byte[] memPhoto = part.getInputStream().readAllBytes();
//			if (memPhoto.length == 0) {
//				MemService memberSvc2 = new MemService();
//				memPhoto = memberSvc2.getOneMem(memNo).getMemPhoto();
//			}
//			in.read(photo);
//			in.close();
			Part part =req.getPart("upfile1");
			InputStream in =part.getInputStream();
			byte[] memPhoto=new byte[in.available()];
				if(memPhoto.length==0) {
					memPhoto = new MemService().getOneMem(memNo).getMemPhoto();
			}
			in.read(memPhoto);
			in.close();
			
			MemVO memVO = new MemVO();
			memVO.setMemNo(memNo);
			memVO.setMemName(memName);
			memVO.setMemTel(memTel);
			memVO.setMemCity(memCity);
			memVO.setMemBirth(memBirth);
			memVO.setMemDist(memDist);
			memVO.setMemAdd(memAdd);
			memVO.setMemMail(memMail);
			memVO.setMemAccount(memAccount);
			memVO.setMemPassword(memPassword);
			memVO.setMemPhoto(memPhoto);
			memVO.setMemAccess(memAccess);
			memVO.setArticleReport(articleReport);
			memVO.setMessageReport(messageReport);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memVO", memVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member_input.jsp");
				failureView.forward(req, res);
				return;
			}
			 /*************************** 2.開始新增資料 ***************************************/
			MemService memSvc = new MemService();
			memVO = memSvc.updatemem(memNo, memName, memTel, memCity, memBirth, memDist, memAdd, memMail, memAccount,
					memPassword, memPhoto, memAccess, articleReport, messageReport);
			 /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("memVO", memVO); //
			String url = "/back-end/member/listAllMem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // �Ӧ�putMem.jsp���ШD

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String memName = req.getParameter("memName");
			String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memName == null || memName.trim().length() == 0) {
				errorMsgs.put("memName", "請輸入姓名");
			} else if (!memName.trim().matches(memNameReg)) {
				errorMsgs.put("memName", "姓名欄位: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
			}

			String memTel = req.getParameter("memTel").trim();
			if (memTel == null || memTel.trim().length() == 0) {
				errorMsgs.put("memTel", "請輸入電話");
			}

			String memCity = req.getParameter("memCity").trim();
			String memCityReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memCity == null || memCity.trim().length() == 0) {
				errorMsgs.put("memCity", "請輸入所在地");
			} else if (!memCity.trim().matches(memCityReg)) {
				errorMsgs.put("memCity", "請輸入正確格式");
			}
			java.sql.Date memBirth = null;
			try {
				memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("memBirth", "請輸入日期");
			}
			String memMail = req.getParameter("memberemail").trim();
			String memMailReg = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
			if (memMail == null || memMail.trim().length() == 0) {
				errorMsgs.put("memMail", "email欄位請勿空白");
			} else if (!memMail.trim().matches(memMailReg)) {
				errorMsgs.put("memMail", "請填入正確的email格式");
			}
			String memAccount = req.getParameter("memAccount");
			String memAccountReg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$";
			if (memAccount == null || memAccount.trim().length() == 0) {
				errorMsgs.put("memAccount", "帳號欄位請勿空白");
			} 
//			else if (!memAccount.trim().matches(memAccountReg)) {
//				errorMsgs.put("memAccount", "帳號：至少一個字母和一個數字 , 且長度必需在6到20之間");
//			}

			String memPassword = req.getParameter("memPassword").trim();
			String memPasswordReg = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$";
			if (memPassword == null || memPassword.trim().length() == 0) {
				errorMsgs.put("memPassword", "密碼欄位請勿空白");
			} 
//			else if (!memPassword.trim().matches(memPasswordReg)) {
//				errorMsgs.put("memPassword", "密碼：至少一個大寫字母、和一個數字, 且長度必需在6到20之間：");
//			}
			String memDist = req.getParameter("memPassword").trim();

			if (memDist == null || memDist.trim().length() == 0) {
				errorMsgs.put("memDist", "區域請勿空白");
			}
			String memAdd = req.getParameter("memAdd").trim();
			String memAddReg = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$";
			if (memAdd == null || memAdd.trim().length() == 0) {
				errorMsgs.put("memAdd", "地址請勿空白");
			} else if (!memAdd.trim().matches(memAddReg)) {
				errorMsgs.put("memAdd", "請輸入正確地址格式");
			}
			Integer memAccess = null;
			try {
				memAccess = Integer.valueOf(req.getParameter("memAccess"));
			} catch (NumberFormatException e) {
				memAccess = 0;
				errorMsgs.put("memAccess", "只能輸入數字");
			}
			Integer articleReport = Integer.valueOf(req.getParameter("articleReport"));
			Integer messageReport = Integer.valueOf(req.getParameter("messageReport"));

			Part part = req.getPart("upfile1");
			InputStream in = part.getInputStream();
			byte[] memPhoto = new byte[in.available()];
			if(memPhoto==null) {
				errorMsgs.put("memPhoto", "請輸入照片");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("front-end/member/update_member_input.jsp");
				failureView.forward(req, res);
				return;
			}
			in.read(memPhoto);
			in.close();

			MemVO memVO = new MemVO();
			memVO.setMemName(memName);
			memVO.setMemTel(memTel);
			memVO.setMemCity(memCity);
			memVO.setMemBirth(memBirth);
			memVO.setMemDist(memDist);
			memVO.setMemAdd(memAdd);
			memVO.setMemMail(memMail);
			memVO.setMemAccount(memAccount);
			memVO.setMemPassword(memPassword);
			memVO.setMemPhoto(memPhoto);
			memVO.setMemAccess(memAccess);
			memVO.setArticleReport(articleReport);
			memVO.setMessageReport(messageReport);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memVO", memVO);
				RequestDispatcher failureView = req.getRequestDispatcher("front-end/member/update_member_input.jsp");
				failureView.forward(req, res);
				return;
			}

			MemService memSvc = new MemService();
			memVO = memSvc.addMem(memName, memTel, memCity, memBirth, memDist, memAdd, memMail, memAccount, memPassword,
					memPhoto, memAccess, articleReport, messageReport);
			String url = "/back-end/member/listAllMem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("delete".equals(action)) { // �Ӧ�listAllMem.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			Integer memNo = Integer.valueOf(req.getParameter("memNo"));

			MemService memSvc = new MemService();
			memSvc.deleteMem(memNo);

			String url = "/back-end/member/listAllMem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		 if ("MemSearchPro".equals(action)) {
			 List<String> errorMsgs = new LinkedList<String>();
	            req.setAttribute("errorMsgs", errorMsgs);
	            String mem_No = req.getParameter("mem_No").trim();
	            String mem_Account = req.getParameter("mem_Account").trim();
	            String mem_Name = req.getParameter("mem_Name").trim();
	            String mem_Access = req.getParameter("mem_Access").trim();
	            if (mem_Account.length()==0&&mem_No.length()==0&&mem_Name.length()==0&&mem_Access.length()==0) {
	                errorMsgs.add( "請輸入搜尋內容");
	            }
	            
	            if (!errorMsgs.isEmpty()) {
//	                req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
	                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_page.jsp");
	                failureView.forward(req, res);
	                return;
	            }
	            Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
	            if (req.getParameter("whichPage") == null) {
	                Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
	                session.setAttribute("map", map1);
	                map = map1;
	            }
	            
	            MemService memSvc = new MemService();
	            List<MemVO> list = memSvc.getAllMem(map);
//	            for (MemVO a : list) {
//	                System.out.println(a);
//	            }

	            req.setAttribute("MemSerchPro", list);
	            RequestDispatcher successView = req.getRequestDispatcher("/back-end/member/listAllMemPro.jsp");
	            successView.forward(req, res);

	        }


	        if ("register".equals(action)) { // 來自login.jsp的請求

	            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
	            req.setAttribute("errorMsgs", errorMsgs);
	            MemService memSvc = new MemService();
	            MemVO memVO = new MemVO();

	            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

	            String memAccount = req.getParameter("memAccount").trim();
	            MemVO memAccountVO = memSvc.checkAccount(memAccount);
	            if (memAccountVO != null) {
	                errorMsgs.put("memAccount", "此帳號已被使用");
	            }
	            
	            String memName = req.getParameter("memName");
				String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.put("memName", "請輸入姓名");
				} else if (!memName.trim().matches(memNameReg)) {
					errorMsgs.put("memName", "姓名欄位: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}

				String memTel = req.getParameter("memTel").trim();
				if (memTel == null || memTel.trim().length() == 0) {
					errorMsgs.put("memTel", "請輸入電話");
				}

				String memCity = req.getParameter("memCity").trim();
//				String memCityReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memCity == null || memCity.trim().length() == 0) {
					errorMsgs.put("memCity", "請輸入所在地");
				} 
				java.sql.Date memBirth = null;
				try {
					memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("memBirth", "請輸入日期");
				}
				String memMail = req.getParameter("memMail").trim();
				String memMailReg = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
				if (memMail == null || memMail.trim().length() == 0) {
					errorMsgs.put("memMail", "email欄位請勿空白");
				} else if (!memMail.trim().matches(memMailReg)) {
					errorMsgs.put("memMail", "請填入正確的email格式");
				}
//				String memAccount = req.getParameter("memAccount");
				String memAccountReg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$";
				if (memAccount == null || memAccount.trim().length() == 0) {
					errorMsgs.put("memAccount", "帳號欄位請勿空白");
				} 
//				else if (!memAccount.trim().matches(memAccountReg)) {
//					errorMsgs.put("memAccount", "帳號：至少一個字母和一個數字 , 且長度必需在6到20之間");
//				}

				String memPassword = req.getParameter("memPassword").trim();
				String memPasswordReg = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$";
				if (memPassword == null || memPassword.trim().length() == 0) {
					errorMsgs.put("memPassword", "密碼欄位請勿空白");
				}
				
				String memDist = req.getParameter("memDist").trim();
				if (memDist == null || memDist.trim().length() == 0) {
					errorMsgs.put("memDist", "區域請勿空白");
				}
				String memAdd = req.getParameter("memAdd").trim();
				if (memAdd == null || memAdd.trim().length() == 0) {
					errorMsgs.put("memAdd", "地址請勿空白");
				}
				Integer memAccess = 0;

				Integer articleReport = Integer.valueOf(req.getParameter("articleReport"));
				Integer messageReport = Integer.valueOf(req.getParameter("messageReport"));

				Part part = req.getPart("upfile1");
				InputStream in = part.getInputStream();
				byte[] memPhoto = new byte[in.available()];
				if (memPhoto.length==0) {
					errorMsgs.put("memPhoto", "請上傳照片");
				}
				in.read(memPhoto);
				in.close();

				memVO.setMemName(memName);
				memVO.setMemTel(memTel);
				memVO.setMemCity(memCity);
				memVO.setMemBirth(memBirth);
				memVO.setMemDist(memDist);
				memVO.setMemAdd(memAdd);
				memVO.setMemMail(memMail);
				memVO.setMemAccount(memAccount);
				memVO.setMemPassword(memPassword);
				memVO.setMemPhoto(memPhoto);
				memVO.setMemAccess(memAccess);
				memVO.setArticleReport(articleReport);
				memVO.setMessageReport(messageReport);


	            if (!errorMsgs.isEmpty()) {
	                req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
	                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memLogin/memRegister.jsp");
	                failureView.forward(req, res);
	                
	                return;
	            }

	            /*************************** 2. 發送驗證碼 ***************************************/


	            String to = req.getParameter("memMail");

	            String subject = "Fun電玩 會員註冊信";

	            String reg_name = req.getParameter("memName");
	            /*************************** 2. 驗證碼產生 ***************************************/
	            String code = memSvc.getAuthCode();
	            String messageText = "Hello ! " + reg_name + "  先生/小姐\n" + "歡迎您加入 Fun電玩" + "\n" + "您註冊帳號的驗證碼為: " + code + "\n" + "請在5分鐘內完成驗證並重新登入";

	            memSvc.sendMail(to, subject, messageText); //使用時記得開啟
	            System.out.println(code);
				req.setAttribute("code", code);
	            /*************************** 4.開始新增資料 ***************************************/
	            memVO = memSvc.addMem(memName, memTel, memCity, memBirth, memDist, memAdd, memMail, memAccount, memPassword,
						memPhoto, memAccess, articleReport, messageReport);
	            /*************************** 5.利用註冊的帳號密碼方法獲取資料的的memID存到session ***************************************/

	            Integer memNo = memSvc.findByAcAndPwd(memAccount, memPassword).getMemNo();
	           
	            session.setAttribute("memNo", memNo);
	            session.setAttribute("memMail", memMail);
	            session.setAttribute("memAccount", memAccount);
	            session.setAttribute("memPassword", memPassword);
	            

	            /*************************** 5.準備轉交驗證資料(Send the Success view) ***********/
	            session.setAttribute("memVO", memVO);
	            String url = "/front-end/memLogin/register_success.jsp";
	            RequestDispatcher successViewchk = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
	            successViewchk.forward(req, res);

	        }

	        if ("accountchk".equals(action)) {

//	            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//	            req.setAttribute("errorMsgs", errorMsgs);

	            String memAccount = req.getParameter("memAccount");
	            MemService memSvc = new MemService();
	            MemVO memVO = memSvc.checkAccount(memAccount);
	            PrintWriter out = res.getWriter();

	            if (memVO != null) {
	                String chkAc = memSvc.checkAccount(memAccount).getMemAccount();
	                JSONObject jsonAc = new JSONObject();

	                jsonAc.put("memAccount", chkAc);
	                out.println(jsonAc);


	            } else {
	                JSONObject jsonAcf = new JSONObject();
	                String chkAcf = "null";
	                jsonAcf.put("memAccount", chkAcf);
	                out.println(jsonAcf);
	            }

//				
	        }


//			輸入驗證碼進入比對
	        if ("resend".equals(action)) {
	            MemService memSvc = new MemService();

	            String to = req.getParameter("memMail");
	            String subject = "Fun電玩 會員註冊信";

	            String reg_name = req.getParameter("memName");
	            String code = memSvc.getAuthCode();
	            String messageText = "Hello ! " + reg_name + "  先生/小姐\n" + "歡迎您加入Fun電玩" + "\n" + "您註冊帳號的驗證碼為: " + code + "\n" + "請在5分鐘內完成驗證並重新登入";
	            System.out.println(code);
	            memSvc.sendMail(to, subject, messageText); //測試時關閉，不然一直寄信
//	            Integer memNo = Integer.valueOf(req.getParameter("memNo"));
//	            session.setAttribute("memNo", memNo);
	            req.setAttribute("code", code);
	            req.setAttribute("errorMsgs", "已重新發送驗證碼,請至信箱確認");
	            req.getRequestDispatcher("/front-end/memLogin/register_success.jsp").forward(req, res);

	            /*************************** 5.利用註冊的帳號密碼方法獲取資料的的memID存到session ***************************************/

	           
//				      呼叫方法存放驗證碼
	        }
	        if ("regconfirm".equals(action)) {       
	            //============================
	            List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				String regpasschk = req.getParameter("regpasschk");
				if ((regpasschk == null || (regpasschk.trim()).length() == 0)) {
					errorMsgs.add("請輸入驗證碼!");
				}
				
				String code = (String) req.getParameter("code");
				System.out.println("regconfirm的密碼"+code);
				if (!(code.equals(regpasschk))) {
					errorMsgs.add("輸入的驗證碼錯誤!");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/memLogin/register_success.jsp");
					failureView.forward(req, res);
					return;
				}
	            //===================================
				Integer A=(Integer)req.getSession().getAttribute("memNo");
				System.out.println("regconfirm的memNo"+A);
				MemJDBCDAO dao = new MemJDBCDAO();
	    		MemService memSV=new MemService();
	    		MemVO memVO=memSV.getOneMem(A);
	    		memVO.setMemAccess(1);//更改為開通
	    		dao.update(memVO);
	    		
				session.invalidate();
                req.setAttribute("errorMsgs", "註冊驗證成功 ! 請重新登入");
				String url = "/front-end/memLogin/login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			
	        }


	        if ("textForLogin".equals(action)) { 
	    		List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				String memAccount = req.getParameter("memAccount").trim();
				String memPassword = req.getParameter("memPassword").trim();
				if (memAccount == null || (memAccount.trim()).length() == 0) {
					errorMsgs.add("請輸入完整帳號和密碼！");
				}
//				 Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memLogin/login.jsp");
					failureView.forward(req, res);
					return;
				}

				if (memPassword == null || (memPassword.trim()).length() == 0) {
					errorMsgs.add("請輸入完整帳號和密碼！");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memLogin/login.jsp");
					failureView.forward(req, res);
					return;
				}

//			=============================================================================================
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.findByAcAndPwd(memAccount, memPassword);
				if (memVO == null) {
					errorMsgs.add("查無資料，請重新輸入！");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memLogin/login.jsp");
					failureView.forward(req, res);
					return;
				}
				
//			=========================================資料驗證跳轉(Send the Success view) ================

				session.setAttribute("memVO", memVO);
				session.setAttribute("memAccount", memAccount);
				session.setAttribute("memMail", memVO.getMemMail());
				session.setAttribute("memAccess", memVO.getMemAccess()); 
				session.setAttribute("memNo", memVO.getMemNo()); 
				if (memVO.getMemAccess() == 0) {
					MemService memSvc1 = new MemService();

		            String to = memVO.getMemMail();
		            String subject = "Fun電玩 會員註冊信";

		            String reg_name = memVO.getMemName();
		            String code = memSvc1.getAuthCode();
		            String messageText = "Hello ! " + reg_name + "  先生/小姐\n" + "歡迎您加入Fun電玩" + "\n" + "您註冊帳號的驗證碼為: " + code + "\n" + "請在5分鐘內完成驗證並重新登入";
		            System.out.println(code);
		            req.setAttribute("code", code);
		            memSvc.sendMail(to, subject, messageText); //測試時關閉，不然一直寄信
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memLogin/notVerify.jsp");
					failureView.forward(req, res);
					return;
				}
//		       
				String url = "/front-end/product/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
//	           
	        }

	        

	        if ("forgotpw".equals(action)) {

	        	  List<String> errorMsgs = new LinkedList<String>();
	            req.setAttribute("errorMsgs", errorMsgs);

	            String memAccount = req.getParameter("memAccount");
	            String memMail = req.getParameter("memMail");
	            String Verifycode = null;

	            MemService memSvc = new MemService();
	            try {
	                Verifycode = memSvc.findPassword(memAccount, memMail).getMemPassword();
	                System.out.println("忘記密碼，重寄密碼："+Verifycode);

	            } catch (Exception e) {
	            	errorMsgs.add("請輸入正確帳號和信箱！");
	                req.getRequestDispatcher("/front-end/memLogin/forgot.jsp").forward(req, res);

	            }

	            String subject = "Fun電玩 會員密碼函";

	            String mem_name = req.getParameter("memAccount");
	            String messageText = "親愛的Fun電玩會員" + mem_name + "你好 !\n" + "您的會員登入密碼為: " + Verifycode + "\n" + "請妥善保管並重新登入";

	            memSvc.sendMail(memMail, subject, messageText);

	            req.setAttribute("errorMsgs", "已發送至信箱,請至信箱確認");
	            RequestDispatcher failureViewpw = req.getRequestDispatcher("/front-end/memLogin/login.jsp");
	            failureViewpw.forward(req, res);

	        }
	        
	        if ("logout".equals(action)) {
				session.invalidate();
				String url = "/front-end/memLogin/login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
	    	if ("getPhoto".equals(action)) {  //if新增byte長度為0，要使用原圖
				OutputStream out =res.getOutputStream();
				String photoID=req.getParameter("memNo");
				MemService memSvc= new MemService();
				MemVO memVO= memSvc.getOneMem(Integer.parseInt(photoID));
				res.setContentType("image/jpg");
				res.setContentLength(memVO.getMemPhoto().length);
				out.write(memVO.getMemPhoto());
				out.close();
	}
}
}