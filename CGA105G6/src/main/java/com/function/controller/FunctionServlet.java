package com.function.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.function.model.*;

@WebServlet("/back-end/function/FunctionServlet")
public class FunctionServlet  extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) {
//			 Select.jsp請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 請求參數,錯誤處理
			String eff = req.getParameter("adminFunction");
			// 去除空白後長度為0
			if (eff == null || (eff.trim()).length() == 0) {
				errorMsgs.put("adminFunction","請輸入權限編號");
			}
			// 是否為空字串(非空白)
			if (!errorMsgs.isEmpty()) {
				// 轉發到
				RequestDispatcher fail = req.getRequestDispatcher("/back-end/function/select_page.jsp");
				fail.forward(req, res);
				return;
			}

			Integer adminFunction = null;
			try {
				adminFunction = Integer.valueOf(eff);
			} catch (NumberFormatException e) {
				errorMsgs.put("adminFunction","權限編號不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/back-end/function/select_page.jsp");
				fail.forward(req, res);
				return;
			}

			// 驗證過,開始查詢資料
			FunctionService functionSvc = new FunctionService();
			FunctionVO functionVO = functionSvc.getOneFunction(adminFunction);
			if (functionVO == null) {
				errorMsgs.put("adminFunction","查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/back-end/function/select_page.jsp");
				fail.forward(req, res);
				return;
			}

			// 回傳資料
			req.setAttribute("functionVO", functionVO);
			String url = "/back-end/function/listOneFunction.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer adminFunction = Integer.valueOf(req.getParameter("adminFunction"));

			FunctionService functionSvc  = new FunctionService();
			FunctionVO functionVO = functionSvc.getOneFunction(adminFunction);

			req.setAttribute("functionVO", functionVO);
			String url = "/back-end/function/update_function_input.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer adminFunction = Integer.valueOf(req.getParameter("adminFunction").trim());
			String adminFunctionName = req.getParameter("adminFunctionName");
		if(adminFunctionName == null || adminFunctionName.trim().length() == 0 ) {
			errorMsgs.put("adminFunctionName","請輸入權限名稱");
		}
			FunctionVO functionVO = new FunctionVO();
			functionVO.setAdminFunction(adminFunction);
			functionVO.setAdminFunctionName(adminFunctionName);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("functionVO", functionVO);
				RequestDispatcher fail = req.getRequestDispatcher("/back-end/function/update_function_input.jsp");
				fail.forward(req, res);
				return;
			}
			// 修改
			FunctionService functionSvc = new FunctionService();
			// 修改完成
//			
			functionVO = functionSvc.updateFunction(adminFunction, adminFunctionName);

			req.setAttribute("functionVO", functionVO);
			String url = "/back-end/function/listOneFunction.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String adminFunctionName = req.getParameter("adminFunctionName").trim();
			if (adminFunctionName == null || adminFunctionName.trim().length() == 0) {
				errorMsgs.put("adminFunctionName","權限名稱請勿留白");
			}
			
			FunctionVO functionVO =new FunctionVO();
			functionVO.setAdminFunctionName(adminFunctionName);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("functionVO", functionVO);
				RequestDispatcher fail = req.getRequestDispatcher("/back-end/function/addFunction.jsp");
				fail.forward(req, res);
				return;
			}

			// 新增資料
			FunctionService functionSvc = new FunctionService();
			functionVO = functionSvc.addFunction(adminFunctionName);
//			
			// 完成,轉交
			req.setAttribute("functionVO", functionVO);
			String url = "/back-end/function/listAllFunction.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("delete".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//接收請求
			Integer adminFunction = Integer.valueOf(req.getParameter("adminFunction"));
			//刪除
			FunctionService functionSvc = new FunctionService();
			functionSvc.deleteFunction(adminFunction);
			//完成,轉交
			String url = "/back-end/function/listAllFunction.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}	
}
