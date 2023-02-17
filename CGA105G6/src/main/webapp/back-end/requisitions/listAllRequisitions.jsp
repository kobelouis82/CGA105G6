<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.requisitions.model.*"%>
<%@ page import="com.supplier.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/back-end/header.jsp"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	RequisitionsService requisitionsSvc = new RequisitionsService();
    List<RequisitionsVO> list = requisitionsSvc.getAll();
    pageContext.setAttribute("list",list);
%>

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


<body>

<table id="table-1">
	<tr><td>
		 <h3>採購單資料列表</h3>
		 <h4><a href="<%=request.getContextPath() %>/back-end/requisitions/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>採購單編號</th>
		<th>管理員編號及姓名</th>
		<th>廠商編號及廠商名稱</th>
		<th>採購時間</th>
		<th>採購單狀態</th>
		<th>付款方式</th>
		<th>總價</th>
		<th>明細</th>
		<th>修改</th>
	</tr>
	<c:forEach var="requisitionsVO" items="${list}">
		
		<tr>
			<td>${requisitionsVO.reqNo}</td>
			<td>${requisitionsVO.adminNo}-[${requisitionsVO.adminVO.adminName}]</td>
			<td>${requisitionsVO.supplyNo}-[${requisitionsVO.supplierVO.supplyName}]</td>
			<td><fmt:formatDate value="${requisitionsVO.getReqDate()}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<c:choose>
					<c:when  test="${requisitionsVO.reqStatus==0}">
						<td>未送出</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqStatus==1}">
						<td>待審核</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqStatus==2}">
						<td>已核准</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqStatus==3}">
						<td>已進貨</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqStatus==4}">
						<td>已付款</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqStatus==5}">
						<td>結案</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqStatus==6}">
						<td>作廢</td>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when  test="${requisitionsVO.reqPay==0}">
						<td>現金</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqPay==1}">
						<td>匯款</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqPay==2}">
						<td>信用卡</td>
					</c:when>
				</c:choose>
			<td>${requisitionsVO.totalPrice}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/requisitions_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="明細">
			     <input type="hidden" name="reqNo"  value="${requisitionsVO.reqNo}">
			     <input type="hidden" name="action" value="getOne_For_Req"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/requisitions.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="reqNo"  value="${requisitionsVO.reqNo}">
			     <input type="hidden" name="action" value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
<%@ include file="/back-end/footer.jsp"%>