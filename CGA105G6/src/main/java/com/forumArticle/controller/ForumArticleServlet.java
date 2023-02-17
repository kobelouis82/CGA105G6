package com.forumArticle.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.forumArticle.model.*;
import com.forumArticlePhoto.model.*;
import com.forumArticleReport.model.ForumArticleReportService;
import com.member.model.*;

@WebServlet("/ForumArticleServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ForumArticleServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("articleNo");

			Integer articleNo = null;
			try {
				articleNo = Integer.valueOf(str);
			} catch (Exception e) {

			}

			/*************************** 2.開始查詢資料 *****************************************/
			ForumArticleService articleSvc = new ForumArticleService();
			ForumArticleVO forumArticleVO = articleSvc.getOneForumArticle(articleNo);
			ForumArticlePhotoService forumPostPicSvc = new ForumArticlePhotoService();

			List<ForumArticlePhotoVO> forumArticlePhotoVO = forumPostPicSvc.getByArticleNo(articleNo);
			System.out.println(forumArticlePhotoVO + "是null嗎?");

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("forumArticleVO", forumArticleVO); // 資料庫取出的empVO物件,存入req
			req.setAttribute("forumArticlePhotoVO", forumArticlePhotoVO);
			String url = "/front-end/article/listOneArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());
			Integer forumNo = Integer.valueOf(req.getParameter("forumNo").trim());
			String title = req.getParameter("title");
			String content = req.getParameter("content").trim();
			ForumArticleService articleSvc = new ForumArticleService();
			Integer articleNo = articleSvc.addForumArticle(memNo, forumNo, content, title);

			Collection<Part> list = req.getParts();
			System.out.println("測試" + articleNo);
			BufferedInputStream bis = null;
			byte[] forumPostImgs = null;

			for (Part part : list) {
				if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
					System.out.println("到底傳入幾張照片呢");
					bis = new BufferedInputStream(part.getInputStream());
					if (bis.available() > 1024) {
						forumPostImgs = new byte[bis.available()];
						bis.read(forumPostImgs);
						ForumArticlePhotoService forumArticlePhotoService = new ForumArticlePhotoService();
						forumArticlePhotoService.addForumArticlePhoto(articleNo, forumPostImgs);
					}
				}
			}
			if(bis!=null) {
				bis.close();
			}

			/*************************** 2.開始新增資料 ***************************************/

//			session.setAttribute("memNo", memNo);
//			session.setAttribute("forumNo", forumNo);
//			session.setAttribute("content", content);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			req.setAttribute("ForumArticleVO", articleVO);
			String url = "/front-end/article/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			/*************************** 1.接收請求參數 ****************************************/
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo"));

			/*************************** 2.開始查詢資料 ****************************************/
			ForumArticleService articleSvc = new ForumArticleService();
			ForumArticleVO articleVO = articleSvc.getOneForumArticle(articleNo);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("forumArticleVO", articleVO);
			String param = "?articleNo=" + articleVO.getArticleNo() + "&title=" + articleVO.getTitle() + "&content="
					+ articleVO.getContent() + "&memNo=" + articleVO.getMemNo() + "&forumNo=" + articleVO.getForumNo();
			String url = "/front-end/article/updateArticle.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Integer articleNo = Integer.valueOf(req.getParameter("articleNo").trim());
			Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());
			Integer forumNo = Integer.valueOf(req.getParameter("forumNo").trim());
			String title = req.getParameter("title");
			Integer articleState = Integer.valueOf(req.getParameter("articleState"));
			String content = req.getParameter("content").trim();
			ForumArticlePhotoService forumPostPicSvc = new ForumArticlePhotoService();

			Collection<Part> list = req.getParts();
			BufferedInputStream bis = null;
			byte[] forumPostImgs = null;

			for (Part part : list) {
				if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
					System.out.println("到底傳入幾張照片呢");
					bis = new BufferedInputStream(part.getInputStream());
					if (bis.available() > 1024) {
						forumPostImgs = new byte[bis.available()];
						bis.read(forumPostImgs);
						ForumArticlePhotoService forumArticlePhotoService = new ForumArticlePhotoService();
						forumArticlePhotoService.addForumArticlePhoto(articleNo, forumPostImgs);
					}
				}
			}
			if(bis!=null) {
				bis.close();
			}
			

			List<ForumArticlePhotoVO> forumArticlePhotoVO = forumPostPicSvc.getByArticleNo(articleNo);
			/*************************** 2.開始修改資料 *****************************************/
			ForumArticleService articleSvc = new ForumArticleService();
			ForumArticleVO articleVO = articleSvc.updateForumArticle(articleNo, memNo, forumNo, content, title,
					articleState);
			System.out.println(articleVO.getTitle());
			System.out.println(articleVO.getArticleNo());
			System.out.println(articleVO.getArticleState());
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("forumArticlePhotoVO", forumArticlePhotoVO);
			req.setAttribute("forumArticleVO", articleVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/front-end/article/listOneArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) {
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo"));
			ForumArticleService articleSvc = new ForumArticleService();
			articleSvc.deleteForumArticle(articleNo);
			String url = "/front-end/article/select_page.jsp";
			RequestDispatcher successview = req.getRequestDispatcher(url);
			successview.forward(req, res);
		}
		if ("hideArticle".equals(action)) {

			Integer articleNo = Integer.valueOf(req.getParameter("articleNo").trim());
			String content = "一去不回頭";
			Integer articleState = 1;

			ForumArticleService articleSvc = new ForumArticleService();
			articleSvc.hideArticle(articleNo, articleState, content);
			String url = "/front-end/article/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("ArtSearch".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
			if (req.getParameter("whichPage") == null) {
				Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
				session.setAttribute("map", map1);
				map = map1;
			}
			ForumArticleService articleSvc = new ForumArticleService();
			List<ForumArticleVO> list = articleSvc.getAllArt(map);
			req.setAttribute("list", list);
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/article/listAllArt.jsp");
			successView.forward(req, res);

		}
	}

}
