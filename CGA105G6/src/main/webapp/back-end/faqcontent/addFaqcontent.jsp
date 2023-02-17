<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.faqcontent.model.*"%>
<!-- 0209版型已調 -->
<%
FAQContentVO faqContentVO = (FAQContentVO) request.getAttribute("faqContentVO");
%>
<%@ include file="/back-end/header.jsp"%>

<style>
.
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
	padding: 2px;
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
		<div class="col-xl-10 col-lg-12 col-md-9">
		<a href="<%=request.getContextPath()%>/back-end/faqcontent/select_page.jsp" class="btn btn-success">回首頁</a>
<p></p>
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h3 class="m-0 font-weight-bold text-primary">新增問題</h3>
				</div>
				<div class="card-body">
					<div class="text-left">
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/faqcontent.do" name="form1">
							<table class="table table-borderless">
								<thead>
								</thead>
								<tbody>
									<tr>
										<td>問題標題:</td>
										<td><input type="TEXT" name="faqContent" size="45"
											class="form-control form-control-user"
											placeholder="<%=(faqContentVO == null) ? "請輸入問題標題" : faqContentVO.getFaqContent()%>" />
										</td>
									</tr>
									<tr>
										<td>回應內容:</td>
										<td><input type="TEXT" name="ansContent" size="45"
											class="form-control form-control-user" style="height: 50px;"
											placeholder="<%=(faqContentVO == null) ? "請輸入問題回應" : faqContentVO.getAnsContent()%>" /></td>
									</tr>
									<tr>
										<td>關鍵字:</td>
										<td><input type="TEXT" name="fqKeyWord" size="45"
											class="form-control form-control-user"
											placeholder="<%=(faqContentVO == null) ? "請輸入問題關鍵字" : faqContentVO.getFqKeyWord()%>" /></td>
									</tr>
								</tbody>
							</table>
							<br>
							<div style="width: 100%; display: flex;">
								<div style="width: 85%"></div>
								<input type="hidden" name="action" value="insert"> <input
									type="submit" value="送出新增" class="btn btn-primary">
							</div>
						</FORM>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/back-end/footer.jsp"%>