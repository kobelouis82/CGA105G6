package com.requisitions_detail.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;import org.apache.catalina.valves.AbstractAccessLogValve;

import com.requisitions_detail.model.*;
@WebServlet("/requisitions_detail.do")
public class Requisitions_DetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if ("getOne_For_Display".equals(action)) { // 嚙踝蕭謕蕭�嚙踐�select_page.jsp嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭嚙�

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭蹇蕭謕��此� - 嚙踝蕭謕蕭豲J嚙踝蕭謕��嚙踐�蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕��**********************/
				String str = req.getParameter("reqDetailNo");  //嚙踝蕭謕蕭��蕭��req.getParameter嚙踝蕭謕蕭豲o"reqDetailNo"嚙踝蕭謕�蕭���嚙踐�蕭豲蕭謕蕭豲嚙踝蕭謕蕭豯歹蕭謕�蕭嚙踐��tr嚙踝蕭謕蕭豲
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入採購單明細編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/requisitions_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙踝蕭謕滲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕��
				}
				
				Integer reqDetailNo = null;
				try {
					reqDetailNo = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("請輸入採購單明細編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/requisitions_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙踝蕭謕滲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕��
				}
				
				/***************************2.嚙踝蕭謕�蕭嚙踐�蕭蹎蕭謕蕭��蕭謕蕭蹎∴蕭蹇嚙踝蕭謕*****************************************/
				Requisitions_DetailService requisitions_detailSvc = new Requisitions_DetailService();
				Requisitions_DetailVO requisitions_detailVO = requisitions_detailSvc.getOneRequisitions_Detail(reqDetailNo);
				if (requisitions_detailVO == null) {
					errorMsgs.add("找不到該筆採購單明細");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/requisitions_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙踝蕭謕滲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕��
				}
				
				/***************************3.嚙踝蕭謕蕭��蕭謕蕭蹎�蕭�嚙踝蕭謕蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success view)*************/
				req.setAttribute("Requisitions_DetailVO", requisitions_detailVO); // 嚙踝蕭謕蕭豲嚙踐�嚙踝蕭謕蕭豲嚙踝蕭謕嚙踝蕭謕蕭豲Requisitions_DetailVO嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕�蕭嚙踐�蕭謇q
				String url = "/back-end/requisitions_detail/listOneRequisitions_Detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭謕蕭豲嚙踝蕭謕蕭嚙踝蕭謕蕭豲嚙踝蕭謕 listOneRequisitions_Detail.jsp
				successView.forward(req, res);
				}
		
		
		if ("getOne_For_Update".equals(action)) { // 嚙踝蕭謕蕭�嚙踐�listAllRequisitions_Detail.jsp嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭嚙�

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭蹇蕭謕��此�****************************************/
				Integer reqDetailNo = Integer.valueOf(req.getParameter("reqDetailNo"));
				
				/***************************2.嚙踝蕭謕�蕭嚙踐�蕭蹎蕭謕蕭��蕭謕蕭蹎∴蕭蹇嚙踝蕭謕****************************************/
				Requisitions_DetailService requisitions_detailSvc = new Requisitions_DetailService();
				Requisitions_DetailVO requisitions_detailVO = requisitions_detailSvc.getOneRequisitions_Detail(reqDetailNo);
								
				/***************************3.嚙踝蕭謕蕭��蕭謕蕭蹎�蕭�嚙踝蕭謕蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success view)************/
				req.setAttribute("Requisitions_DetailVO", requisitions_detailVO);         // 嚙踝蕭謕蕭豲嚙踐�嚙踝蕭謕蕭豲嚙踝蕭謕嚙踝蕭謕蕭豲Requisitions_DetailVO嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕�蕭嚙踐�蕭謇q
				String url = "/back-end/requisitions_detail/update_requisitions_detail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭謕蕭豲嚙踝蕭謕蕭嚙踝蕭謕蕭豲嚙踝蕭謕 update_requisitions_detail_input.jsp
				successView.forward(req, res);
			}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
		
	
			Integer reqNo = Integer.valueOf(req.getParameter("reqNo"));
			
			Integer reqDetailNo = Integer.valueOf(req.getParameter("reqDetailNo"));
				
			Integer serialNo = Integer.valueOf(req.getParameter("serialNo"));
				
			Byte status = Byte.valueOf(req.getParameter("status"));
				
			Integer qty = Integer.valueOf(req.getParameter("qty"));

			Integer price = Integer.valueOf(req.getParameter("price"));

				Requisitions_DetailVO requisitions_detailVO = new Requisitions_DetailVO();	
				requisitions_detailVO.setReqNo(reqNo);
				requisitions_detailVO.setReqDetailNo(reqDetailNo);
				requisitions_detailVO.setSerialNo(serialNo);
				requisitions_detailVO.setStatus(status);
				requisitions_detailVO.setQty(qty);
				requisitions_detailVO.setPrice(price);

				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("Requisitions_DetailVO", requisitions_detailVO); // 嚙踝蕭謕�蕭嚙踐�蕭豲嚙踝蕭謕蕭豲J嚙踝蕭謕��嚙踐�蕭豲嚙踝蕭謕�蕭嚙踐�蕭豲Requisitions_DetailVO嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕�蕭嚙踐��蕭嚙踐�蕭謇q
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/requisitions_detail/update_requisitions_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.嚙踝蕭謕�蕭嚙踐�蕭蹎蕭謕蕭謘踐�蕭�嚙踝蕭謕蕭豲*****************************************/
				Requisitions_DetailService requisitions_detailSvc = new Requisitions_DetailService();
				requisitions_detailVO = requisitions_detailSvc.updateRequisitions_Detail(reqNo,reqDetailNo,serialNo, status, qty, price);
				
				/***************************3.嚙踝蕭謕蕭謘踐��嚙踐�蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success view)*************/
				req.setAttribute("Requisitions_DetailVO", requisitions_detailVO); 
				String url = "/back-end/requisitions_detail/listOneRequisitions_Detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 嚙踝蕭謕蕭�嚙踐�addRequisitions_Detail.jsp嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭嚙�  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭蹇蕭謕��此� - 嚙踝蕭謕蕭豲J嚙踝蕭謕��嚙踐�蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕��************************/	
			Integer reqNo = Integer.valueOf(req.getParameter("reqNo"));
			
			Integer serialNo = Integer.valueOf(req.getParameter("serialNo"));
				
			Byte status = Byte.valueOf(req.getParameter("status"));
				
			Integer qty = Integer.valueOf(req.getParameter("qty").trim());

			Integer price = Integer.valueOf(req.getParameter("price").trim());

				Requisitions_DetailVO requisitions_detailVO = new Requisitions_DetailVO();
				requisitions_detailVO .setReqNo(reqNo);
				requisitions_detailVO .setSerialNo(serialNo);
				requisitions_detailVO .setStatus(status);
				requisitions_detailVO .setQty(qty);
				requisitions_detailVO .setPrice(price);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("Requisitions_DetailVO", requisitions_detailVO); // 嚙踝蕭謕�蕭嚙踐�蕭豲嚙踝蕭謕蕭豲J嚙踝蕭謕��嚙踐�蕭豲嚙踝蕭謕�蕭嚙踐�蕭豲Requisitions_DetailVO嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕�蕭嚙踐��蕭嚙踐�蕭謇q
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/requisitions_detail/addRequisitions_Detail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.嚙踝蕭謕�蕭嚙踐�蕭蹎蕭謕�蕭嚙踐�蕭��蕭謕蕭豲嚙踝蕭謕***************************************/
				Requisitions_DetailService requisitions_detailSvc = new Requisitions_DetailService();
				requisitions_detailVO = requisitions_detailSvc.addRequisitions_Detail(reqNo, serialNo, status, qty, price);
				
				/***************************3.嚙踝蕭謕�蕭嚙踐�蕭��蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success view)***********/
				String url = "/back-end/requisitions_detail/listAllRequisitions_Detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭謕�蕭嚙踐�蕭��蕭謕蕭豲嚙踝蕭謕蕭嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭赯tAllRequisitions_Detail.jsp
				successView.forward(req, res);				
				}
		
		
				if ("delete".equals(action)) { // 嚙踝蕭謕蕭�嚙踐�listAllRequisitions_Detail.jsp

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭蹇蕭謕��此�***************************************/
				Integer reqDetailNo = Integer.valueOf(req.getParameter("reqDetailNo"));
				
				/***************************2.嚙踝蕭謕�蕭嚙踐�蕭蹎蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕***************************************/
				Requisitions_DetailService requisitions_detailSvc = new Requisitions_DetailService();
				requisitions_detailSvc.deleteRequisitions_Detail(reqDetailNo);
				
				/***************************3.嚙踝蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success view)***********/								
				String url = "/back-end/requisitions/listAllRequisitions.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭嚙踝蕭謕蕭豲,嚙踝蕭謕蕭豲嚙踝蕭謕嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭�嚙踝蕭�嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲
				successView.forward(req, res);
		}
				
				
				if ("getOne_For_Req".equals(action)) { // 嚙踝蕭謕蕭�嚙踐�select_page.jsp嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭嚙�
					
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					/***************************1.嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭蹇蕭謕��此� - 嚙踝蕭謕蕭豲J嚙踝蕭謕��嚙踐�蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕��**********************/
					String str = req.getParameter("reqNo");  //嚙踝蕭謕蕭��蕭��req.getParameter嚙踝蕭謕蕭豲o"reqDetailNo"嚙踝蕭謕�蕭���嚙踐�蕭豲蕭謕蕭豲嚙踝蕭謕蕭豯歹蕭謕�蕭嚙踐��tr嚙踝蕭謕蕭豲
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入採購單編號");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/requisitions_detail/select_page.jsp");
						failureView.forward(req, res);
						return;//嚙踝蕭謕滲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕��
					}
					
					Integer reqNo = null;
					try {
						reqNo = Integer.valueOf(str);
					} catch (Exception e) {
						errorMsgs.add("請輸入採購單編號");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/requisitions_detail/select_page.jsp");
						failureView.forward(req, res);
						return;//嚙踝蕭謕滲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕��
					}
					Requisitions_DetailVO requisitions_detailVO = new Requisitions_DetailVO();
					/***************************2.嚙踝蕭謕�蕭嚙踐�蕭蹎蕭謕蕭��蕭謕蕭蹎∴蕭蹇嚙踝蕭謕*****************************************/
					Requisitions_DetailService requisitions_detailService = new Requisitions_DetailService();
					List<Requisitions_DetailVO> requisitions_detailVO2 = requisitions_detailService.getReqDetailByReq(reqNo);

					
					if (requisitions_detailVO2 == null) {
						errorMsgs.add("找不到該筆採購單明細");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/requisitions_detail/select_page.jsp");
						failureView.forward(req, res);
						return;//嚙踝蕭謕滲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕��
					}
					
					/***************************3.嚙踝蕭謕蕭��蕭謕蕭蹎�蕭�嚙踝蕭謕蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success view)*************/
					req.setAttribute("Requisitions_DetailVO", requisitions_detailVO2); // 嚙踝蕭謕蕭豲嚙踐�嚙踝蕭謕蕭豲嚙踝蕭謕嚙踝蕭謕蕭豲Requisitions_DetailVO嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕�蕭嚙踐�蕭謇q
					String url = "/back-end/requisitions_detail/getlistReqDetail.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭謕蕭豲嚙踝蕭謕蕭嚙踝蕭謕蕭豲嚙踝蕭謕 listOneRequisitions_Detail.jsp
					successView.forward(req, res);
					}
}
}