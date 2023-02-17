package com.secondHandRecycleSelect.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shpe.model.ShpeService;
import com.shpe.model.ShpeVO;

@WebServlet("/shrs.do")
public class secondHandRecycleSelectServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("selectAppNo".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("applicationNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入申請單編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/secondhandrecycle/secondHandRecycleSelect.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer applicationNo = null;
				try {
					applicationNo = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("申請單編號格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/secondhandrecycle/secondHandRecycleSelect.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				ShpeService shpeSvc = new ShpeService();
				ShpeVO shpeVO = shpeSvc.selectAppNo(applicationNo);
				if (shpeVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/secondhandrecycle/secondHandRecycleSelect.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shpeVO", shpeVO); 
				String url = "/front-end/secondhandrecycle/SHRSListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
	}
}
