<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.requisitions_detail.model.*"%>
<%@ include file="/back-end/header.jsp"%>

<%
	Requisitions_DetailVO requisitions_detailVO = (Requisitions_DetailVO ) request.getAttribute("Requisitions_DetailVO"); //Requisitions_DetailServlet.java(Concroller), 存入req的requisitions_detailVO物件
%>



<title>採購單明細資料</title>

<style>
		body {
			font-family: Arial, sans-serif;
		}

		#table-1 {
			width: 100%;
			margin: 20px 0;
			border-collapse: collapse;
		}

		#table-1 tr td {
			text-align: center;
			padding: 10px 0;
		}

		table {
			width: 100%;
			margin: 20px 0;
			border-collapse: collapse;
		}

		table tr th {
			background-color: lightgray;
			text-align: center;
			padding: 10px;
		}

		table tr td {
			text-align: center;
			padding: 10px;
			border: 1px solid lightgray;
		}

		form {
			display: inline-block;
			margin-bottom: 0;
		}

	</style>


<body>


<table id="table-1">
	<tr><td>
		 <h4><a href="<%=request.getContextPath()%>/back-end/requisitions/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>採購單明細編號</th>
		<th>採購單編號</th>
		<th>遊戲流水號</th>
		<th>遊戲狀態編號</th>
		<th>商品數量</th>
		<th>商品單價</th>
	</tr>
	<tr>
		<td><%=requisitions_detailVO.getReqDetailNo()%></td>		
		<td><%=requisitions_detailVO.getReqNo()%></td>
		<td><%=requisitions_detailVO.getSerialNo()%>-[<%=requisitions_detailVO.getShopVO().getItemName()%>]</td>
		<td><%=requisitions_detailVO.getStatus()%></td>
		<td><%=requisitions_detailVO.getQty()%></td>
		<td><%=requisitions_detailVO.getPrice()%></td>
	</tr>
</table>

</body>
 <%@ include file="/back-end/footer.jsp"%> 