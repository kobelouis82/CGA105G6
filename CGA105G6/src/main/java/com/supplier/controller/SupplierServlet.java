package com.supplier.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.supplier.model.SupplierService;
import com.supplier.model.SupplierVO;


@WebServlet("/supplier.do")
public class SupplierServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 嚙諉佗蕭select_page.jsp嚙踝蕭嚙請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭嚙羯嚙畿嚙緲**********************/
				String str = req.getParameter("supplyNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廠商編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/supplier/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				Integer supplyNo = null;
				try {
					supplyNo = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("廠商編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/supplier/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�*****************************************/
				SupplierService supplierSvc = new SupplierService();
				SupplierVO supplierVO = supplierSvc.getOneSupplier(supplyNo);
				if (supplierVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/supplier/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)*************/
				req.setAttribute("supplierVO", supplierVO); // 嚙踝蕭w嚙踝蕭嚙碼嚙踝蕭supplierVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
				String url = "/back-end/supplier/listOneSupplier.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭嚙穀嚙踝蕭嚙� listOneSupplier.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 嚙諉佗蕭listAllSupplier.jsp嚙踝蕭嚙請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭****************************************/
				Integer supplyNo = Integer.valueOf(req.getParameter("supplyNo"));
				
				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�****************************************/
				SupplierService supplierSvc = new SupplierService();
				SupplierVO supplierVO = supplierSvc.getOneSupplier(supplyNo);
								
				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)************/
				req.setAttribute("supplierVO", supplierVO);         // 嚙踝蕭w嚙踝蕭嚙碼嚙踝蕭supplierVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
				String url = "/back-end/supplier/update_supplier_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭嚙穀嚙踝蕭嚙� update_supplier_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 嚙諉佗蕭update_supplier_input.jsp嚙踝蕭嚙請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭嚙羯嚙畿嚙緲**********************/
				Integer supplyNo = Integer.valueOf(req.getParameter("supplyNo").trim());
				
				String supplyName = req.getParameter("supplyName");
				String supplyNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (supplyName == null || supplyName.trim().length() == 0) {
					errorMsgs.add("廠商名稱: 請勿空白");
				} else if(!supplyName.trim().matches(supplyNameReg)) { //嚙瘡嚙磊嚙練嚙賠伐蕭嚙篁(嚙磕)嚙踝蕭雃嚙�(regular-expression)
					errorMsgs.add("\"廠商姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間\"");
	            }
				
				String supplyContact = req.getParameter("supplyContact").trim();
				if (supplyContact == null || supplyContact.trim().length() == 0) {
					errorMsgs.add("廠商聯絡人請勿空白");
				}	
				
				String supplyPhone = req.getParameter("supplyPhone");
				String supplyPhoneReg = "^[0-9]*$";
				if (supplyPhone == null || supplyPhone.trim().length() == 0) {
					errorMsgs.add("廠商電話: 請勿空白");
				} else if(!supplyPhone.trim().matches(supplyPhoneReg)) { //嚙瘡嚙磊嚙練嚙賠伐蕭嚙篁(嚙磕)嚙踝蕭雃嚙�(regular-expression)
					errorMsgs.add("廠商電話:只能是0-9的數字");
	            }


				String supplyAddress = req.getParameter("supplyAddress");
				String supplyAddressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
				if (supplyAddress == null || supplyAddress.trim().length() == 0) {
					errorMsgs.add("廠商地址: 只能是中、英文字母、數字和_ ");
				} 


				SupplierVO supplierVO = new SupplierVO();
				supplierVO.setSupplyNo(supplyNo);
				supplierVO.setSupplyName(supplyName);
				supplierVO.setSupplyContact(supplyContact);
				supplierVO.setSupplyPhone(supplyPhone);
				supplierVO.setSupplyAddress(supplyAddress);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("supplierVO", supplierVO); // 嚙緣嚙踝蕭嚙踝蕭J嚙賣式嚙踝蕭嚙羯嚙踝蕭supplierVO嚙踝蕭嚙踝蕭,嚙稽嚙編嚙皚req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/supplier/update_supplier_input.jsp");
					failureView.forward(req, res);
					return; //嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				/***************************2.嚙罷嚙締嚙論改蕭嚙踝蕭*****************************************/
				SupplierService supplierSvc = new SupplierService();
				supplierVO = supplierSvc.updateSupplier(supplyNo, supplyName, supplyContact, supplyPhone, supplyAddress);
				
				/***************************3.嚙論改完嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)*************/
				req.setAttribute("supplierVO", supplierVO); // 嚙踝蕭wupdate嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙確嚙踝蕭嚙踝蕭supplierVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
				String url = "/back-end/supplier/listOneSupplier.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙論改成嚙穀嚙踝蕭,嚙踝蕭嚙締istOneSupplier.jsp
				successView.forward(req, res);
		}

        	if ("insert".equals(action)) { // 嚙諉佗蕭addSupplier.jsp嚙踝蕭嚙請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭嚙羯嚙畿嚙緲*************************/
		
			String supplyName = req.getParameter("supplyName");
			String supplyNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (supplyName == null || supplyName.trim().length() == 0) {
				errorMsgs.add("廠商名稱: 請勿空白");
			} else if(!supplyName.trim().matches(supplyNameReg)) { //嚙瘡嚙磊嚙練嚙賠伐蕭嚙篁(嚙磕)嚙踝蕭雃嚙�(regular-expression)
				errorMsgs.add("\"廠商姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間\"");
            }
			
			String supplyContact = req.getParameter("supplyContact").trim();
			if (supplyContact == null || supplyContact.trim().length() == 0) {
				errorMsgs.add("廠商聯絡人請勿空白");
			}	
			
			String supplyPhone = req.getParameter("supplyPhone");
			String supplyPhoneReg = "^[0-9]*$";
			if (supplyPhone == null || supplyPhone.trim().length() == 0) {
				errorMsgs.add("廠商電話: 請勿空白");
			} else if(!supplyPhone.trim().matches(supplyPhoneReg)) { //嚙瘡嚙磊嚙練嚙賠伐蕭嚙篁(嚙磕)嚙踝蕭雃嚙�(regular-expression)
				errorMsgs.add("廠商電話:只能是0-9的數字");
            }


			String supplyAddress = req.getParameter("supplyAddress");
			String supplyAddressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
			if (supplyAddress == null || supplyAddress.trim().length() == 0) {
				errorMsgs.add("廠商地址: 只能是中、英文字母、數字和_ ");
			} 


			SupplierVO supplierVO = new SupplierVO();
			supplierVO.setSupplyName(supplyName);
			supplierVO.setSupplyContact(supplyContact);
			supplierVO.setSupplyPhone(supplyPhone);
			supplierVO.setSupplyAddress(supplyAddress);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("supplierVO", supplierVO); // 嚙緣嚙踝蕭嚙踝蕭J嚙賣式嚙踝蕭嚙羯嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙稽嚙編嚙皚req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/supplier/addSupplier.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.嚙罷嚙締嚙編嚙磕嚙踝蕭嚙�***************************************/
				SupplierService SupplierSvc = new SupplierService();
				supplierVO = SupplierSvc.addSupplier(supplyName, supplyContact, supplyPhone, supplyAddress);
				
				/***************************3.嚙編嚙磕嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)***********/
				String url = "/back-end/supplier/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙編嚙磕嚙踝蕭嚙穀嚙踝蕭嚙踝蕭嚙締istAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 嚙諉佗蕭listAllSupplier.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭***************************************/
				Integer supplyNo = Integer.valueOf(req.getParameter("supplyNo"));
				
				/***************************2.嚙罷嚙締嚙磋嚙踝蕭嚙踝蕭嚙�***************************************/
				SupplierService SupplierSvc = new SupplierService();
				SupplierSvc.deleteSupplier(supplyNo);
				
				/***************************3.嚙磋嚙踝蕭嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)***********/								
				String url = "/back-end/supplier/listAllSupplier.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙磋嚙踝蕭嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙稷嚙箴嚙碼嚙磋嚙踝蕭嚙踝蕭嚙諉瘀蕭嚙踝蕭嚙踝蕭
				successView.forward(req, res);
		}
	}
}
