<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.faqcontent.model.*"%>
<%@ include file="/back-end/header.jsp"%>

<%
FAQContentVO faqContentVO = (FAQContentVO) request.getAttribute("faqContentVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�d�ߵ��G</title>

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
	width: 600px;
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
						<table class="table table table-bordered">
							<thead>
								<tr>
									<th>���D�s��</th>
									<th>���D���e</th>
									<th>�^�����e</th>
									<th>����r</th>
							</thead>
							<tr>
								<td><%=faqContentVO.getFaqNo()%></td>
								<td><%=faqContentVO.getFaqContent()%></td>
								<td><%=faqContentVO.getAnsContent()%></td>
								<td><%=faqContentVO.getFqKeyWord()%></td>
							</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/back-end/footer.jsp"%>