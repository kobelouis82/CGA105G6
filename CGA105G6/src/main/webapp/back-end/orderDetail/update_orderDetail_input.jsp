<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.orderDetail.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%
OrderDetailVO orderDetailVO = (OrderDetailVO) request.getAttribute("orderDetailVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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

<FORM METHOD="post"
	ACTION="<%=request.getContextPath()%>/OrderDetailServlet.do"
	name="form1">
	<table>
		<tr>
			<td>訂單編號:<font color=red><b>*</b></font></td>
			<td><%=orderDetailVO.getOrderNo()%></td>
		</tr>
		<tr>
			<td>遊戲編號:<font color=red><b>*</b></font></td>
			<td><%=orderDetailVO.getSerialNo()%></td>
		</tr>
		<tr>
			<td>會員編號:<font color=red><b>*</b></font></td>
			<td><%=orderDetailVO.getMemNo()%></td>
		</tr>
		<tr>
			<td>商品價格:<font color=red><b>*</b></font></td>
			<td><%=orderDetailVO.getItemPrice()%></td>
		</tr>
		<tr>
			<td>商品數量:<font color=red><b>*</b></font></td>
			<td><%=orderDetailVO.getItemSale()%></td>
		</tr>
		<tr>
			<td>商品狀態:</td>
			<td><select size="1" name="status">
					<option value=0>現貨</option>
					<option value=1>二手</option>
					<option value=2>預購</option>
			</select></td>
		</tr>

	</table>
	<br> <input type="hidden" name="action" value="update">
	
		<input type="hidden" name=orderNo value="<%=orderDetailVO.getOrderNo()%>">
	<input type="submit" value="送出修改">
</FORM>
<%@ include file="/back-end/footer.jsp"%>