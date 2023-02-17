<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%
OrderVO orderVO = (OrderVO) request.getAttribute("orderVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%-- --<%= shopVO==null %>--${shopVO.deptno}-- <!-- line 100 --> --%>
<!DOCTYPE html>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="order.do" name="form1">
		<table>
			<tr>
				<td>訂單編號:<font color=red><b>*</b></font></td>
				<td><%=orderVO.getOrderNo()%></td>
			</tr>
			<tr>
				<td>會員編號:<font color=red><b>*</b></font></td>
				<td><%=orderVO.getMemNo()%></td>
			</tr>
			<tr>
				<td>訂單成立時間:<font color=red><b>*</b></font></td>
				<td><%=orderVO.getOrderTime()%></td>
			</tr>
			<tr>
				<td>訂單金額:<font color=red><b>*</b></font></td>
				<td><%=orderVO.getOrderTotal()%></td>
			</tr>
			<tr>
				<td>訂單狀態:</td>
				<td><select size="1" name="orderState">
						<option value=0>未出貨</option>
						<option value=1>已出貨</option>
						<option value=2>已完成訂單</option>
						<option value=3>訂金已收</option>
						<option value=4>訂金未收</option>
						<option value=5>訂單作廢</option>
						<option value=6>退款</option>
				</select></td>
			</tr>

			<tr>
				<td>運送方式:</td>
				<td><select size="1" name="orderShip">
						<option value=0>超商</option>
						<option value=1>宅配</option>
						<option value=2>郵寄</option>
				</select></td>
			</tr>

			<tr>
				<td>付款方式:</td>
				<td><select size="1" name="orderPay">
						<option value=0>信用卡付款</option>
						<option value=1>匯款</option>
						<option value=2>貨到付款</option>
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> 
			<input type="hidden" name=orderNo value="<%=orderVO.getOrderNo()%>">
			<input type="hidden" name=memNo value="<%=orderVO.getMemNo()%>">
			<input type="hidden" name=orderTotal value="<%=orderVO.getOrderTotal()%>">
			<input type="hidden" name=orderTime value="<%=orderVO.getOrderTime()%>">
	
		<input type="submit" value="送出修改">
	</FORM>
<%@ include file="/back-end/footer.jsp"%>