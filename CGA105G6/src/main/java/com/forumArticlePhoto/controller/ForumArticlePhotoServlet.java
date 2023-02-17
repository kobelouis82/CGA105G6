package com.forumArticlePhoto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forumArticlePhoto.model.*;

@WebServlet("/ForumArticlePhotoGetByPhotoNo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ForumArticlePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		Integer ForumArticlePhotoNo = Integer.valueOf(req.getParameter("ForumArticlePhotoNo").trim());
		System.out.println(ForumArticlePhotoNo);
		ForumArticlePhotoService ForumArticlePhotoSvc = new ForumArticlePhotoService();
		ForumArticlePhotoVO ForumArticlePhotoVO = ForumArticlePhotoSvc.getOneForumArticlePhoto(ForumArticlePhotoNo);

		ServletOutputStream out = res.getOutputStream();
		out.write(ForumArticlePhotoVO.getPhoto());

		out.close();
	}

}

