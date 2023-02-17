<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%
OrderService orderSvc = new OrderService();
List<OrderVO> list = orderSvc.getAll();
pageContext.setAttribute("list", list);
%>

<style>
table#table-1 {
	background-color: darkgray;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

img {
	width: 150px;
	height: 150px;
}
</style>

<table id="table-1">
	<tr>
		<td>
			<h3>所有訂單資料 - listAllOrder.jsp</h3>
			<h4>
				<a href="select_page.jsp">查詢訂單</a>
			</h4>
		</td>
	</tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>訂單成立時間</th>
		<th>訂單金額</th>
		<th>訂單狀態</th>
		<th>運送方式</th>
		<th>付款方式</th>
		<th>修改</th>
		<th>訂單明細</th>
	</tr>
	<%@ include file="/back-end/page1.file"%>
	<c:forEach var="orderVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">

		<tr>
			<td>${orderVO.orderNo}</td>
			<td>${orderVO.memNo}</td>
			<td>${orderVO.orderTime}</td>
			<td>${orderVO.orderTotal}</td>
			<td><c:choose>
					<c:when test="${orderVO.orderState == 0}">未出貨</c:when>
					<c:when test="${orderVO.orderState == 1}">已出貨</c:when>
					<c:when test="${orderVO.orderState == 2}">已完成訂單</c:when>
					<c:when test="${orderVO.orderState == 3}">訂金已收</c:when>
					<c:when test="${orderVO.orderState == 4}">訂金未收</c:when>
					<c:when test="${orderVO.orderState == 5}">訂單作廢</c:when>
					<c:when test="${orderVO.orderState == 4}">已完成訂單</c:when>
					<c:when test="${orderVO.orderState == 6}">退貨</c:when>
				</c:choose></td>
			<td><c:choose>
					<c:when test="${orderVO.orderShip == 0}">超商取貨</c:when>
					<c:when test="${orderVO.orderShip == 1}">宅配</c:when>
					<c:when test="${orderVO.orderShip == 2}">郵寄</c:when>
				</c:choose></td>
			<td><c:choose>
					<c:when test="${orderVO.orderPay == 0}">信用卡</c:when>
					<c:when test="${orderVO.orderPay == 1}">匯款</c:when>
					<c:when test="${orderVO.orderPay == 2}">貨到付款</c:when>
				</c:choose></td>

			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/back-end/order/order.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="orderNo" value="${orderVO.orderNo}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
			<FORM METHOD="post"
				action="<%=request.getContextPath()%>/OrderDetailServlet.do">
				<input type="hidden" name="orderNo"
					value="${orderVO.orderNo}"> <input type="hidden"
					name="action" value="getOne_For_Display"> <input
					type="submit" value="查看">
			</FORM>
		</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/back-end/page2.file"%>
<%@ include file="/back-end/footer.jsp"%>