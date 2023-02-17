<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.order.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
OrderVO orderVO = (OrderVO) request.getAttribute("orderVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
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
</head>

<table id="table-1">
	<tr>
		<td>
			<h3>單一訂單資料 - listOneOrder.jsp</h3>
			<h4>
				<a href="select_page.jsp">訂單查詢</a>
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

	<tr>
		<td><%=orderVO.getOrderNo()%></td>
		<td><%=orderVO.getMemNo()%></td>
		<td><%=orderVO.getOrderTime()%></td>
		<td><%=orderVO.getOrderTotal()%></td>
		<td><%=(orderVO.getOrderState() == 0) ? "未出貨"
		: (orderVO.getOrderState() == 1) ? "已出貨"
				: (orderVO.getOrderState() == 2) ? "已完成訂單"
						: (orderVO.getOrderState() == 3) ? "訂金已收"
								: (orderVO.getOrderState() == 4) ? "訂金未收"
										: (orderVO.getOrderState() == 5) ? "訂單作廢" : "退貨"%></td>
		<td><%=(orderVO.getOrderShip() == 0) ? "超商取貨" : ((orderVO.getOrderShip() == 1) ? "宅配" : "郵寄")%></td>
		<td><%=(orderVO.getOrderPay() == 0) ? "信用卡" : ((orderVO.getOrderPay() == 1) ? "匯款" : "貨到付款")%></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/order/order.do"
				style="margin-bottom: 0px;">
				<input type="submit" value="修改"> 
				<input type="hidden" name="orderNo" value="${orderVO.orderNo}">  
				<input type="hidden" name="action" value="getOne_For_Update">
			</FORM>
		</td>
		<td>
			<FORM METHOD="post"
				action="<%=request.getContextPath()%>/OrderDetailServlet.do">
				<input type="hidden" name="orderNo"
					value="<%=orderVO.getOrderNo()%>"> <input type="hidden"
					name="action" value="getOne_For_Display"> <input
					type="submit" value="查看">
			</FORM>
		</td>
</table>
<%@ include file="/back-end/footer.jsp"%>