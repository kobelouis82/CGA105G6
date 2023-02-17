package com.secondHandRecycle.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.shpe.model.ShpeService;
import com.shpe.model.ShpeVO;

@WebServlet("/shr.do")
public class secondHandRecycleServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("selectItem".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String gameCode = req.getParameter("gameCode");
			String gameCodeReg = "^[(0-9)]{2,20}$";
			if (gameCode == null || gameCode.trim().length() == 0) {
				errorMsgs.add("產品條碼請勿空白");
			} else if (!gameCode.trim().matches(gameCodeReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("產品條碼只能是數字, 且長度必需在20之內");
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ShpeService shpeSvc = new ShpeService();
			ShpeVO shpeVO = shpeSvc.selectItem(gameCode);
			if (shpeVO == null) {
				errorMsgs.add("查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/secondrecycle/secondHandRecycle.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			HttpSession session = req.getSession();
//			
//			session.setAttribute("itemName", shpeVO.getItemName());
//			session.setAttribute("itemPrice", shpeVO.getItemPrice());
//					
			req.setAttribute("shpeVO", shpeVO);
			String url = "/front-end/secondrecycle/SRListOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("recycleAdd".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer memNo = null;
			try {
				memNo = Integer.valueOf(req.getParameter("memNo").trim());
			} catch (NumberFormatException e) {
				memNo=0;
				errorMsgs.add("");
			}
			String gameCode = null;
			try {
				gameCode = req.getParameter("gameCode");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			String itemName = null;
			try {
				itemName = req.getParameter("itemName");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			Integer itemPrice = null;
			try {
				itemPrice = Integer.valueOf(req.getParameter("itemPrice").trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			Byte diskBox = null;
			try {
				diskBox = Byte.valueOf(req.getParameter("diskBox"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			Byte disk = null;
			try {
				disk = Byte.valueOf(req.getParameter("disk"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			Boolean diskFrom = null;
			try {
				diskFrom = Boolean.valueOf(req.getParameter("diskFrom"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			Integer estimate = null;
			try {
				estimate = Integer.valueOf(req.getParameter("estimate"));
			} catch (NumberFormatException e) {
				estimate=0;
				errorMsgs.add("請點擊試算按鈕");
			}
			
			Integer recycleState =1;

			ShpeVO shpeVO = new ShpeVO();
			shpeVO.setMemNo(memNo);
			shpeVO.setGameCode(gameCode);
			shpeVO.setItemName(itemName);
			shpeVO.setItemPrice(itemPrice);
			shpeVO.setDiskBox(diskBox);
			shpeVO.setDisk(disk);
			shpeVO.setDiskFrom(diskFrom);
			shpeVO.setEstimate(estimate);
			shpeVO.setRecycleState(recycleState);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("shpeVO", shpeVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/secondrecycle/SRListOne.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ShpeService shpeSvc = new ShpeService();
			shpeVO = shpeSvc.recycleAdd(memNo,gameCode,itemName, itemPrice, diskBox, disk, diskFrom, estimate, recycleState);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/secondhandrecycle/SHRSListAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}
