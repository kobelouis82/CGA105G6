<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.requisitions.model.*"%>
<%@ page import="com.supplier.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/back-end/header.jsp"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

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
		 <h3>���ʳ��ƦC��</h3>
		 <h4><a href="<%=request.getContextPath() %>/back-end/requisitions/select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���ʳ�s��</th>
		<th>�޲z���s���Ωm�W</th>
		<th>�t�ӽs���μt�ӦW��</th>
		<th>���ʮɶ�</th>
		<th>���ʳ檬�A</th>
		<th>�I�ڤ覡</th>
		<th>�`��</th>
		<th>����</th>
		<th>�ק�</th>
	</tr>
	<c:forEach var="requisitionsVO" items="${list}">
		
		<tr>
			<td>${requisitionsVO.reqNo}</td>
			<td>${requisitionsVO.adminNo}-[${requisitionsVO.adminVO.adminName}]</td>
			<td>${requisitionsVO.supplyNo}-[${requisitionsVO.supplierVO.supplyName}]</td>
			<td><fmt:formatDate value="${requisitionsVO.getReqDate()}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<c:choose>
					<c:when  test="${requisitionsVO.reqStatus==0}">
						<td>���e�X</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqStatus==1}">
						<td>�ݼf��</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqStatus==2}">
						<td>�w�֭�</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqStatus==3}">
						<td>�w�i�f</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqStatus==4}">
						<td>�w�I��</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqStatus==5}">
						<td>����</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqStatus==6}">
						<td>�@�o</td>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when  test="${requisitionsVO.reqPay==0}">
						<td>�{��</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqPay==1}">
						<td>�״�</td>
					</c:when>
					<c:when  test="${requisitionsVO.reqPay==2}">
						<td>�H�Υd</td>
					</c:when>
				</c:choose>
			<td>${requisitionsVO.totalPrice}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/requisitions_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="����">
			     <input type="hidden" name="reqNo"  value="${requisitionsVO.reqNo}">
			     <input type="hidden" name="action" value="getOne_For_Req"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/requisitions.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="reqNo"  value="${requisitionsVO.reqNo}">
			     <input type="hidden" name="action" value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
<%@ include file="/back-end/footer.jsp"%>