package com.favoriteArticle.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.favoriteArticle.model.*;
import com.forumArticle.model.ForumArticleService;
import com.forumArticle.model.ForumArticleVO;
import com.forumArticlePhoto.model.ForumArticlePhotoService;
import com.forumArticlePhoto.model.ForumArticlePhotoVO;
import com.member.model.MemVO;
@WebServlet("/FavoriteArt.do")
public class FavoriteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		// session 取得會員編號
		MemVO memVO = (MemVO) req.getSession().getAttribute("memVO");
		Integer memNo = memVO.getMemNo();
		String action = req.getParameter("action");
		System.out.println("有沒有印出"+memNo);
		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 *************************/
			ForumFavoriteArticleService forumFavoriteArticleSvc = new ForumFavoriteArticleService();
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo").trim());
			ForumFavoriteArticleVO forumFavoriteArticleVO = forumFavoriteArticleSvc.getOneFavorite(memNo, articleNo);

			if (forumFavoriteArticleVO != null) {
				req.setAttribute("articleNo", articleNo);
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			ForumArticleService articleSvc = new ForumArticleService();
			ForumArticleVO forumArticleVO = articleSvc.getOneForumArticle(articleNo);
			ForumArticlePhotoService forumPostPicSvc = new ForumArticlePhotoService();

			List<ForumArticlePhotoVO> forumArticlePhotoVO = forumPostPicSvc.getByArticleNo(articleNo);
			System.out.println(forumArticlePhotoVO+"是null嗎?");

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("forumArticleVO", forumArticleVO); 
			req.setAttribute("forumArticlePhotoVO", forumArticlePhotoVO);
			forumFavoriteArticleSvc.addForumFavoriteArticle(memNo, articleNo);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("articleNo", articleNo);
			String url = "/front-end/article/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("delete".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 *************************/
			ForumFavoriteArticleService forumFavoriteArticleSvc = new ForumFavoriteArticleService();
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo").trim());

			/*************************** 2.開始刪除資料 ***************************************/
			forumFavoriteArticleSvc.deleteForumFavoriteArticle(memNo, articleNo);
			/*************************** 3.收尋資料 ***************************************/
			List<ForumFavoriteArticleVO> forumFavoriteArticleSessionVOs = forumFavoriteArticleSvc.getOneAllFavorite(memNo);

			/*************************** 4.新增完成,準備轉交(Send the Success view) ***********/

			req.getSession().setAttribute("forumFavoriteArticleSessionVOs", forumFavoriteArticleSessionVOs);
			String url = "/front-end/article/FavoriteArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("listAllFavorite".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			ForumFavoriteArticleService forumFavoriteArticleSvc = new ForumFavoriteArticleService();
			List<ForumFavoriteArticleVO> forumFavoriteArticleSessionVOs = forumFavoriteArticleSvc.getOneAllFavorite(memNo);

			req.getSession().setAttribute("forumFavoriteArticleSessionVOs", forumFavoriteArticleSessionVOs);

			RequestDispatcher successView = req.getRequestDispatcher("/front-end/article/FavoriteArticle.jsp");
			successView.forward(req, res);
		}
		if ("myArt".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			ForumArticleService forumPostSvc = new ForumArticleService();
			List<ForumArticleVO> forumArticleSessionVOs = forumPostSvc.findMyPost(memNo);

			req.getSession().setAttribute("forumArticleSessionVOs", forumArticleSessionVOs);
			req.setAttribute("memVO", memVO);

			RequestDispatcher successView = req.getRequestDispatcher("/front-end/article/myArticle.jsp");
			successView.forward(req, res);
		}
	}
}
