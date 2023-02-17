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

@WebServlet("/ForumArticleReportServletforadmin")
public class ForumArticleReportForAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("updateRes".equals(action)) {
			Integer articleReportResult = Integer.valueOf(req.getParameter("articleReportResult").trim());
			Integer articleReportState = Integer.valueOf(req.getParameter("articleReportState").trim());
			Integer articleReportNo = Integer.valueOf(req.getParameter("articleReportNo").trim());
			System.out.println(articleReportResult);
			System.out.println(articleReportState);
			System.out.println(articleReportNo);
			ForumArticleReportService articleReportSvc = new ForumArticleReportService();
			articleReportSvc.updateForumArticleReport(articleReportNo, articleReportState, articleReportResult);
			String url = "/back-end/article/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("hideArticle".equals(action)) {
			Integer articleReportResult = Integer.valueOf(req.getParameter("articleReportResult").trim());
			Integer articleReportState = Integer.valueOf(req.getParameter("articleReportState").trim());
			Integer articleReportNo = Integer.valueOf(req.getParameter("articleReportNo").trim());
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo").trim());
			String content = "一去不回頭";
			Integer articleState = 1;
			
			
			ForumArticleReportService articleReportSvc = new ForumArticleReportService();
			articleReportSvc.updateForumArticleReport(articleReportNo, articleReportState, articleReportResult);
			ForumArticleService articleSvc = new ForumArticleService();
			articleSvc.hideArticle(articleNo, articleState, content);
			String url = "/back-end/article/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("hideArticleforMem".equals(action)) {
			Integer articleReportResult = Integer.valueOf(req.getParameter("articleReportResult").trim());
			Integer articleReportState = Integer.valueOf(req.getParameter("articleReportState").trim());
			Integer articleReportNo = Integer.valueOf(req.getParameter("articleReportNo").trim());
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo").trim());
			String content = "一去不回頭";
			Integer articleState = 1;
			
			
			ForumArticleReportService articleReportSvc = new ForumArticleReportService();
			articleReportSvc.updateForumArticleReport(articleReportNo, articleReportState, articleReportResult);
			ForumArticleService articleSvc = new ForumArticleService();
			articleSvc.hideArticle(articleNo, articleState, content);
			String url = "/front-end/article/myArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

}
