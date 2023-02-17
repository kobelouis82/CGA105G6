<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.requisitions_detail.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	Requisitions_DetailService requisitions_detailSvc = new Requisitions_DetailService();
    List<Requisitions_DetailVO> list = requisitions_detailSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<title>�Ҧ����ʳ����</title>

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

<h4><a href="<%=request.getContextPath() %>/back-end/requisitions/select_page.jsp">�^����</a></h4>

<table>
	<tr>
		<th>���ʳ���ӽs��</th>
		<th>���ʳ�s��</th>
		<th>�C���y����</th>
		<th>�C�����A�s��</th>
		<th>�ӫ~�ƶq</th>
		<th>�ӫ~���</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	
	<c:forEach var="requisitions_detailVO" items="${list}">
		
		<tr>
			<td>${requisitions_detailVO.reqDetailNo}</td>		
			<td>${requisitions_detailVO.reqNo}</td>
			<td>${requisitions_detailVO.serialNo}-[${requisitions_detailVO.shopVO.itemName}]</td>
				<c:choose>
					<c:when  test="${requisitions_detailVO.status==0}">
						<td>�{�f</td>
					</c:when>
					<c:when  test="${requisitions_detailVO.status==1}">
						<td>�w��</td>
					</c:when>
				</c:choose>
			<td>${requisitions_detailVO.qty}</td>
			<td>${requisitions_detailVO.price}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/requisitions_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="reqDetailNo"  value="${requisitions_detailVO.reqDetailNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/requisitions_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="reqDetailNo"  value="${requisitions_detailVO.reqDetailNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

 <%@ include file="/back-end/footer.jsp"%>