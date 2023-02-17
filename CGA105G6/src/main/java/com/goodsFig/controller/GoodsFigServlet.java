package com.goodsFig.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.goodsFig.model.*;

@MultipartConfig
public class GoodsFigServlet extends HttpServlet{
 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

  req.setCharacterEncoding("UTF-8");
  String action = req.getParameter("action");

  if ("getOne_For_Display".equals(action)) { 

   List<String> errorMsgs = new LinkedList<String>();
   // Store this set in the request scope, in case we need to
   // send the ErrorPage view.
   req.setAttribute("errorMsgs", errorMsgs);   
   String str = req.getParameter("serialNo");
   if (str == null || (str.trim()).length() == 0) {
    errorMsgs.add("請輸入商品編號");
   }
   // Send the use back to the form, if there were errors
   if (!errorMsgs.isEmpty()) {
    RequestDispatcher failureView = req.getRequestDispatcher("/shop/select_page.jsp");
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
    RequestDispatcher failureView = req.getRequestDispatcher("/shop/select_page.jsp");
    failureView.forward(req, res);
    return;
   }  
   GoodsFigService goodsFigSvc = new GoodsFigService();
   GoodsFigVO goodsFigVO = goodsFigSvc.getOneGoodsFig(serialNo);
   if (goodsFigVO == null) {
    errorMsgs.add("查無資料");
   }
   // Send the use back to the form, if there were errors
   if (!errorMsgs.isEmpty()) {
    RequestDispatcher failureView = req.getRequestDispatcher("/shop/select_page.jsp");
    failureView.forward(req, res);
    return;
   }   
   req.setAttribute("goodsFigVO", goodsFigVO); 
   String url = "/shop/listOneShop.jsp";
   RequestDispatcher successView = req.getRequestDispatcher(url); 
   successView.forward(req, res);
  }
 }
}