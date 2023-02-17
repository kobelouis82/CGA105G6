package com.order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.order.model.*;
import com.orderDetail.model.*;
import com.cart.model.*;
import com.goodsFig.model.GoodsFigService;
import com.shop.model.ShopService;
import com.shop.model.ShopVO;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;

@MultipartConfig
@WebServlet("/OrderServlet.do")
public class OrderServlet extends HttpServlet {
	public static AllInOne domain;

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

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("orderNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer orderNo = null;
			try {
				orderNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("訂單編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			OrderService orderSvc = new OrderService();
			OrderVO orderVO = orderSvc.getOneOrder(orderNo);
			if (orderVO == null) {
				errorMsgs.add("請輸入訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("orderVO", orderVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/order/listOneOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("getOne_For_Front_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("orderNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer orderNo = null;
			try {
				orderNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("訂單編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/order/SerachOrder.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			OrderService orderSvc = new OrderService();
			OrderVO orderVO = orderSvc.getOneOrder(orderNo);
			if (orderVO == null) {
				errorMsgs.add("請輸入訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/order/SerachOrder.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("orderVO", orderVO); // 資料庫取出的empVO物件,存入req
			String url = "/front-end/order/listOneOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("getOne_For_MemNo_Front_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			
//			Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());
			HttpSession session = req.getSession();
			Integer memNo = (Integer) session.getAttribute("memNo");
			if(memNo!=null) {
				OrderService orderSvc = new OrderService();
				List<OrderVO> orderVO = orderSvc.getOneOrderForMemNo(memNo);
				req.setAttribute("orderVO", orderVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/order/listOneOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			}else {
				res.sendRedirect(req.getContextPath() + "/front-end/memLogin/login.jsp");
				return;
			}
			
			/*************************** 2.開始查詢資料 *****************************************/
//			OrderService orderSvc = new OrderService();
//			List<OrderVO> orderVO = orderSvc.getOneOrderForMemNo(memNo);
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("orderVO", orderVO); // 資料庫取出的empVO物件,存入req
//			String url = "/front-end/order/listOneOrder.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1.�����ШD�Ѽ�
			 ****************************************/
			Integer orderNo = Integer.valueOf(req.getParameter("orderNo"));

			/***************************
			 * 2.�}�l�d�߸��
			 ****************************************/
			OrderService orderSvc = new OrderService();
			OrderVO OrderVO = orderSvc.getOneOrder(orderNo);

			/***************************
			 * 3.�d�ߧ���,�ǳ����(Send the Success view)
			 ************/
			req.setAttribute("orderVO", OrderVO); // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/back-end/order/update_order_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
			 **********************/
			Integer orderNo = Integer.valueOf(req.getParameter("orderNo").trim());
			Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());
			Integer orderTotal = Integer.valueOf(req.getParameter("orderTotal").trim());
			Integer orderState = Integer.valueOf(req.getParameter("orderState").trim());
			Integer orderShip = Integer.valueOf(req.getParameter("orderShip").trim());
			Integer orderPay = Integer.valueOf(req.getParameter("orderPay").trim());
			Timestamp orderTime = java.sql.Timestamp.valueOf(req.getParameter("orderTime").trim());
			OrderVO orderVO = new OrderVO();
			orderVO.setMemNo(memNo);
			orderVO.setOrderTotal(orderTotal);
			orderVO.setOrderState(orderState);
			orderVO.setOrderShip(orderShip);
			orderVO.setOrderPay(orderPay);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("orderVO", orderVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order/update_order_input.jsp");
				failureView.forward(req, res);
				return; // �{�����_
			}

			/*************************** 2.開始新增資料 *****************************************/
			OrderService orderSvc = new OrderService();
			orderVO = orderSvc.updateOrder(orderNo, memNo, orderTotal, orderState, orderShip, orderPay,orderTime);

			/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
			req.setAttribute("orderVO", orderVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
			String url = "/back-end/order/listOneOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("insertOrder".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());
			Integer orderTotal = Integer.valueOf(req.getParameter("orderTotal").trim());
			Integer orderState = Integer.valueOf(req.getParameter("orderState").trim());
			Integer orderShip = Integer.valueOf(req.getParameter("orderShip").trim());
			Integer orderPay = Integer.valueOf(req.getParameter("orderPay").trim());
			OrderVO orderVO = new OrderVO();
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			orderVO.setMemNo(memNo);
			orderVO.setOrderTotal(orderTotal);
			orderVO.setOrderState(orderState);
			orderVO.setOrderShip(orderShip);
			orderVO.setOrderPay(orderPay);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("orderVO", orderVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/order/addOrder.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			OrderService orderSvc = new OrderService();
			int orderId = orderSvc.addOrder(orderVO);

//					List<CartVO> products = new ArrayList<CartVO>();
//					List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();

			// OrderDetail值輸入
			String[] serialNos = req.getParameterValues("serialNo");
			String[] itemPrices = req.getParameterValues("itemPrice");
			String[] itemSales = req.getParameterValues("itemSale");

			for (int i = 0; i < serialNos.length; i++) {
				Integer serialNo = Integer.valueOf(serialNos[i].trim());
				Integer itemPrice = Integer.valueOf(itemPrices[i].trim());
				Integer itemSale = Integer.valueOf(itemSales[i].trim());
				Integer status = Integer.valueOf(req.getParameter("status").trim());
				OrderDetailService orderDetailService = new OrderDetailService();
				orderDetailVO = orderDetailService.addOrderDetail(orderId, serialNo, memNo, itemPrice, itemSale,
						status);
				orderDetailVO.setOrderNo(orderId);
				orderDetailVO.setMemNo(memNo);
				orderDetailVO.setItemPrice(itemPrice);
				orderDetailVO.setItemSale(itemSale);
				orderDetailVO.setStatus(status);
				orderDetailVO.setSerialNo(serialNo);
			}

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/order/listAllOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
		if ("toMoneyPay".equals(action)) {
			// 新增訂單
			Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());
			Integer orderTotal = Integer.valueOf(req.getParameter("orderTotal").trim());
			Integer orderState = Integer.valueOf(req.getParameter("orderState").trim());
			Integer orderShip = Integer.valueOf(req.getParameter("orderShip").trim());
			Integer orderPay = Integer.valueOf(req.getParameter("orderPay").trim());
			HttpSession session = req.getSession();
			session.setAttribute("memNo", memNo);
			session.setAttribute("orderTotal", orderTotal);
			session.setAttribute("orderState", orderState);
			session.setAttribute("orderShip", orderShip);
			session.setAttribute("orderPay", orderPay);
			String[] serialNos = req.getParameterValues("serialNo");
			String[] itemPrices = req.getParameterValues("itemPrice");
			String[] itemSales = req.getParameterValues("itemSale");
			Integer status = Integer.valueOf(req.getParameter("status").trim());
			session.setAttribute("serialNos", serialNos);
			session.setAttribute("itemPrices", itemPrices);
			session.setAttribute("itemSales", itemSales);
			session.setAttribute("status", status);
//			req.setAttribute("memNo", memNo);
//			req.setAttribute("orderTotal", orderTotal);
//			req.setAttribute("orderState", orderState);
//			req.setAttribute("orderShip", orderShip);
//			req.setAttribute("orderPay", orderPay);
//				req.setAttribute("serialNos", serialNos);
//				req.setAttribute("itemPrices", itemPrices);
//				req.setAttribute("itemSales", itemSales);				
//				req.setAttribute("status", status);
			// 根據表單建立收款連結 (中文編碼有問題)
			// 使用者跳轉至綠界的交易流程網站
			// 按照流程輸入卡號..... (中文編碼!)
			// 測試卡號: 一般信用卡測試卡號 : 4311-9522-2222-2222 安全碼 : 222
			// 信用卡測試有效月/年：輸入的 MM/YYYY 值請大於現在當下時間的月年，
			// 例如在 2016/04/20 當天作測試，請設定 05/2016(含)之後的有效月年，否則回應刷卡失敗。
			// 手機請輸入正確，因為會傳驗證碼
			// 檢查後台: 信用卡收單 - 交易明細 - 查詢
			domain = new AllInOne("");
			AioCheckOutOneTime obj = new AioCheckOutOneTime();
			// 從 view 獲得資料，依照 https://developers.ecpay.com.tw/?p=2866 獲得必要的參數
			// MerchantTradeNo : 必填 特店訂單編號 (不可重複，因此需要動態產生)
			obj.setMerchantTradeNo(new String("salon" + System.currentTimeMillis()));
			// MerchantTradeDate : 必填 特店交易時間 yyyy/MM/dd HH:mm:ss
			obj.setMerchantTradeDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));
			// TotalAmount : 必填 交易金額
			obj.setTotalAmount(String.valueOf(orderTotal));
			// TradeDesc : 必填 交易描述
			obj.setTradeDesc("StoreID:" + String.valueOf(memNo));
			// ItemName : 必填 商品名稱
			String s = "hi";
			obj.setItemName(String.valueOf(s));
			// ReturnURL : 必填 我用不到所以是隨便填一個英文字
			obj.setReturnURL("a");
			// OrderResultURL : 選填 消費者完成付費後。重新導向的位置
			String url = req.getRequestURL() + "?action=insertFrontOrder";
//			String url = "http://localhost:8081" + req.getContextPath() + "/OrderServlet.do?action=insertFrontOrder";
			obj.setClientBackURL(url);
			obj.setNeedExtraPaidInfo("N");
			// 回傳form訂單 並自動將使用者導到 綠界
			String form = domain.aioCheckOut(obj, null);
			System.out.println(form);
			res.setCharacterEncoding("UTF-8");
			res.getWriter().print("<html><body>" + form + "</body></html>");
		}

		if ("insertFrontOrder".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			// 新增訂單
			HttpSession session = req.getSession();
			Integer memNo = (Integer) session.getAttribute("memNo");
			Integer orderTotal = (Integer) session.getAttribute("orderTotal");
			Integer orderState = (Integer) session.getAttribute("orderState");
			Integer orderShip = (Integer) session.getAttribute("orderShip");
			Integer orderPay = (Integer) session.getAttribute("orderPay");
//					Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());
//					Integer orderTotal = Integer.valueOf(req.getParameter("orderTotal").trim());
//					Integer orderState = Integer.valueOf(req.getParameter("orderState").trim());
//					Integer orderShip = Integer.valueOf(req.getParameter("orderShip").trim());
//					Integer orderPay = Integer.valueOf(req.getParameter("orderPay").trim());
			OrderVO orderVO = new OrderVO();
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			orderVO.setMemNo(memNo);
			orderVO.setOrderTotal(orderTotal);
			orderVO.setOrderState(orderState);
			orderVO.setOrderShip(orderShip);
			orderVO.setOrderPay(orderPay);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("orderVO", orderVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/CGA105G6/front-end/order/SerachOrder.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			OrderService orderSvc = new OrderService();
			int orderId = orderSvc.addOrder(orderVO);
			// OrderDetail值輸入

			

//					String[] serialNos = req.getParameterValues("serialNo");
//					String[] itemPrices = req.getParameterValues("itemPrice");
//					String[] itemSales = req.getParameterValues("itemSale");
			String[] serialNos = (String[]) session.getAttribute("serialNos");
			String[] itemPrices = (String[]) session.getAttribute("itemPrices");
			String[] itemSales = (String[]) session.getAttribute("itemSales");
			
			for (int i = 0; i < serialNos.length; i++) {

					  Integer serialNo = Integer.valueOf(serialNos[i].trim());
					  Integer itemPrice = Integer.valueOf(itemPrices[i].trim());
					  Integer itemSale = Integer.valueOf(itemSales[i].trim());
//					  Integer status = Integer.valueOf(req.getParameter("status").trim());				
//				Integer serialNo = (Integer) session.getAttribute("serialNos[i].trim()");
//				Integer itemPrice = (Integer) session.getAttribute("serialNos[i].trim()");
//				Integer itemSale = (Integer) session.getAttribute("serialNos[i].trim()");
				Integer status = (Integer) session.getAttribute("status");
				OrderDetailService orderDetailService = new OrderDetailService();
				orderDetailVO = orderDetailService.addOrderDetail(orderId, serialNo, memNo, itemPrice, itemSale,
						status);
				orderDetailVO.setOrderNo(orderId);
				orderDetailVO.setMemNo(memNo);
				orderDetailVO.setItemPrice(itemPrice);
				orderDetailVO.setItemSale(itemSale);
				orderDetailVO.setStatus(status);
				orderDetailVO.setSerialNo(serialNo);
			}

//					OrderVO orderVO1 = orderSvc.getOneOrder(orderId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("orderVO", orderVO);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

			String url = "/front-end/product/AllShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
	}
}
