package com.forumArticleMessage.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forumArticle.model.ForumArticleService;
import com.forumArticle.model.ForumArticleVO;
import com.forumArticleMessage.model.ForumArticleMessageService;

@WebServlet("/ForumArticleMessageServlet")
public class ForumArticleMessageServlet extends HttpServlet {
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
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo").trim());
			Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());
			String content = req.getParameter("messageContent");
			Integer messageState =0;
			ForumArticleMessageService article_commentSvc = new ForumArticleMessageService();
			article_commentSvc.addForumArticleMessage(articleNo, memNo, content, messageState);
			ForumArticleService articleSvc = new ForumArticleService();
			ForumArticleVO articleVO = articleSvc.getOneForumArticle(articleNo);			
			req.setAttribute("forumArticleVO", articleVO); // 資料庫取出的empVO物件,存入req
			String url = "/front-end/article/listOneArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

}
