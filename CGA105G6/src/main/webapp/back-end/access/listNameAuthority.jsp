<%@page import="java.util.jar.Attributes.Name"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.access.model.*"%>
<%@ page import="com.title.model.*"%>
<%@ include file="/back-end/header.jsp"%>

<style>
body {
	/* 	background-color: #4e5452; */
	color: #4e5452;
}

div.left {
	margin-top: 20px;
}

div.right {
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
}

a.content {
	color: #80c344;
	font-size: 0.6em;
}

a.content:hover {
	color: #4B7F52;
}

table {
	width: 700px;
	margin: 30px auto;
	/* 	border: 1px solid #4e5452; */
}

th, td {
	text-align: center;
	/* 	border: 1px solid #4e5452; */
	padding: 10px 15px;
}

td.function {
	text-align: justify;
}

input.change {
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	margin-top: 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}

input.change:hover {
	background-color: #e4f007;
	color: #80c344;
	cursor: pointer;
}

tr {
	/* 	border-top: 1px solid #eee; */
	border-bottom: 2px solid #eee;
}
</style>
<div class="container-fluid">
	<!-- 標頭 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">特定權限的網站管理員列表</h1>
	</div>
	<!-- 中間區塊 -->
	<div class="row">
		<div class="col-xl-3 col-lg-12 col-md-9">
			<a
				href="<%=request.getContextPath()%>/back-end/access/select_page1.jsp"
				class="btn btn-success">權限查詢首頁</a>
			<p></p>
		</div>
		<div class="col-xl-12 col-lg-12 col-md-9">
			<div class="card shadow mb-10">
				<div class="card-header py-3">
					<h3 class="m-0 font-weight-bold text-primary">資料列表:</h3>
				</div>
				<div class="card-body">
					<div class="text-center">
						<table class="table table table-borderless">
							<tr>
								<th>職稱</th>
								<th>姓名</th>
								<th>管理權限</th>
							</tr>
							<jsp:useBean id="adminSvc" scope="page"
								class="com.admin.model.AdminService" />
							<jsp:useBean id="functionSvc" scope="page"
								class="com.function.model.FunctionService" />
							<jsp:useBean id="accessSvc" scope="page"
								class="com.access.model.AccessService" />
							<c:forEach var="adminVO" items="${adminSvc.all}">
								<tr>
									<c:if test="${adminVO.adminName == param.adminName}">
										<td>${adminVO.titleVO.adminTitle}</td>
										<td>${adminVO.adminName}</td>
										<td class="function"><c:forEach var="functionVO"
												items="${functionSvc.all}" varStatus="nextLine">
												<input type="checkbox" name="function${nextLine.count}"
													${accessSvc.getOneAccess(adminVO.adminNo,functionVO.adminFunction).adminFunction == functionVO.adminFunction ? 'checked':''}
													disabled />
												<label for="function${nextLine.count}"
													${accessSvc.getOneAccess(adminVO.adminNo,functionVO.adminFunction).adminFunction == functionVO.adminFunction ? 'class=spotlight':''}>${functionVO.adminFunctionName}</label>
							${nextLine.count%3 == 0 ? '<br>':''}
						</c:forEach></td>
									</c:if>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<%@ include file="/back-end/footer.jsp"%>