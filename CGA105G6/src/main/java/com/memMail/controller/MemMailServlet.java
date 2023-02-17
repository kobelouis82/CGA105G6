package com.memMail.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.memMail.model.*;


@WebServlet("/memMail/memMail.do")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class MemMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int count = 1;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		System.out.println("in");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("insert".equals(action)){
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
				
				String sendNoTest = req.getParameter("sendNo");
				if(sendNoTest.equals("99")) {
					errorMsgs.put("sendNo", new String[] {"寄件人出錯"});
				}
				Integer sendNo = Integer.valueOf(sendNoTest);
				
				String rcptNoTest = req.getParameter("rcptNo");
//				String rcptNoReg = "^[(0-9)]{5,5}$";
//				if(!rcptNoTest.trim().matches(rcptNoReg)) {
//					errorMsgs.put("rcptNo", new String[] {"收件人出錯"});
//				}
				Integer rcptNo = Integer.valueOf(rcptNoTest);
				String mailTitle = req.getParameter("mailTitle");
				if(mailTitle.trim().isEmpty()) {
					errorMsgs.put("mailTitle", new String[] {"忘了打標題?"});
				}
				String mailCont = req.getParameter("mailCont");
				if(mailCont.trim().isEmpty()) {
					errorMsgs.put("mailCont", new String[] {"忘了打內容?"});
				}

				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memMail/addMemMail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				Integer mailStat =  1;
				Integer mailReadStat =  Integer.valueOf(req.getParameter("mailReadStat"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String mailTime = sdf.format(new java.util.Date());
				
				MemMailService memMailSvc = new MemMailService();
				MemMailVO memMailVO = memMailSvc.addMemMail(sendNo,rcptNo,mailReadStat,mailStat,mailCont,mailTime, mailTitle);
				req.setAttribute("memMailVO", memMailVO);
				
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/memMail/listAllMemMail.jsp");
				successView.forward(req, res);
				
		}
		
		if("getOne_For_Update".equals(action)) {
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				MemMailVO memMailVO = new MemMailService().getOneMemMail(Integer.valueOf(req.getParameter("mailNo")));
				req.setAttribute("memMailVO", memMailVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/memMail/update_memMail_input.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memMail/listAllMemMail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("read".equals(action)) {
			Map<String,String[]> errorMsgs = new LinkedHashMap<String,String[]>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				MemMailService memMailSvc = new MemMailService();
				MemMailVO memMailVO = memMailSvc.getOneMemMail(Integer.valueOf(req.getParameter("mailNo")));
				memMailVO.setMailReadStat(1);
				
				Integer sendNo =  memMailVO.getSendNo();
				Integer rcptNo =  memMailVO.getRcptNo();
				String mailCont =  memMailVO.getMailCont();
				Integer mailStat =  memMailVO.getMailStat();
				Integer mailReadStat =  memMailVO.getMailReadStat();
				String mailTime = memMailVO.getMailTime();
				Integer mailNo =  memMailVO.getMailNo();
				String mailTitle =  memMailVO.getMailTitle();
				MemMailVO memMailVO2 = memMailSvc.updateMemMail(mailNo,sendNo,rcptNo,mailReadStat,mailStat,mailCont,mailTime,mailTitle);
				req.setAttribute("memMailVO", memMailVO2);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/memMail/listOneMemMail.jsp");
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.put("exception", new String[] {e.getMessage()});
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/memMail/listAllMemMail.jsp");
				failureView.forward(req, res);
			}
		}
	}
}