<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderDetail.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%
List<OrderDetailVO> orderDetailVOList = (List<OrderDetailVO>) request.getAttribute("orderDetailVO");
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
			<h3>所有訂單明細資料 - listOneOrderDetail.jsp</h3>
			<h4>
				<a href="/CGA105G6/back-end/orderDetail/select_page.jsp">查詢訂單</a>
			</h4>
		</td>
	</tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>遊戲流水條碼</th>
		<th>遊戲名稱</th>
		<th>會員編號</th>
		<th>商品價格</th>
		<th>商品銷售數量</th>
		<th>商品狀態編號</th>
		<th>修改</th>
	</tr>

	<c:forEach var="orderDetailVO" items="<%=orderDetailVOList%>">

		<tr>
			<td>${orderDetailVO.orderNo}</td>
			<td>${orderDetailVO.serialNo}</td>
			<td>${orderDetailVO.shopVO.itemName}</td>
			<td>${orderDetailVO.memNo}</td>
			<td>${orderDetailVO.itemPrice}</td>
			<td>${orderDetailVO.itemSale}</td>
			<td><c:choose>
					<c:when test="${orderDetailVO.status == 0}">現貨</c:when>
					<c:when test="${orderDetailVO.status == 1}">二手</c:when>
					<c:when test="${orderDetailVO.status == 2}">預購</c:when>
				</c:choose></td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/OrderDetailServlet.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="orderNo" value="${orderDetailVO.orderNo}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<%@ include file="/back-end/footer.jsp"%>