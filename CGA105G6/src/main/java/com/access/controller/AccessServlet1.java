package com.access.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.access.model.AccessService;
import com.access.model.AccessVO;
import com.function.model.FunctionService;

@WebServlet("/access/access.do")
public class AccessServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getName_For_Display".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String adminName = req.getParameter("adminName");
				if(adminName.equals("0")) {
					errorMsgs.put("name", "請選擇管理員姓名");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/access/select_page1.jsp");
					failureView.forward(req, res);
					return;
				}
				
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/access/listNameAuthority.jsp");
				successView.forward(req, res);
			}catch(Exception e){
				errorMsgs.put("Exception" , e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/access/select_page1.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("getFunction_For_Display".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String number = req.getParameter("adminFunction");
				if(number.equals("0")) {
					errorMsgs.put("adminFunction", "請選擇權限名稱");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/access/select_page1.jsp");
					failureView.forward(req,res);
					return;
				}
				
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/access/listFx_noAuthority.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/access/select_page1.jsp");
				failureView.forward(req, res);
			}	
		}
		
		if("getOne_For_Update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			try {
			Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));
				
			AccessService  accessSvc = new AccessService();
				List<AccessVO> list = accessSvc.getbyAdmin(adminNo);
			req.setAttribute("list", list);
				
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/access/update_Access_input1.jsp");
				successView.forward(req,res);
				
			}catch(Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/access/listAllAuthority1.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs",errorMsgs);
			
			FunctionService functionSvc = new FunctionService();
			AccessService accessSvc = new AccessService();
			
			try {
				//取得前端的參數資料
				Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));
				Set<Integer> adminFunctionSet = new LinkedHashSet<Integer>();
				for(int i = 1; i <= functionSvc.getAll().size(); i++) {
					if(req.getParameter("adminFunction"+i) == null) {
						continue;
					}else {
						adminFunctionSet.add(Integer.valueOf(req.getParameter("adminFunction"+i)));
					}
				}
					
				List<AccessVO> accessVOlist = accessSvc.getbyAdmin(adminNo);
				Set<Integer> adminFunctionOriginSet = new LinkedHashSet<Integer>();
				for(AccessVO accessVO : accessVOlist) {
					adminFunctionOriginSet.add(accessVO.getAdminFunction());
				}
				for(Integer adminFunctionOrigin : adminFunctionOriginSet) {
					if(!adminFunctionSet.contains(adminFunctionOrigin)){
						accessSvc.deleteAccess(adminNo, adminFunctionOrigin);
					}
				}
				for(Integer adminFunction : adminFunctionSet) {
					if(!adminFunctionOriginSet.contains(adminFunction)){
						accessSvc.addAccess(adminNo, adminFunction);
					}
				}
				
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/access/listAllAuthority.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/access/listAllAuthority.jsp");
				failureView.forward(req, res);
			}
		}
	}
}