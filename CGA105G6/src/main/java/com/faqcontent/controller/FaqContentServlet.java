package com.faqcontent.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faqcontent.model.*;


@WebServlet("/faqcontent.do")
public class FaqContentServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("faqNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入問題編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/faqcontent/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

			Integer faqNo = null;
			try {
				faqNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("問題編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/faqcontent/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

			/***************************2.開始查詢資料*****************************************/
			FAQContentService faqContentSvc = new FAQContentService();
			FAQContentVO faqContentVO = faqContentSvc.getOneFAQContent(faqNo);
			if (faqContentVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/faqcontent/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("faqContentVO", faqContentVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/faqcontent/listOneFaqcontent.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數****************************************/
			Integer faqNo = Integer.valueOf(req.getParameter("faqNo"));

			/***************************2.開始查詢資料****************************************/
			FAQContentService faqContentSvc = new FAQContentService();
			FAQContentVO faqContentVO = faqContentSvc.getOneFAQContent(faqNo);

			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("faqContentVO", faqContentVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/faqcontent/update_faqcontent_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer faqNo = Integer.valueOf(req.getParameter("faqNo").trim());

			String faqContent = req.getParameter("faqContent");
			String faqContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_~ ?)]{2,500}$";
			if (faqContent == null || faqContent.trim().length() == 0) {
				errorMsgs.add("問題內容請勿空白");
			} else if (!faqContent.trim().matches(faqContentReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("問題內容: 只能是中、英文字母、數字和_ , 且長度必需在2到500之間");
			}
			
			String ansContent = req.getParameter("ansContent");
			String ansContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_~ ?)]{2,500}$";
			if (ansContent == null || ansContent.trim().length() == 0) {
				errorMsgs.add("回答內容請勿空白");
			} else if (!ansContent.trim().matches(ansContentReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("回答內容: 只能是中、英文字母、數字和_ , 且長度必需在2到500之間");
			}
			
			String fqKeyWord = req.getParameter("fqKeyWord");
			String fqKeyWordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_~ ?)]{2,20}$";
			if (fqKeyWord == null || fqKeyWord.trim().length() == 0) {
				errorMsgs.add("關鍵字請勿空白");
			} else if (!fqKeyWord.trim().matches(fqKeyWordReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("關鍵字: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
			}
			
//			String fqKeyWord = req.getParameter("fqKeyWord").trim();
//			if (fqKeyWord == null || fqKeyWord.trim().length() == 0) {
//				errorMsgs.add("關鍵字請填數字.");
//			}

			FAQContentVO faqContentVO = new FAQContentVO();
//			faqContentVO.setFaqNo(faqNo);
			faqContentVO.setFaqContent(faqContent);
			faqContentVO.setAnsContent(ansContent);
			faqContentVO.setFqKeyWord(fqKeyWord);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("faqContentVO", faqContentVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/faqcontent/update_faqcontent_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}

			/***************************2.開始修改資料*****************************************/
			FAQContentService faqContentSvc = new FAQContentService();
			faqContentVO = faqContentSvc.updateFAQContent(faqNo, faqContent, ansContent, fqKeyWord);

			/***************************3.修改完成,準備轉交(Send the Success view)*************/				
			req.setAttribute("faqContentVO", faqContentVO); // 資料庫取出的list物件,存入request
			String url = "/back-end/faqcontent/listOneFaqcontent.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//			Integer faqNo = Integer.valueOf(req.getParameter("faqNo").trim());

			String faqContent = req.getParameter("faqContent");
			String faqContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_~ ?)]{2,500}$";
			if (faqContent == null || faqContent.trim().length() == 0) {
				errorMsgs.add("問題內容請勿空白");
			} else if (!faqContent.trim().matches(faqContentReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("問題內容: 只能是中、英文字母、數字和_ , 且長度必需在2到500之間");
			}
			
			String ansContent = req.getParameter("ansContent");
			String ansContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_~ ?)]{2,500}$";
			if (ansContent == null || faqContent.trim().length() == 0) {
				errorMsgs.add("回答內容請勿空白");
			} else if (!ansContent.trim().matches(ansContentReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("回答內容: 只能是中、英文字母、數字和_ , 且長度必需在2到500之間");
			}
			
			String fqKeyWord = req.getParameter("fqKeyWord");
			String fqKeyWordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_~ ?)]{2,20}$";
			if (fqKeyWord == null || fqKeyWord.trim().length() == 0) {
				errorMsgs.add("關鍵字請勿空白");
			} else if (!fqKeyWord.trim().matches(fqKeyWordReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("關鍵字: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
			}
			
//			String fqKeyWord = req.getParameter("fqKeyWord").trim();
//			if (fqKeyWord == null || fqKeyWord.trim().length() == 0) {
//				errorMsgs.add("關鍵字請填數字.");
//			}

			FAQContentVO faqContentVO = new FAQContentVO();
//			faqContentVO.setFaqNo(faqNo);
			faqContentVO.setFaqContent(faqContent);
			faqContentVO.setAnsContent(ansContent);
			faqContentVO.setFqKeyWord(fqKeyWord);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("faqContentVO", faqContentVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/faqcontent/addFaqcontent.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			FAQContentService faqContentSvc = new FAQContentService();
			faqContentVO = faqContentSvc.addFAQContent(faqContent, ansContent, fqKeyWord);

			/*************************** 3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/back-end/faqcontent/listAllFaqcontent.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數 ***************************************/
			Integer faqNo = Integer.valueOf(req.getParameter("faqNo").trim());
//			/*************************** 2.開始刪除資料***************************************/
			FAQContentService faqContentSvc = new FAQContentService();
			faqContentSvc.deleteFAQContent(faqNo);

			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			String url = "/back-end/faqcontent/listAllFaqcontent.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		
		if ("listFAQContents_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
				
				/***************************2.開始複合查詢***************************************/
				FAQContentService faqContentSvc = new FAQContentService();
				List<FAQContentVO> list  = faqContentSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listFAQContents_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/faqcontent/listFAQContents_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}
	}
}
