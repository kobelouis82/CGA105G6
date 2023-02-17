package com.shop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.cart.model.CartService;
import com.favorite.model.FavoriteService;
import com.goodsFig.model.GoodsFigService;
import com.goodsFig.model.GoodsFigVO;
import com.shop.model.ShopService;
import com.shop.model.ShopVO;

@MultipartConfig
@WebServlet("/ShopServlet.do")
public class ShopServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
			String str = req.getParameter("serialNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入商品編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer serialNo = null;
			try {
				serialNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("商品編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.�}�l�d�߸�� *****************************************/
			ShopService shopSvc = new ShopService();
			ShopVO shopVO = shopSvc.getOneShop(serialNo);
			if (shopVO == null) {
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
			req.setAttribute("shopVO", shopVO); 
//			SingleProductShop.jsp
			String url = "/front-end/product/SingleProductShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("getOne_For_BackEnd_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
			String str = req.getParameter("serialNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入商品編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer serialNo = null;
			try {
				serialNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("商品編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.�}�l�d�߸�� *****************************************/
			ShopService shopSvc = new ShopService();
			ShopVO shopVO = shopSvc.getOneShop(serialNo);
			if (shopVO == null) {
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
			req.setAttribute("shopVO", shopVO); 
//			SingleProductShop.jsp			
			String url = "/back-end/shop/listOneShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("getOne_For_Display_To_ShopDetail".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
			String str = req.getParameter("serialNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入商品編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer serialNo = null;
			try {
				serialNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("商品編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.�}�l�d�߸�� *****************************************/
			ShopService shopSvc = new ShopService();
			ShopVO shopVO = shopSvc.getOneShop(serialNo);
			if (shopVO == null) {
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
			req.setAttribute("shopVO", shopVO); 
			String url = "/CGA105G6/front-end/product/SingleProductShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� ****************************************/
			Integer serialNo = Integer.valueOf(req.getParameter("serialNo"));

			/*************************** 2.�}�l�d�߸�� ****************************************/
			ShopService shopSvc = new ShopService();
			ShopVO ShopVO = shopSvc.getOneShop(serialNo);

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
			req.setAttribute("shopVO", ShopVO); 
			String url = "/back-end/shop/update_shop_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("serach_For_Product".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
			String itemName = req.getParameter("itemName");
			/*************************** 2.�}�l�d�߸�� *****************************************/
			ShopService shopSvc = new ShopService();
			List<ShopVO> shopVO = shopSvc.getByItemName(itemName);
			if (shopVO == null) {
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/product/AllShop.jsp");
				failureView.forward(req, res);
				return;
			}

			
			req.setAttribute("shopVO", shopVO); 

			String url = "/front-end/product/SerachShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
			Integer serialNo = Integer.valueOf(req.getParameter("serialNo").trim());

			String gameCode = req.getParameter("gameCode").trim();
			if (gameCode == null || gameCode.trim().length() == 0) {
				errorMsgs.add("商品條碼請勿空白");
			}
			String itemName = req.getParameter("itemName");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_\s)]{2,20}$";
			


			String itemDes = req.getParameter("itemDes");			
		
			
			Integer itemPrice = null;
			try {
				itemPrice = Integer.valueOf(req.getParameter("itemPrice").trim());
			} catch (NumberFormatException e) {
				itemPrice = 0;
				errorMsgs.add("請輸入數字");
			}

			Integer gameClassNo = null;
			try {
				gameClassNo = Integer.valueOf(req.getParameter("gameClassNo").trim());
			} catch (NumberFormatException e) {
				gameClassNo = 0;
				errorMsgs.add("請輸入數字");
			}

			Integer gamePlatformNo = null;
			try {
				gamePlatformNo = Integer.valueOf(req.getParameter("gamePlatformNo").trim());
			} catch (NumberFormatException e) {
				gamePlatformNo = 0;
				errorMsgs.add("請輸入數字");
			}

			Integer comNo = null;
			try {
				comNo = Integer.valueOf(req.getParameter("comNo").trim());
			} catch (NumberFormatException e) {
				comNo = 0;
				errorMsgs.add("請輸入數字");
			}

			Integer status = null;
			try {
				status = Integer.valueOf(req.getParameter("status").trim());
			} catch (NumberFormatException e) {
				status = 0;
				errorMsgs.add("請輸入數字");
			}
			

			Integer inventoryStock = null;
			try {
				inventoryStock = Integer.valueOf(req.getParameter("inventoryStock").trim());
			} catch (NumberFormatException e) {
				inventoryStock = 0;
				errorMsgs.add("請輸入數字");
			}

			Integer preorderQty = null;
			try {
				preorderQty = Integer.valueOf(req.getParameter("preorderQty").trim());
			} catch (NumberFormatException e) {
				preorderQty = 0;
				errorMsgs.add("請輸入數字");
			}
			
			Integer itemState = null;
			try {
				itemState = Integer.valueOf(req.getParameter("itemState").trim());
			} catch (NumberFormatException e) {
				itemState = 0;
				errorMsgs.add("請輸入數字");
			}


			ShopVO shopVO = new ShopVO();
			shopVO.setSerialNo(serialNo);
			shopVO.setGameCode(gameCode);
			shopVO.setItemName(itemName);
			shopVO.setItemPrice(itemPrice);
			shopVO.setGameClassNo(gameClassNo);
			shopVO.setGamePlatformNo(gamePlatformNo);
			shopVO.setComNo(comNo);
			shopVO.setStatus(status);
			shopVO.setItemDes(itemDes);
			shopVO.setInventoryStock(inventoryStock);
			shopVO.setPreorderQty(preorderQty);
			shopVO.setItemState(itemState);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("shopVO", shopVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/update_shop_input.jsp");
				failureView.forward(req, res);
				return; 
			}

			/*************************** 2.�}�l�ק��� *****************************************/
			ShopService shopSvc = new ShopService();
			shopVO = shopSvc.updateShop(serialNo, gameCode, itemName, itemPrice, gameClassNo, gamePlatformNo, comNo,
					status, itemDes, inventoryStock, preorderQty, itemState);

			/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
			req.setAttribute("shopVO", shopVO); 
			String url = "/back-end/shop/listOneShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			
			String gameCode = req.getParameter("gameCode").trim();
			if (gameCode == null || gameCode.trim().length() == 0) {
				errorMsgs.add("商品條碼請勿空白");
			}
			
			String itemName = req.getParameter("itemName");
//			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
//			if (itemName == null || itemName.trim().length() == 0) {
//				errorMsgs.add("商品名稱: 請勿空白");
//			} 

			Integer itemPrice = null;
			try {
				itemPrice = Integer.valueOf(req.getParameter("itemPrice").trim());
			} catch (NumberFormatException e) {
				itemPrice = 0;
				errorMsgs.add("請輸入數字");
			}

			Integer gameClassNo = null;
			try {
				gameClassNo = Integer.valueOf(req.getParameter("gameClassNo").trim());
			} catch (NumberFormatException e) {
				gameClassNo = 0;
				errorMsgs.add("請輸入數字");
			}

			Integer gamePlatformNo = null;
			try {
				gamePlatformNo = Integer.valueOf(req.getParameter("gamePlatformNo").trim());
			} catch (NumberFormatException e) {
				gamePlatformNo = 0;
				errorMsgs.add("請輸入數字");
			}

			Integer comNo = null;
			try {
				comNo = Integer.valueOf(req.getParameter("comNo").trim());
			} catch (NumberFormatException e) {
				comNo = 0;
				errorMsgs.add("請輸入數字");
			}

			Integer status = null;
			try {
				status = Integer.valueOf(req.getParameter("status").trim());
			} catch (NumberFormatException e) {
				status = 0;
				errorMsgs.add("請輸入數字");
			}

			String itemDes = req.getParameter("itemDes");
			

			Integer inventoryStock = null;
			try {
				inventoryStock = Integer.valueOf(req.getParameter("inventoryStock").trim());
			} catch (NumberFormatException e) {
				inventoryStock = 0;
				errorMsgs.add("請輸入數字");
			}

			Integer preorderQty = null;
			try {
				preorderQty = Integer.valueOf(req.getParameter("preorderQty").trim());
			} catch (NumberFormatException e) {
				preorderQty = 0;
				errorMsgs.add("請輸入數字");
			}
			Integer itemState = null;
			try {
				itemState = Integer.valueOf(req.getParameter("itemState").trim());
			} catch (NumberFormatException e) {
				itemState = 0;
				errorMsgs.add("請輸入數字");
			}
//			Integer itemState = null;
//			try {
//				itemState = Integer.valueOf(req.getParameter("itemState").trim());
//			} catch (NumberFormatException e) {
//				itemState = 0;
//				errorMsgs.add("0���U�[�A1���W�[,���o�]�w��L�ƭ�!");
//			}

//			Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
			
//			Part part =req.getPart("upfile1");
//			InputStream in =part.getInputStream();
//			byte[] photo=new byte[in.available()];
//			String photoID=req.getParameter("serialNo");
//			GoodsFigService goodsFigSvc= new GoodsFigService();
//			GoodsFigVO goodsFigVO= goodsFigSvc.addGoodsOneFig(Integer.parseInt(photoID),photo);
//			res.setContentType("image/jpg");
//			res.setContentLength(goodsFigVO.getGamePic().length);
//			in.read(photo);
//			in.close();
			
			Part part =req.getPart("upfile1");
			InputStream in =part.getInputStream();
			byte[] gamePic=new byte[in.available()];
			in.read(gamePic);
			in.close();

			
			ShopVO shopVO = new ShopVO();
			GoodsFigVO goodsFigVO = new GoodsFigVO();
			shopVO.setGameCode(gameCode);
			shopVO.setItemName(itemName);
			shopVO.setItemPrice(itemPrice);
			shopVO.setGameClassNo(gameClassNo);
			shopVO.setGamePlatformNo(gamePlatformNo);
			shopVO.setComNo(comNo);
			shopVO.setStatus(status);
			shopVO.setItemDes(itemDes);
			shopVO.setInventoryStock(inventoryStock);
			shopVO.setPreorderQty(preorderQty);
			shopVO.setItemState(itemState);
			

			


			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("shopVO", shopVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop/addShop.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.�}�l�s�W��� ***************************************/
			ShopService shopSvc = new ShopService();
			int id = shopSvc.addShop(shopVO);
			GoodsFigService goodsFigSvc = new GoodsFigService();
			goodsFigVO = goodsFigSvc.addGoodsOneFig(id, gamePic);
			
			goodsFigVO.setGamePic(gamePic);
			goodsFigVO.setSerialNo(id);
			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			String url = "/back-end/shop/listAllShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("getPhoto".equals(action)) { 
			OutputStream out =res.getOutputStream();
			String photoID=req.getParameter("serialNo");
			GoodsFigService goodsFigSvc= new GoodsFigService();
			GoodsFigVO goodsFigVO= goodsFigSvc.getOneGoodsFig(Integer.parseInt(photoID));
			
//			System.out.println("id = "+Integer.parseInt(photoID));
			
			res.setContentType("image/jpg");
			res.setContentLength(goodsFigVO.getGamePic().length);
			out.write(goodsFigVO.getGamePic());
			out.close();
		}
		
		if ("addItem".equals(action)) { 
			
			if (req.getSession().getAttribute("memNo") == null) {
//				/CGA105G6/src/main/webapp/front-end/memLogin/login.jsp
				String url = "/front-end/memLogin/login.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
//			1...
			Integer memNo = Integer.valueOf(req.getParameter("memNo"));
			Integer serialNo = Integer.valueOf(req.getParameter("serialNo"));
			String itemName = req.getParameter("itemName");
			Integer itemPrice = Integer.valueOf(req.getParameter("itemPrice"));
			Integer itemSale = Integer.valueOf(req.getParameter("itemSale"));
//			2...
			CartService cartSvc = new CartService();
			boolean isItemExist = cartSvc.isItemExist(serialNo);

			if (!isItemExist) {
				cartSvc.addCart(memNo, serialNo, itemName, itemPrice, itemSale);	
				
			} else {
				cartSvc.updateCart(serialNo,itemSale);
			}
			
			String url = "/front-end/product/AllShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
if ("addItemToFavorite".equals(action)) { 
			
			if (req.getSession().getAttribute("memNo") == null) {
//				/CGA105G6/src/main/webapp/front-end/memLogin/login.jsp
				String url = "/front-end/memLogin/login.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
//			1...
			Integer memNo = Integer.valueOf(req.getParameter("memNo"));
			Integer serialNo = Integer.valueOf(req.getParameter("serialNo"));
			String itemName = req.getParameter("itemName");
			Integer itemPrice = Integer.valueOf(req.getParameter("itemPrice"));
			Integer itemSale = Integer.valueOf(req.getParameter("itemSale"));
//			2...
			FavoriteService favoriteSvc = new FavoriteService();
			boolean isItemExist = favoriteSvc.isItemExist(serialNo);

			if (!isItemExist) {
				favoriteSvc.addCart(memNo, serialNo, itemName, itemPrice, itemSale);	
				
			} else {
				favoriteSvc.updateCart(serialNo,itemSale);
			}
			
			String url = "/front-end/product/AllShop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("removeOneItem".equals(action)) {
			Integer serialNo = Integer.valueOf(req.getParameter("serialNo"));
			CartService cartSvc = new CartService();
			cartSvc.deleteCart(serialNo);
			String url = "/front-end/product/CartList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("removeOneItemForFavorite".equals(action)) {
			Integer serialNo = Integer.valueOf(req.getParameter("serialNo"));
			FavoriteService favoriteSvc = new FavoriteService();
			favoriteSvc.deleteCart(serialNo);
			String url = "/front-end/product/FavoriteList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("updateCount".equals(action)) {
			Integer serialNo = Integer.valueOf(req.getParameter("serialNo"));
			Integer itemSale = Integer.valueOf(req.getParameter("itemSale"));
			CartService cartSvc = new CartService();
			cartSvc.updateCount(serialNo, itemSale);
			String url = "/front-end/product/CartList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("getItemName".equals(action)) { 
			
			Integer serialNo = Integer.valueOf(req.getParameter("serialNo"));
			ShopService shopService = new ShopService();
			shopService.getOneItemName(serialNo);
		}
		if ("SecondHandRecycle_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
//System.out.println("map="+map.get("action")[0]);				
//System.out.println("map="+map.get("itemName")[0]);				
//System.out.println("map.size()="+map.size());				
				/***************************2.開始複合查詢***************************************/
				ShopService shopSvc = new ShopService();
				List<ShopVO> list  = shopSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("SecondHandRecycle_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("front-end/secondrecycle/SecondHandRecycle_ByCompositeQuery.jsp"); 
				successView.forward(req, res);
		}
		
		if ("selectItemInf".equals(action)) {

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
			ShopService shopSvc = new ShopService();
			ShopVO shopVO = shopSvc.selectItemInf(gameCode);
			if (shopVO == null) {
				errorMsgs.add("查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/secondrecycle/secondHandRecycle.jsp");
				failureView.forward(req, res);
				return;
			}
		
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/				
			req.getSession().setAttribute("shopVO", shopVO);

			
			String url = "/front-end/secondrecycle/SRListOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}
}

		


