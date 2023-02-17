package com.requisitions.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.requisitions.model.RequisitionsService;
import com.requisitions.model.RequisitionsVO;
import com.requisitions_detail.model.Requisitions_DetailService;
import com.requisitions_detail.model.Requisitions_DetailVO;
@WebServlet("/requisitions.do")
public class RequisitionsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 嚙踝蕭謕蕭�嚙踐�select_page.jsp嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭嚙�

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭蹇蕭謕��此� - 嚙踝蕭謕蕭豲J嚙踝蕭謕��嚙踐�蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕�� **********************/
			String str = req.getParameter("reqNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入採購單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/requisitions/select_page.jsp");
				failureView.forward(req, res);
				return;// 嚙踝蕭謕滲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕��
			}

			Integer reqNo = null;
			try {
				reqNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("請輸入採購單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/requisitions/select_page.jsp");
				failureView.forward(req, res);
				return;// 嚙踝蕭謕滲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕��
			}

			/*************************** 2.嚙踝蕭謕�蕭嚙踐�蕭蹎蕭謕蕭��蕭謕蕭蹎∴蕭蹇嚙踝蕭謕 *****************************************/
			RequisitionsService requisitionsSvc = new RequisitionsService();
			RequisitionsVO requisitionsVO = requisitionsSvc.getOneRequisitions(reqNo);
			if (requisitionsVO == null) {
				errorMsgs.add("找不到該採購單");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/requisitions/select_page.jsp");
				failureView.forward(req, res);
				return;// 嚙踝蕭謕滲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕��
			}

			/*************************** 3.嚙踝蕭謕蕭��蕭謕蕭蹎�蕭�嚙踝蕭謕蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success view) *************/
			req.setAttribute("requisitionsVO", requisitionsVO); // 嚙踝蕭謕蕭豲嚙踐�嚙踝蕭謕蕭豲嚙踝蕭謕嚙踝蕭謕蕭豲requisitionsVO嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕�蕭嚙踐�蕭謇q
			String url = "/back-end/requisitions/listOneRequisitions.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭謕蕭豲嚙踝蕭謕蕭嚙踝蕭謕蕭豲嚙踝蕭謕 listOneRequisitions.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 嚙踝蕭謕蕭�嚙踐�listAllRequisitions.jsp嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭嚙�

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭蹇蕭謕��此� ****************************************/
			Integer reqNo = Integer.valueOf(req.getParameter("reqNo"));

			/*************************** 2.嚙踝蕭謕�蕭嚙踐�蕭蹎蕭謕蕭��蕭謕蕭蹎∴蕭蹇嚙踝蕭謕 ****************************************/
			RequisitionsService requisitionsSvc = new RequisitionsService();
			RequisitionsVO requisitionsVO = requisitionsSvc.getOneRequisitions(reqNo);

			/*************************** 3.嚙踝蕭謕蕭��蕭謕蕭蹎�蕭�嚙踝蕭謕蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success view) ************/
			req.setAttribute("requisitionsVO", requisitionsVO); // 嚙踝蕭謕蕭豲嚙踐�嚙踝蕭謕蕭豲嚙踝蕭謕嚙踝蕭謕蕭豲requisitionsVO嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕�蕭嚙踐�蕭謇q
			String url = "/back-end/requisitions/update_requisitions_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭謕蕭豲嚙踝蕭謕蕭嚙踝蕭謕蕭豲嚙踝蕭謕 update_requisitions_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 靘update_requisitions_input.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.��隢�� - 頛詨�撘�隤方��� **********************/
			Integer reqNo = Integer.valueOf(req.getParameter("reqNo"));

			Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));

			Integer supplyNo = Integer.valueOf(req.getParameter("supplyNo"));

			Byte reqStatus = Byte.valueOf(req.getParameter("reqStatus"));

			Byte reqPay = Byte.valueOf(req.getParameter("reqPay"));

			Integer totalPrice = Integer.valueOf(req.getParameter("totalPrice"));

			RequisitionsVO requisitionsVO = new RequisitionsVO();
			requisitionsVO.setReqNo(reqNo);
			requisitionsVO.setAdminNo(adminNo);
			requisitionsVO.setSupplyNo(supplyNo);
			requisitionsVO.setReqStatus(reqStatus);
			requisitionsVO.setReqPay(reqPay);
			requisitionsVO.setTotalPrice(totalPrice);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("requisitionsVO", requisitionsVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/requisitions/update_requisitions_input.jsp");
				failureView.forward(req, res);
				return; // 蝔�葉�
			}

			/*************************** 2.���耨�鞈�� *****************************************/
			RequisitionsService requisitionsSvc = new RequisitionsService();
			requisitionsVO = requisitionsSvc.updateRequisitions(reqNo, adminNo, supplyNo, reqStatus, reqPay,
					totalPrice);

			/*************************** 3.靽格摰��,皞��漱(Send the Success view) *************/
			req.setAttribute("requisitionsVO", requisitionsVO); // 鞈�澈update�����,甇�蝣箇��equisitionsVO�隞�,摮req
			String url = "/back-end/requisitions/listOneRequisitions.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 靽格�����,頧漱listOneRequisitions.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 嚙踝蕭謕蕭�嚙踐�listAllRequisitions.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭蹇蕭謕��此� ***************************************/
			Integer reqNo = Integer.valueOf(req.getParameter("reqNo"));

			/*************************** 2.嚙踝蕭謕�蕭嚙踐�蕭蹎蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕 ***************************************/
			RequisitionsService requisitionsSvc = new RequisitionsService();
			requisitionsSvc.deleteRequisitions(reqNo);

			/*************************** 3.嚙踝蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success view) ***********/
			String url = "/back-end/requisitions/listAllRequisitions.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭嚙踝蕭謕蕭豲,嚙踝蕭謕蕭豲嚙踝蕭謕嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭�嚙踝蕭�嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲
			successView.forward(req, res);
		}

	
		if ("insertReq".equals(action)) { // 嚙踝蕭謕蕭�嚙踐�addRequisitions.jsp嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭嚙�

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭蹇蕭謕��此� - 嚙踝蕭謕蕭豲J嚙踝蕭謕��嚙踐�蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕�� *************************/
					//嚙踝蕭謕�蕭嚙踐�嚙踝蕭��蕭謕蕭豲
			Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));

			Integer supplyNo = Integer.valueOf(req.getParameter("supplyNo"));

			Byte reqStatus = Byte.valueOf(req.getParameter("reqStatus"));

			Byte reqPay = Byte.valueOf(req.getParameter("reqPay"));

			Integer totalPrice = Integer.valueOf(req.getParameter("totalPrice"));

			RequisitionsVO requisitionsVO = new RequisitionsVO();
			Requisitions_DetailVO requisitions_detailVO = new Requisitions_DetailVO();
			requisitionsVO.setAdminNo(adminNo);
			requisitionsVO.setSupplyNo(supplyNo);
			requisitionsVO.setReqStatus(reqStatus);
			requisitionsVO.setReqPay(reqPay);
			requisitionsVO.setTotalPrice(totalPrice);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("requisitionsVO", requisitionsVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/requisitions/addRequisitions.jsp");
				failureView.forward(req, res);
				return; // 嚙踝蕭謕滲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕��
			}
			
			/*************************** 2.嚙踝蕭謕�蕭嚙踐�蕭蹎蕭謕�蕭嚙踐�蕭��蕭謕蕭豲嚙踝蕭謕 ***************************************/
			//嚙踝蕭謕�蕭嚙踐�蕭豲嚙踝蕭謕蕭豲嚙踝蕭蹎蕭謕蕭豲嚙踝蕭謕
			
			RequisitionsService requisitionsSvc = new RequisitionsService();
			int reqId = requisitionsSvc.addReq(requisitionsVO);
			
//			String[] reqDetailNos = req.getParameterValues("reqDetailNo");
			String[] serialNos = req.getParameterValues("serialNo");
			String[] qtys= req.getParameterValues("qty");
			String[] prices = req.getParameterValues("price");
			
			for (int i = 0; i < serialNos.length; i++) {
//			Integer reqDetailNo = Integer.valueOf(reqDetailNos[i]);
			Integer serialNo = Integer.valueOf(serialNos[i].trim());
			Byte status = Byte.valueOf(req.getParameter("status"));
			Integer qty = Integer.valueOf(qtys[i].trim());
			Integer price = Integer.valueOf(prices[i].trim());
			
			Requisitions_DetailService requisitions_detailService = new Requisitions_DetailService();
			requisitions_detailVO = requisitions_detailService.addRequisitions_Detail(reqId, serialNo,status,qty, price);	
			requisitions_detailVO.setReqNo(reqId);
			requisitions_detailVO.setSerialNo(serialNo);
			requisitions_detailVO.setStatus(status);
			requisitions_detailVO.setQty(qty);
			requisitions_detailVO.setPrice(price);
	
			}
			
			/*************************** 3.嚙踝蕭謕�蕭嚙踐�蕭��蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success view) ***********/
			String url = "/back-end/requisitions/listAllRequisitions.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭謕�蕭嚙踐�蕭��蕭謕蕭豲嚙踝蕭謕蕭嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭赯tAllRequisitions.jsp
			successView.forward(req, res);
		}
	
	
		if ("getReq_By_Admin".equals(action)) { // ����select_page.jsp��������
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.�����������姜瞏 - ���J��都撘�������劑����歇**********************/
			String str = req.getParameter("adminNo");  //����req.getParameter���o"reqDetailNo"��姜�撠��������楊��悌str���
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入管理員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/requisitionsselect_page.jsp");
				failureView.forward(req, res);
				return;//��溯��������酉
			}
			
			Integer adminNo = null;
			try {
				adminNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("�����������������都撘�������Ⅱ");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/requisitions/select_page.jsp");
				failureView.forward(req, res);
				return;//��溯��������酉
			}
			
			/***************************2.��蔆����������蕭*****************************************/
			RequisitionsService requisitionsService = new RequisitionsService();
			List<RequisitionsVO> requisitionsVO = requisitionsService.getOneRequisitionsByAdmin(adminNo);

			
			if (requisitionsVO == null) {
				errorMsgs.add("���������蕭");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/requisitions_detail/select_page.jsp");
				failureView.forward(req, res);
				return;//��溯��������酉
			}
			
			/***************************3.���������,���������蕭(Send the Success view)*************/
			req.setAttribute("RequisitionsVO", requisitionsVO); // ����w�����Ⅳ���Requisitions_DetailVO������,��楊���eq
			String url = "/back-end/requisitions/getReqByAdmin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �����������蕭 listOneRequisitions_Detail.jsp
			successView.forward(req, res);
			}
}
}