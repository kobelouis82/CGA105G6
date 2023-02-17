<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.faqcontent.model.*"%>
<%@ include file="/back-end/header.jsp"%>


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

h3 {
	color: black;
}

h4 {
	color: blue;
	display: inline;
}

ul {
	list-style: none;
	padding: 0;
	margin: 0;
}

a, img {
	margin-right: 30px;
}

b {
	list-style: none;
	border-radius: 4px;
	margin-left: 150px;
	text-align: left;
}


p {
	list-style: none;
	padding: 0;
	margin-left: -20px;
}

input[type="text"] {
	padding: 6px;
	font-size: 14px;
	border-radius: 4px;
	border: 1px solid #ddd;
}

input[type="submit"] {
	background-color: #0077;
	color: #fff;
	padding: 6px 12px;
	border-radius: 4px;
	border: 0;
	cursor: pointer;
}

select {
	padding: 6px;
	font-size: 14px;
	border-radius: 4px;
	border: 1px solid #ddd;
}

.error {
	color: red;
	margin-bottom: 10px;
}
</style>


<div class="container-fluid">
	<!-- 標頭 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">常見問題編輯頁面</h1>
	</div>
	<!-- 中間區塊 -->
	<div class="row">
		<div class="col-xl-10 col-lg-12 col-md-9">
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<div class="text-left">
						<h3 class="m-0 font-weight-bold text-primary">問題查詢:</h3>
					</div>
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
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/faqcontent.do">
							<table class="table table-borderless">
								<thead>
								</thead>
								<tbody>
									<tr>
										<th></th>
										<td><b>輸入關鍵字 :</b> <input type="text" name="faq_Content">
											<input type="hidden" name="action"
											value="listFAQContents_ByCompositeQuery"> <input
											type="submit" value="送出"></td>
									</tr>
								</tbody>
							</table>
						</FORM>
						<jsp:useBean id="faqContentSvc" scope="page"
							class="com.faqcontent.model.FAQContentService" />
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/faqcontent.do">
							<table class="table table-borderless">
								<thead>
								</thead>
								<tbody>
									<tr>
										<th></th>
										<td><b>選擇關鍵字 :</b> <select size="1" name="faqNo">
												<c:forEach var="FAQContentVO" items="${faqContentSvc.all}">
													<option value="${FAQContentVO.faqNo}">${FAQContentVO.fqKeyWord}
												</c:forEach>
										</select> <input type="hidden" name="action" value="getOne_For_Display">
											<input type="submit" value="送出"></td>
									</tr>
								</tbody>
							</table>
						</FORM>
						<hr>
						<div class="text-center">
							<a class="btn btn-link"
								href="<%=request.getContextPath()%>/back-end/faqcontent/listAllFaqcontent.jsp"
								role="button">顯示所有常見問題</a> <a class="btn btn-link"
								href="<%=request.getContextPath()%>/back-end/faqcontent/addFaqcontent.jsp"
								role="button">新增一個新的問題</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- ==================== -->
<%@ include file="/back-end/footer.jsp"%>