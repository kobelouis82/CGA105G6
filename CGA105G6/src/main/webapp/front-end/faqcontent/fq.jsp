<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.faqcontent.model.*"%>
<%@include file="/front-end/header.jsp"%>

<%
FAQContentService faqcontentSvc = new FAQContentService();
List<FAQContentVO> list = faqcontentSvc.getAll();
pageContext.setAttribute("list", list);
%>

<link
	href="<%=request.getContextPath()%>/front-end/faqcontent/assets/fq.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

<body>
	<section id="main-content">
		<section class="wrapper">
			<h3 class="funcTitle02">
				<i></i> 常見問題
			</h3>

			<div class="row">
				<div class="col-md-12">
					<div class="content-panel">
						<div>
							<h4 class="funcTitle02">
								<i></i>       
							</h4>
							<h4 class="funcTitle02">
								<a href="<%=request.getContextPath()%>/front-end/main.jsp"><i
									class="fa-solid fa-circle-chevron-left">返回</i></a>
							</h4>
						</div>
						<hr>
						<table class="table table-striped table-advance table-hover"
							id="myTable">

							<thead>
								<tr>
									<th><i class="fa fa-solid fa-hashtag"></i> 問題編號</th>
									<th class="hidden-phone"><i class="fa fa-question-circle"></i>
										問題內容</th>
									<th><i class="fa fa-bookmark"></i> 回應內容</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<%@ include file="page1.file"%>
								<c:forEach var="faqContentVO" items="${list}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr>
										<td>${faqContentVO.faqNo}</td>
										<td>${faqContentVO.faqContent}</td>
										<td>${faqContentVO.ansContent}</td>
									</tr>
								</c:forEach>
								</tr>
							</tbody>
						</table>
						<%@ include file="page2.file"%>
					</div>
				</div>
			</div>
		</section>
	</section>


</body>

<%@include file="/front-end/footer.jsp"%>
