<%@page import="com.admin.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ include file="/back-end/header.jsp"%>


<style>
ul, li {
	list-style: none;
}

.select {
	width: 210px;
	height: 30px;
	border-radius: 20px;
	text-align: center;
	border: none;
}

select:focus {
	border: 2px solid white;
}

input {
	border: none;
	border-radius: 20px;
}
</style>

<div class="container-fluid">
	<!-- 中間區塊 -->
	<div class="row">
		<div class="col-xl-12 col-lg-12 col-md-9">
			<div class="card shadow mb-10">
				<div class="card-header py-3">
					<h3 class="m-0 font-weight-bold text-center">管理員資料查詢</h3>
				</div>
				<div class="card-body">
					<div class="text-center">

						<!-- 錯誤修正訊息 -->
						<c:if test="${not empty errorMsgs}">
							<ul class="text-center">
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red;font-weight:bold">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

						<form METHOD="post" ACTION="admin.do">
							<table class="table table table-borderless">
								<tr>
									<td><b>輸入員工編號 (如1):</b> <input type="text" name="adminNo">
										<input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="送出" class="btn btn-dark btnIn"></td>
								</tr>
								<tr>
							</table>
						</form>

						<jsp:useBean id="adminSvc" scope="page"
							class="com.admin.model.AdminService" />
						<form METHOD="post" ACTION="admin.do">
							<table class="table table table-borderless">
								<tr>
									<td><b>選擇管理員編號：</b> <select size="1" name="adminNo"
										class="select">
											<c:forEach var="adminVO" items="${adminSvc.all}">
												<option value="${adminVO.adminNo}">${adminVO.adminNo}
											</c:forEach>
									</select> <input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="送出" class="btn btn-dark btnIn"></td>
								</tr>
							</table>
						</form>

						<form METHOD="post" ACTION="admin.do">
							<table class="table table table-borderless">
								<tr>
									<td><b>選擇管理員姓名：</b> <select size="1" name="adminNo"
										class="select">
											<c:forEach var="adminVO" items="${adminSvc.all}">
												<option value="${adminVO.adminNo}">${adminVO.adminName}
											</c:forEach>
									</select> <input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" value="送出" class="btn btn-dark btnIn"></td>
								</tr>
							</table>
						</form>
						<hr>
						<div class="text-center">
							<a class="btn btn-link"
								href="<%=request.getContextPath()%>/back-end/admin/listAllAdmin.jsp"
								role="button">查詢全管理員資料</a> <a class="btn btn-link"
								href="<%=request.getContextPath()%>/back-end/admin/addAdmin.jsp"
								role="button">加入新管理員</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<%@ include file="/back-end/footer.jsp"%>