<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.function.model.*"%>
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
	<!-- ���Y -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">�v���d�߭���</h1>
	</div>
	<!-- �����϶� -->
	<div class="row">
		<div class="col-xl-3 col-lg-12 col-md-9">
			<a
				href="<%=request.getContextPath()%>/back-end/access/select_page1.jsp"
				class="btn btn-success">�^����</a>
			<p></p>
		</div>
		<div class="col-xl-12 col-lg-12 col-md-9">
			<div class="card shadow mb-10">
				<div class="card-header py-3">
					<h3 class="m-0 font-weight-bold text-center">��Ƭd��</h3>
				</div>
			
				<div class="card-body">
					<div class="text-center">
					<!-- ���~�ץ��T�� -->
						<c:if test="${not empty errorMsgs}">
							<ul class="text-center">
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red;font-weight:bold">${errorMsgs.name}</li>
									<li style="color: red;font-weight:bold">${errorMsgs.adminFunction}</li>
								</c:forEach>
							</ul>
						</c:if>
						<jsp:useBean id="adminSvc" scope="page"
							class="com.admin.model.AdminService" />
						<form method="post"
							action="<%=request.getContextPath()%>/access/access.do">
							<table class="table table table-borderless">
								<tr> 
									<td><b>��ܺ����޲z���m�W:</b>&nbsp;&nbsp;
									    <select size="1" name="adminName">
											<option value="0">--�п��--
												<c:forEach var="adminVO" items="${adminSvc.all}">
													<option value="${adminVO.adminName}">${adminVO.adminName}
												</c:forEach>
										</select> 
										<input type="submit" value="�e�X"  class="btn btn-primary btn-sm">
										<input type="hidden" name="action" value="getName_For_Display">
									</td>
								</tr>
							</table>
						</form>
						<jsp:useBean id="functionSvc" scope="page"
							class="com.function.model.FunctionService" />
						<form method="post"
							action="<%=request.getContextPath()%>/access/access.do">
							<table class="table table table-borderless">
								<tr>
									<td><b>����v���W��:</b>&nbsp;&nbsp; 
										<select size="1" name="adminFunction">
											<option value="0">--�п��--
												<c:forEach var="functionVO" items="${functionSvc.all}">
													<option value="${functionVO.adminFunction}">${functionVO.adminFunctionName}
												</c:forEach>
										</select> 
										<input type="submit" value="�e�X"  class="btn btn-primary btn-sm">
										<input type="hidden" name="action" value="getFunction_For_Display"></td>
								</tr>
							</table>
						</form>
						<hr>
						<div class="text-center">
							<a class="btn btn-link" href="<%=request.getContextPath()%>/back-end/access/listAllAuthority.jsp"
								role="button">�����޲z���v���C��</a> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
</div>

<%@ include file="/back-end/footer.jsp"%>