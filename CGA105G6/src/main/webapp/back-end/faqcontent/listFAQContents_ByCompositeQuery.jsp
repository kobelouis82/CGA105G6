<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.faqcontent.model.*"%>
<%@ include file="/back-end/header.jsp"%>

<%
FAQContentService faqcontentSvc = new FAQContentService();
List<FAQContentVO> list = faqcontentSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>�ƦX�d��</title>

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

body {
	color: #333;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
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
</style>

</head>

<div class="container-fluid">
	<!-- ���Y -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">FQ�������D�޲z</h1>

	</div>
	<!-- �����϶� -->
	<div class="row">
		<div class="col-xl-3 col-lg-12 col-md-9">
			<a
				href="<%=request.getContextPath()%>/back-end/faqcontent/select_page.jsp"
				class="btn btn-success">�^����</a>
			<p></p>
		</div>
		<div class="col-xl-12 col-lg-12 col-md-9">
			<div class="card shadow mb-10">
				<div class="card-header py-3">
					<h3 class="m-0 font-weight-bold text-primary">�d�ߵ��G</h3>
				</div>
				<div class="card-body">
					<div class="text-left">


						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/faqcontent.do" name="form1">
							<table class="table table table-bordered">
								<thead>
									<tr>
										<th>���D�s��</th>
										<th>���D���e</th>
										<th>�^�����e</th>
										<th>����r���e</th>
									</tr>
								</thead>
								<%@ include file="page1.file"%>

								<c:forEach var="faqContentVO"
									items="${listFAQContents_ByCompositeQuery}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

									<tr>
										<td>${faqContentVO.faqNo}</td>
										<td>${faqContentVO.faqContent}</td>
										<td>${faqContentVO.ansContent}</td>
										<td>${faqContentVO.fqKeyWord}</td>


									</tr>
									</tbody>
								</c:forEach>
							</table>
							<br>
						</FORM>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="page2.file"%>

<%@ include file="/back-end/footer.jsp"%>