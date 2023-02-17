//package com.sermeg.controller;
//
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.sermeg.model.SerMegService;
//import com.sermeg.model.SerMegVO;
//
//public class SerMegServlet extends HttpServlet{
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}
//	
//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//
//		if ("getOne_For_UpdateSerMeg".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			String str = req.getParameter("messageNo");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("請輸入訊息編號");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sermeg/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			Integer messagNo = null;
//			try {
//				messagNo = Integer.valueOf(str);
//			} catch (Exception e) {
//				errorMsgs.add("訊息編號格式不正確");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sermeg/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 2.開始查詢資料 *****************************************/
//			SerMegService serMegSvc = new SerMegService();
//			SerMegVO serMegVO = serMegSvc.getOneSerMeg(messageNo);
//			if (serMegVO == null) {
//				errorMsgs.add("查無資料");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sermeg/select_page.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("serMegVO", serMegVO); // 資料庫取出的empVO物件,存入req
//			String url = "/back-end/sermeg/listOneSermeg.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
//		}
//
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 ****************************************/
//			Integer messageNo = Integer.valueOf(req.getParameter("messageNo"));
//
//			/*************************** 2.開始查詢資料 ****************************************/
//			SerMegService serMegSvc = new SerMegService();
//			SerMegVO serMegVO = serMegSvc.getOneSerMeg(messageNo);
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			req.setAttribute("serMegVO", serMegVO); // 資料庫取出的empVO物件,存入req
//			String url = "/back-end/sermeg/update_sermeg_input.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//			successView.forward(req, res);
//		}
//
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer messageNo = Integer.valueOf(req.getParameter("messageNo").trim());
//
//			String messageContent = req.getParameter("messageContent");
//			String messageContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_?)]{3,500}$";
//			if (messageContent == null || messageContent.trim().length() == 0) {
//				errorMsgs.add("訊息內容: 請勿空白");
//			} else if (!messageContent.trim().matches(messageContentReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("訊息內容: 只能是中、英文字母、數字和_? , 且長度必需在3到500之間");
//			}
//
//			Integer adminNo = null;
//			try {
//				adminNo = Integer.valueOf(req.getParameter("adminNo").trim());
//			} catch (NumberFormatException e) {
//				adminNo = 0;
//				errorMsgs.add("管理員編號請填數字.");
//			}
//			
//			Integer memNo = null;
//			try {
//				memNo = Integer.valueOf(req.getParameter("memNo").trim());
//			} catch (NumberFormatException e) {
//				memNo = 0;
//				errorMsgs.add("會員編號請填數字.");
//			}
//			
//			
//
//			SerMegVO serMegVO = new SerMegVO();
//			serMegVO.setAdminNo(adminNo);
//			serMegVO.setMemNo(memNo);
//			serMegVO.setMessageContent(messageContent);
//			
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("serMegVO", serMegVO); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sermeg/update_sermeg_input.jsp");
//				failureView.forward(req, res);
//				return; // 程式中斷
//			}
//
//			/*************************** 2.開始修改資料 *****************************************/
//			SerMegService serMegSvc = new SerMegService();
//			serMegVO = serMegSvc.updateSerMeg(messageNo, adminNo, memNo, messageContent);
//
//			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("serMegVO", serMegVO); // 資料庫update成功後,正確的的empVO物件,存入req
//			String url = "/back-end/sermeg/listOneFaqcontent.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//			successView.forward(req, res);
//		}
//
//		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
////			(以下未完成)
////			Integer serMeg = Integer.valueOf(req.getParameter("serMeg").trim());
//
//			String messageContent = req.getParameter("messageContent");
//			String messageContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_?)]{1,500}$";
//			if (messageContent == null || messageContent.trim().length() == 0) {
//				errorMsgs.add("員工姓名: 請勿空白");
//			} else if (!messageContent.trim().matches(messageContentReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}
//
//			String fqKeyWord = req.getParameter("fqKeyWord").trim();
//			if (fqKeyWord == null || fqKeyWord.trim().length() == 0) {
//				errorMsgs.add("職位請勿空白");
//			}
//
//			SerMegVO serMegVO = new SerMegVO();
////			serMegVO.setFaqNo(serMeg);
//			serMegVO.setFaqContent(messageContent);
//			serMegVO.setFqKeyWord(fqKeyWord);
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("serMegVO", serMegVO); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sermeg/addFaqcontent.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/*************************** 2.開始新增資料 ***************************************/
//			SerMegService serMegSvc = new SerMegService();
//			serMegVO = serMegSvc.addFAQContent(messageContent, fqKeyWord);
//
//			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			String url = "/back-end/sermeg/listAllFaqcontent.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
//		}
//
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 ***************************************/
//			Integer serMeg = Integer.valueOf(req.getParameter("serMeg").trim());
//			/*************************** 2.開始刪除資料 ***************************************/
//			SerMegService serMegSvc = new SerMegService();
//			serMegSvc.deleteFAQContent(serMeg);
//
//			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//			String url = "/back-end/sermeg/listAllFaqcontent.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//			successView.forward(req, res);
//		}
//	}
//}
