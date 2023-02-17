package com.orderDetail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.orderDetail.model.*;
import com.shop.model.ShopVO;


@WebServlet("/OrderDetailServlet.do")
public class OrderDetailServlet extends HttpServlet{
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
				
				Integer orderNo = Integer.valueOf(req.getParameter("orderNo").trim());
//				String str = req.getParameter("orderNo");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入訂單編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/orderDetail/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer orderNo = null;
//				try {
//					orderNo = Integer.valueOf(str);
//				} catch (Exception e) {
//					errorMsgs.add("訂單編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/orderDetail/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************2.開始查詢資料*****************************************/
				OrderDetailService orderDetailSvc = new OrderDetailService();
				List<OrderDetailVO> orderDetailVO = orderDetailSvc.getAllOrderNo(orderNo);
				if (orderDetailVO == null) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orderDetail/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("orderDetailVO", orderDetailVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/orderDetail/listOneOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		if ("getOneFront_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer orderNo = Integer.valueOf(req.getParameter("orderNo").trim());
//				String str = req.getParameter("orderNo");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入訂單編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/orderDetail/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer orderNo = null;
//				try {
//					orderNo = Integer.valueOf(str);
//				} catch (Exception e) {
//					errorMsgs.add("訂單編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/orderDetail/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************2.開始查詢資料*****************************************/
				OrderDetailService orderDetailSvc = new OrderDetailService();
				List<OrderDetailVO> orderDetailVO = orderDetailSvc.getAllOrderNo(orderNo);
				if (orderDetailVO == null) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orderDetail/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("orderDetailVO", orderDetailVO); // 資料庫取出的empVO物件,存入req
				
				String url = "/front-end/orderDetail/listAllOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		 if ("insertOrderDetail".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer orderNo = Integer.valueOf(req.getParameter("orderNo").trim());
					Integer serialNo = Integer.valueOf(req.getParameter("serialNo").trim());
					Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());
					Integer itemPrice = Integer.valueOf(req.getParameter("itemPrice").trim());
					Integer itemSale = Integer.valueOf(req.getParameter("itemSale").trim());
					Integer status = Integer.valueOf(req.getParameter("status").trim());

					OrderDetailVO orderDetailVO = new OrderDetailVO();
					orderDetailVO.setMemNo(memNo);
					orderDetailVO.setOrderNo(orderNo);
					orderDetailVO.setSerialNo(serialNo);
					orderDetailVO.setItemSale(itemSale);
					orderDetailVO.setItemPrice(itemPrice);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("orderDetailVO", orderDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/order/addOrderDetail.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					OrderDetailService orderDetailSvc = new OrderDetailService();
					orderDetailVO = orderDetailSvc.addOrderDetail(orderNo,serialNo, memNo, itemPrice, itemSale, status);
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					
					String url = "/back-end/order/listAllOrderDetail.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
			}
		 if ("getOne_For_Update".equals(action)) { 

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				
				Integer orderNo = Integer.valueOf(req.getParameter("orderNo"));

				
				OrderDetailService orderDetailSvc = new OrderDetailService();
				OrderDetailVO OrderDetailVO = orderDetailSvc.getOneOrderDetail(orderNo);

				
				req.setAttribute("orderDetailVO", OrderDetailVO); 
				String url = "/back-end/orderDetail/update_orderDetail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
			}
			
			if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				Integer orderNo = Integer.valueOf(req.getParameter("orderNo").trim());
				Integer status = Integer.valueOf(req.getParameter("status").trim());
				OrderDetailVO orderDetailVO = new OrderDetailVO();
				orderDetailVO.setStatus(status);			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderDetailVO", orderDetailVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orderDetail/update_order_input.jsp");
					failureView.forward(req, res);
					return; 
				}

				/*************************** 2.開始新增資料*****************************************/
				OrderDetailService orderDetailService = new OrderDetailService();
				orderDetailVO = orderDetailService.updateOrderDetail(status,orderNo);

				/*************************** 3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("orderDetailVO", orderDetailVO); 
				String url = "/back-end/orderDetail/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			}
	}
}
