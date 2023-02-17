package com.cart.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.*;



public class CartServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if ("addItem".equals(action)) {
			
			
//			1...
			Integer memNo = Integer.valueOf(request.getParameter("memNo"));
			Integer serialNo = Integer.valueOf(request.getParameter("serialNo"));
			String itemName = request.getParameter("itemName");
			Integer itemPrice = Integer.valueOf(request.getParameter("itemPrice"));
			Integer itemSale = Integer.valueOf(request.getParameter("itemSale"));
//			2...
			CartService cartSvc = new CartService();
			cartSvc.addCart(memNo, serialNo, itemName, itemPrice, itemSale);
		
			String url = "/front-end/product/cart.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
		}
		if ("removeOneSerialNo".equals(action)) {
			Integer serialNo = Integer.valueOf(request.getParameter("serialNo"));
			CartService cartSvc = new CartService();
			cartSvc.deleteCart(serialNo);
			String url = "/front-end/product/cart.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(request, response);
		}
		if ("updateCount".equals(action)) {
			Integer serialNo = Integer.valueOf(request.getParameter("serialNo"));
			Integer itemSale = Integer.valueOf(request.getParameter("itemSale"));
			CartService cartSvc = new CartService();
			cartSvc.updateCount(serialNo, itemSale);
			String url = "/front-end/product/cart.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(request, response);
		}
	}
}
