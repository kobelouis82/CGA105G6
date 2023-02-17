package com.websocketChat.controller;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

@WebServlet("/NameServlet")
public class NameServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String userName = req.getParameter("userName");

		req.setAttribute("userName", userName);
		
		if(userName.equals("admin")) {
			Jedis jedis = new Jedis("localhost", 6379);
			Set<String> keys = jedis.keys("admin:*");
			Set<String> realKey = new LinkedHashSet<String>();				
			for(String key : keys) {
				String str1 = key.substring(0, key.indexOf(":"));
				key = key.substring(str1.length()+1, key.length());
				realKey.add(key);
			}
			req.setAttribute("keys", realKey);
				
			RequestDispatcher dispatcher = req.getRequestDispatcher("/back-end/service/cs.jsp");
			dispatcher.forward(req, res);
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/front-end/chat/privateChat.jsp");
			dispatcher.forward(req, res);
		}
		
	}
}
