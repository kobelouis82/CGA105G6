package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.admin.model.*;
import com.member.model.MemService;
import com.member.model.MemVO;
@MultipartConfig
@WebServlet("/MemServletFront")
public class MemServletFront extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String Date = null;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        if ("getOne_For_UpdateMem".equals(action)) { // 來自listAllEmp.jsp的請求??

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            Integer memNo = (Integer) session.getAttribute("memNo");

            /*************************** 2.開始查詢資料 ****************************************/
            MemService memSvc = new MemService();
            MemVO memVO = memSvc.getOneMem(memNo);

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            session.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
            String url = "/front-end/member/updateMem.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }

        if ("updateMem".equals(action)) { // 來自update_emp_input.jsp的請求

        	Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

            Integer memNo = Integer.valueOf(req.getParameter("memNo").trim());

			String memName = req.getParameter("memName");
			String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memName == null || memName.trim().length() == 0) {
				errorMsgs.put("memName", "請輸入姓名");
			} else if (!memName.trim().matches(memNameReg)) {
				errorMsgs.put("memName", "姓名欄位: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
			}

			String memTel = req.getParameter("memTel").trim();
			if (memTel == null || memTel.trim().length() == 0) {
				errorMsgs.put("memTel", "請輸入電話");
			}

			String memCity = req.getParameter("memCity").trim();
			String memCityReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memCity == null || memCity.trim().length() == 0) {
				errorMsgs.put("memCity", "請輸入所在地");
			} else if (!memCity.trim().matches(memCityReg)) {
				errorMsgs.put("memCity", "請輸入正確格式");
			}
			java.sql.Date memBirth = null;
			try {
				memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("memBirth", "請輸入日期");
			}
			String memMail = req.getParameter("memMail").trim();
			String memMailReg = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
			if (memMail == null || memMail.trim().length() == 0) {
				errorMsgs.put("memMail", "email欄位請勿空白");
			} else if (!memMail.trim().matches(memMailReg)) {
				errorMsgs.put("memMail", "請填入正確的email格式");
			}
			String memAccount = req.getParameter("memAccount");
			String memAccountReg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$";
			if (memAccount == null || memAccount.trim().length() == 0) {
				errorMsgs.put("memAccount", "帳號欄位請勿空白");
			} 
//			else if (!memAccount.trim().matches(memAccountReg)) {
//				errorMsgs.put("memAccount", "帳號：至少一個字母和一個數字 , 且長度必需在6到20之間");
//			}

			String memPassword = req.getParameter("memPassword").trim();
			String memPasswordReg = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$";
			if (memPassword == null || memPassword.trim().length() == 0) {
				errorMsgs.put("memPassword", "密碼欄位請勿空白");
			} 
//			else if (!memPassword.trim().matches(memPasswordReg)) {
//				errorMsgs.put("memPassword", "密碼：至少一個大寫字母、和一個數字, 且長度必需在6到20之間：");
//			}
			String memDist = req.getParameter("memDist").trim();

			if (memDist == null || memDist.trim().length() == 0) {
				errorMsgs.put("memDist", "區域請勿空白");
			}
			String memAdd = req.getParameter("memAdd").trim();
			String memAddReg = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$";
			if (memAdd == null || memAdd.trim().length() == 0) {
				errorMsgs.put("memAdd", "地址請勿空白");
			} 
//			else if (!memAdd.trim().matches(memAddReg)) {
//				errorMsgs.put("memAdd", "密碼：至少一個大寫字母、和一個數字, 且長度必需在6到20之間：");
//			}
			Integer memAccess = null;
			try {
				memAccess = Integer.valueOf(req.getParameter("memAccess"));
			} catch (NumberFormatException e) {
				memAccess = 0;
				errorMsgs.put("memAccess", "只能輸入數字");
			}
			Integer articleReport = Integer.valueOf(req.getParameter("articleReport"));
			Integer messageReport = Integer.valueOf(req.getParameter("messageReport"));

			Part part = req.getPart("upfile1");
			byte[] memPhoto = part.getInputStream().readAllBytes();
			if (memPhoto.length == 0) {
				MemService memberSvc2 = new MemService();
				memPhoto = memberSvc2.getOneMem(memNo).getMemPhoto();
			}


            MemVO memVO = new MemVO();
    		memVO.setMemNo(memNo);
			memVO.setMemName(memName);
			memVO.setMemTel(memTel);
			memVO.setMemCity(memCity);
			memVO.setMemBirth(memBirth);
			memVO.setMemDist(memDist);
			memVO.setMemAdd(memAdd);
			memVO.setMemMail(memMail);
			memVO.setMemAccount(memAccount);
			memVO.setMemPassword(memPassword);
			memVO.setMemPhoto(memPhoto);
			memVO.setMemAccess(memAccess);
			memVO.setArticleReport(articleReport);
			memVO.setMessageReport(messageReport);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                session.setAttribute("memVO", memVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/update_member_input.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            /*************************** 2.開始新增資料 ***************************************/

            MemService memSvc = new MemService();
			memVO = memSvc.updatemem(memNo, memName, memTel, memCity, memBirth, memDist, memAdd, memMail, memAccount,
					memPassword, memPhoto, memAccess, articleReport, messageReport);
         
            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/front-end/product/index.jsp";
        	RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
//            res.sendRedirect(url);

        }


    }
}