<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.faqcontent.model.*"%>
<%@ include file="/back-end/header.jsp"%>

<%
FAQContentVO faqContentVO = (FAQContentVO) request.getAttribute("faqContentVO");
//EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���D��ƭק�</title>

<style>
table#table-1 {
	background-color: #0077;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

input[type="text"] {
	padding: 6px;
	font-size: 14px;
	border-radius: 4px;
	border: 1px solid #ddd;
	margin-bottom: 10px;
}

input[type="submit"] {
	background-color: #0077;
	color: #fff;
	padding: 6px 12px;
	border-radius: 4px;
	border: 0;
	cursor: pointer;
}

h3 {black;
	
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
	color: black;
}
</style>

</head>
<div class="container-fluid">
	<!-- ���Y -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">���D��ƭק�</h1>
	</div>
	<!-- �����϶� -->
	<div class="row">
		<div class="col-xl-10 col-lg-12 col-md-9">
			<a
				href="<%=request.getContextPath()%>/back-end/faqcontent/select_page.jsp"
				class="btn btn-success">�^����</a>
			<p></p>
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h3 class="m-0 font-weight-bold text-primary">���D��ƭק�</h3>
				</div>
				<div class="card-body">
					<div class="text-left">


						<%-- ���~��C --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">�Эץ��H�U���~:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/faqcontent.do"
							name="form1">
							<table>
								
								<tr>
									<td>���D�s��:<font color=red><b>*</b></font></td>
									<td><%=faqContentVO.getFaqNo()%></td>
								</tr>
								<tr>
									<td>���D���e:</td>
									<td><input type="TEXT" name="faqContent" size="45"
									class="form-control form-control-user"
										placeholder="<%=faqContentVO.getFaqContent()%>" /></td>
								</tr>
								<tr>
									<td>�^�����e:</td>
									<td><input type="TEXT" name="ansContent" size="45"
									class="form-control form-control-user"
										placeholder="<%=faqContentVO.getAnsContent()%>" /></td>
								</tr>
								<tr>
									<td>����r:</td>
									<td><input type="TEXT" name="fqKeyWord" size="45"
									class="form-control form-control-user"
										placeholder="<%=faqContentVO.getFqKeyWord()%>" /></td>
								</tr>
							</table>
							<br> <input type="submit" value="�e�X�ק�"> <input
								type="hidden" name="faqNo" value="${faqContentVO.faqNo}">
							<input type="hidden" name="action" value="update">
						</FORM>

						</script>
</html>