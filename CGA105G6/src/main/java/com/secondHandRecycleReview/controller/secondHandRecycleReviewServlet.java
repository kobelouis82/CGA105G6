package com.secondHandRecycleReview.controller;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shpe.model.*;

@WebServlet("/shrrs.do")
public class secondHandRecycleReviewServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getRecycleState".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer applicationNo = Integer.valueOf(req.getParameter("applicationNo"));
				
				/***************************2.開始查詢資料****************************************/
				ShpeService shpeSvc = new ShpeService();
				ShpeVO shpeVO = shpeSvc.getRecycleState(applicationNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("shpeVO", shpeVO);         // 資料庫取出的empVO物件,存入req
				String url = "back-end/secondhandrecycle/secondHandRecycleReviewUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		if ("updateState".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer applicationNo  = Integer.valueOf(req.getParameter("applicationNo"));
			
			String gameCode = req.getParameter("gameCode");
			
			String itemName = req.getParameter("itemName");
			
			Byte diskBox = Byte.valueOf(req.getParameter("diskBox"));
			
			Byte disk = Byte.valueOf(req.getParameter("disk"));
			
			Boolean diskFrom = Boolean.valueOf(req.getParameter("diskFrom"));
			
			Integer estimate = Integer.valueOf(req.getParameter("estimate"));
			
			Integer recycleState = Integer.valueOf(req.getParameter("recycleState"));
			


				ShpeVO shpeVO = new ShpeVO();
				shpeVO.setApplicationNo(applicationNo);
				shpeVO.setGameCode(gameCode);
				shpeVO.setItemName(itemName);
				shpeVO.setDiskBox(diskBox);
				shpeVO.setDisk(disk);
				shpeVO.setDiskFrom(diskFrom);
				shpeVO.setEstimate(estimate);
				shpeVO.setRecycleState(recycleState);
				

				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shpeVO", shpeVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/secondhandrecycle/secondHandRecycleReviewUpdate.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始修改資料*****************************************/
				ShpeService shpeSvc = new ShpeService();
				shpeVO = shpeSvc.updateState(applicationNo, gameCode, itemName, diskBox, disk, diskFrom, estimate, recycleState);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shpeVO", shpeVO); 
				String url = "back-end/secondhandrecycle/secondHandRecycleReviewUpdateOk.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}	
	}
}
