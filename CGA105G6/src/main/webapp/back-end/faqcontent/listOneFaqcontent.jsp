<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.faqcontent.model.*"%>
<%@ include file="/back-end/header.jsp"%>

<%
FAQContentVO faqContentVO = (FAQContentVO) request.getAttribute("faqContentVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>查詢結果</title>

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
	<!-- 標頭 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">FQ頁面問題管理</h1>

	</div>
	<!-- 中間區塊 -->
	<div class="row">
		<div class="col-xl-3 col-lg-12 col-md-9">
			<a
				href="<%=request.getContextPath()%>/back-end/faqcontent/select_page.jsp"
				class="btn btn-success">回首頁</a>
			<p></p>
		</div>
		<div class="col-xl-12 col-lg-12 col-md-9">
			<div class="card shadow mb-10">
				<div class="card-header py-3">
					<h3 class="m-0 font-weight-bold text-primary">查詢結果</h3>
				</div>
				<div class="card-body">
					<div class="text-left">
						<table class="table table table-bordered">
							<thead>
								<tr>
									<th>問題編號</th>
									<th>問題內容</th>
									<th>回應內容</th>
									<th>關鍵字</th>
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