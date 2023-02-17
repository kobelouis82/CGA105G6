<%@page import="com.admin.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String sessionId = (String) session.getAttribute("id");
%>
<%@ page import="java.util.*"%>
<%
AdminVO adminVO = (AdminVO) request.getAttribute("adminVO"); //EmpServlet.java(Concroller), 存入req的adminVO物件
%>
<%@ include file="/back-end/header.jsp"%>

<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">管理員資料</h1>
<div class="col-xl-3 col-lg-12 col-md-9">
	<a href="<%=request.getContextPath()%>/back-end/admin/select_page.jsp"
		class="btn btn-success">回到管理員資料查詢</a>
	<p></p>
</div>


<!-- DataTales Example -->
<div class="card shadow mb-4">
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%"
				cellspacing="0">
				<thead>
					<tr>
						<th>員工編號</th>
						<th>員工姓名</th>
						<th>職位</th>
						<th>電話</th>
						<th>信箱</th>
						<th>帳號</th>
						<th>密碼</th>
						<th>在職狀態</th>
						<th>照片</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=adminVO.getAdminNo()%></td>
						<td><%=adminVO.getAdminName()%></td>
						<td>${adminVO.titleVO.adminTitle}</td>
						<td><%=adminVO.getPhone()%></td>
						<td><%=adminVO.getMail()%></td>
						<td><%=adminVO.getAccount()%></td>
						<td><%=adminVO.getPassword()%></td>
						<td>${(adminVO.state==0)?'在職':'離職'}</td>
						<td><Img
							src="${pageContext.request.contextPath}/back-end/admin/admin.do?action=getPhoto&adminNo=<%=adminVO.getAdminNo()%>"
							width="100"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@ include file="/back-end/footer.jsp"%>
