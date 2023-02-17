<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%
AdminVO adminVO = (AdminVO) request.getAttribute("adminVO"); //EmpServlet.java (Concroller) 存入req的adminVO物件 (包括幫忙取出的adminVO, 也包括輸入資料錯誤時的adminVO物件)
%>
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
	text-align: left;
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
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}

tr {
	/* 	border-top: 1px solid #eee; */
	border-bottom: 2px solid #eee;
}
</style>
</head>
<div class="container-fluid">
	<!-- 標頭 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">修改管理員資料</h1>
	</div>
	<!-- 中間區塊 -->
	<div class="row">
		<div class="col-xl-3 col-lg-12 col-md-9">
			<a
				href="<%=request.getContextPath()%>/back-end/admin/select_page.jsp"
				class="btn btn-success">回管理員資料查詢</a>
			<p></p>
		</div>
		<div class="col-xl-12 col-lg-12 col-md-9">
			<div class="card shadow mb-10">
				<div class="card-header py-3">
					<h3 class="m-0 font-weight-bold text-primary">管理員資料修改</h3>
				</div>
				<div class="card-body">
					<div class="text-left">
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<FORM METHOD="post" ACTION="admin.do" name="form1"
							enctype="multipart/form-data">
							<table class="table table table-borderless">
								<tr>
									<th>員工編號:<font color=red><b>*</b></font></th>
									<td><%=adminVO.getAdminNo()%></td>
								</tr>
								<tr>
									<th>員工姓名:</th>
									<td><input type="TEXT" name="adminName" size="45"
										value="<%=adminVO.getAdminName()%>" /></td>
								</tr>
								<tr>
									<th>電話:</th>
									<td><input type="TEXT" name="phone" size="45"
										value="<%=adminVO.getPhone()%>" /></td>
								</tr>
								<tr>
									<th>信箱:</th>
									<td><input type="TEXT" name="mail" size="45"
										value="<%=adminVO.getMail()%>" /></td>
								</tr>

								<tr>
									<th>帳號:</th>
									<td><input type="TEXT" name="account" size="45"
										value="<%=adminVO.getAccount()%>" /></td>
								</tr>
								<tr>
									<th>密碼:</th>
									<td><input type="TEXT" name="password" size="45"
										value="<%=adminVO.getPassword()%>" /></td>
								<tr>
									<th>照片:</th>
									<Img
										src="${pageContext.request.contextPath}/back-end/admin/admin.do?action=getPhoto&adminNo=<%=adminVO.getAdminNo()%>"
										width=200>
									<td><input type="file" name="upfile1" /></td>
								</tr>
								<tr>
									<th>在職狀態:</th>
									<td><select size="1" name="state" size="45">
											<option value="0" selected="selected">在職

											
											<option value="1" >離職

											
									</select>
								</tr>

								<jsp:useBean id="titleSvc" scope="page"
									class="com.title.model.TitleService" />
								<tr>
									<td>職位名稱:<font color=red><b>*</b></font></td>
									<td><select size="1" name="adminTitleNo">
											<c:forEach var="titleVO" items="${titleSvc.all}">
												<option value="${titleVO.adminTitleNo}"
													${(adminVO.adminTitleNo==titleVO.adminTitleNo)?'selected':'' }>${titleVO.adminTitle}
											</c:forEach>
									</select></td>
								</tr>

							</table>
							<br>
							 <div class="text-center">
							<input type="hidden" name="action" value="update">
							<input type="hidden" name="adminNo"
								value="<%=adminVO.getAdminNo()%>"> 
							<input type="submit" value="送出修改" class="btn btn-warning" >
							</div>
						</FORM>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/back-end/footer.jsp"%>