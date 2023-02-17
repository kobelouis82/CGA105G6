package com.forumArticleReport.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forumArticle.model.ForumArticleService;
import com.forumArticleReport.model.ForumArticleReportService;

@WebServlet("/ForumArticleReportServlet")
public class ForumArticleReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo").trim());
			Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());
			String reportReason = req.getParameter("reportReason");
			Integer articleReportState = Integer.valueOf(req.getParameter("articleReportState").trim());
			
			ForumArticleReportService article_reportSvc = new ForumArticleReportService();
			article_reportSvc.addForumArticleReport(articleNo, memNo, articleReportState, reportReason);
			String url = "/front-end/article/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
	}

}