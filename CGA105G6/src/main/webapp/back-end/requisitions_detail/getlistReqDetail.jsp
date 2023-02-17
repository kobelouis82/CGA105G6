<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.requisitions_detail.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ include file="/back-end/header.jsp"%>


<head>
<title>採購單列表</title>
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

		input[type="submit"] {
			background-color: lightgray;
			border: none;
			padding: 5px 10px;
			border-radius: 5px;
			cursor: pointer;
		}

		input[type="submit"]:hover {
			background-color: gray;
		}
	</style>

</head>
<body>

<table id="table-1">
	<tr><td>
		 <h4><a href="<%=request.getContextPath()%>/back-end/requisitions/listAllRequisitions.jsp">回採購單列表</a></h4>
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
		<th>修改明細</th>
		<th>刪除明細</th>
	</tr>
		
		
		<c:forEach var="requisitions_detailVO" items="${Requisitions_DetailVO}">
	<tr>
		<td>${requisitions_detailVO.reqDetailNo}</td>
		<td>${requisitions_detailVO.reqNo}</td>
		<td>${requisitions_detailVO.serialNo}-[${requisitions_detailVO.shopVO.itemName}]</td>
				<c:choose>
					<c:when  test="${requisitions_detailVO.status==0}">
						<td>現貨</td>
					</c:when>
					<c:when  test="${requisitions_detailVO.status==1}">
						<td>預購</td>
					</c:when>
				</c:choose>
		<td>${requisitions_detailVO.qty}</td>
		<td>${requisitions_detailVO.price}</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/requisitions_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="reqDetailNo"  value="${requisitions_detailVO.reqDetailNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/requisitions_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="reqDetailNo"  value="${requisitions_detailVO.reqDetailNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
		</td>
	</tr>
		</c:forEach>
</table>
</body>
 <%@ include file="/back-end/footer.jsp"%> 