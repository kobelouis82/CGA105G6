<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forumArticleReport.model.*"%>
<%@ page import="java.util.*"%>
<%@ include file="/back-end/header.jsp"%>
<%
ForumArticleReportService forumArticleReportSvc = new ForumArticleReportService();
List<ForumArticleReportVO> list1 = forumArticleReportSvc.getAll();
List<ForumArticleReportVO> list2 = forumArticleReportSvc.getAll();
pageContext.setAttribute("list1", list1);
pageContext.setAttribute("list2", list2);
%>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>文章檢舉管理</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/static/css/backend.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
<style>
section {
	height: 100%;
	background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
	/* 		    background-color: #FFDEE9; */
	background-repeat: no-repeat;
	background-size: cover;
	justify-content: center;
}

.evenarticle {
	background-color: white;
	width: 100%;
	height: 40px;
	position: relative;
	left: 50%;
	transform: translate(-50%);
	display: flex;
	align-items: center;
	white-space: nowrap;
}

.title {
	height: 30px;
	/*             background-color: #FFC107; */
	position: relative;
	left: 50%;
	transform: translate(-50%);
	display: flex;
	align-items: center;
}

.repTime {
	width: 80px;
	font-size: 10px;
	color: rgb(105, 105, 105);
	text-align: center;
}

.author {
	width: 40px;
	text-align: center;
}

.atitle {
	width: 350px;
	text-align: center;
}

.sort {
	width: 80px;
	font-size: 8px;
	font-weight: 700;
	text-align: center;
	padding-left: 15px;
}

.status {
	width: 60px;
	font-weight: 700;
	text-align: center;
	padding-left: 15px;
}

.astitle {
	width: 500px;
}

.evenarticle:hover {
	background-color: rgb(255, 250, 197);
}

.btn-danger, .btn-success {
	height: 30px;
	white-space: nowrap;
	padding: 0 10px;
}

#container2 {
	margin-top: 5px;
	padding: 0;
}

.realStatus {
	color: red;
	font-weight: 700;
}

.slvStatus {
	color: green;
	font-weight: 700;
}

.repResult {
	width: 100px;
	padding: 0 10px;
	text-align: center;
}

.repEmp {
	width: 120px;
	padding: 0 10px;
	text-align: center;
}

.repBack {
	font-weight: 700;
}

.repScs {
	color: red;
	font-weight: 700;
}

.none {
	width: 60px;
}

.nav-link.inside {
	background-color: rgb(243, 243, 243) !important;
	color: black !important;
}

.nav-link.active {
	background-color: #000000 !important;
	font-weight: 700 !important;
	color: white !important;
}

.container {
	padding-top: 20px
}

.topTitle {
	font-size: 28px;
	font-weight: 700;
	text-align: center;
}

#home-tab, #profile-tab {
	border: none;
}

.accordion-button {
	position: relative;
	display: flex;
	align-items: center;
	width: 75% !important;
}

.accordion-header {
	display: flex;
	white-space: nowrap;
	justify-content: center;
	align-items: center;
}

#deleteArt {
	display: flex;
	height: 40px;
	justify-content: center;
	align-items: center;
}

.evenarticle:nth-child(even) {
	background-color: rgb(240, 240, 240);
}

/* 設定文章檢舉內容及文章 */
.insideTitle {
	background-color: black;
	color: white;
	font-weight: 700;
	display: flex;
	justify-content: center;
	align-items: center;
	padding-left: 10px;
}

.insideContent {
	display: flex;
	justify-content: center;
	padding-top: 10px;
	margin-bottom: 15px;
	padding-left: 10px;
}

.reporter {
	width: 30%;
}

.reason {
	width: 70%;
}

.upPic {
	width: 40px;
	height: 40px;
}

[aria-expanded="true"].accordion-button {
	background-color: hsla(168, 100%, 50%, 0.2);
	clip-path: inset(0px round 10px);
	animation: huerotate 6s infinite linear;
	filter: hue-rotate(360deg);
}

@
keyframes huerotate { 0% {
	filter: hue-rotate(0deg);
}
100
%
{
filter
:
hue-rotate(
360deg
);
}
}
</style>
</head>
<center>
	<div class="container">
		<h1 class="topTitle">討論區文章檢舉一覽</h1>
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item" role="presentation">
				<button class="nav-link  inside active" id="home-tab"
					data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button"
					role="tab" aria-controls="home-tab-pane" aria-selected="true">未處理</button>
			</li>
			<li class="nav-item" role="presentation">
				<button class="nav-link inside" id="profile-tab"
					data-bs-toggle="tab" data-bs-target="#profile-tab-pane"
					type="button" role="tab" aria-controls="profile-tab-pane"
					aria-selected="false">已處理</button>
			</li>

		</ul>
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="home-tab-pane"
				role="tabpanel" aria-labelledby="home-tab" tabindex="0">
				<div class="container" id="container2">
					<div class="title">
						&ensp;&ensp;&ensp;&ensp;
						<div class="status">狀態</div>
						<div class="sort">分類</div>
						<div class="atitle">標題</div>
						<div class="repTime">檢舉時間</div>
					</div>
					<c:forEach var="forumArticleReportVO" items="${list1}" varStatus="count">
					<c:if test="${forumArticleReportVO.articleReportState == 0}">
						<div class="accordion-item">
							<h2 class="accordion-header" id="heading${count.index}" />
							<button class="accordion-button" type="button"
								data-bs-toggle="collapse"
								data-bs-target="#collapse${count.index}" aria-expanded="false"
								aria-controls="collapse${count.index}">
								<div class="status">
									<c:if test="${forumArticleReportVO.articleReportState == 0}">
										<span class="realStatus">未處理</span>
									</c:if>
								</div>
								<div class="sort">${forumArticleReportVO.articleVO.forumVO.forumName}</div>
								<div class="atitle">${forumArticleReportVO.articleVO.title}</div>
								<div class="repTime"><fmt:formatDate value="${forumArticleReportVO.reportTime}"
									pattern="yyyy-MM-dd" />&emsp;</div>
								<div class="none"></div>
								<form method="post"
									action="<%=request.getContextPath()%>/ForumArticleReportServletforadmin">
									<input type="hidden" name="action" value="updateRes"> <input
										type="hidden" name="articleReportResult" value=0> <input
										type="hidden" name="articleReportState" value=1> <input
										type="hidden" name="articleReportNo"
										value="${forumArticleReportVO.articleReportNo}">
									<button type="submit" class="btn btn-success">駁回檢舉</button>
								</form>
								&nbsp;
								<form method="post" id="deleteArt"
									action="<%=request.getContextPath()%>/ForumArticleReportServletforadmin">
									<input type="hidden" name="action" value="hideArticle">
									<input type="hidden" name="articleReportResult" value="1">
									<input type="hidden" name="articleReportState" value="1">
									<input type="hidden" name="articleReportNo"
										value="${forumArticleReportVO.articleReportNo}"> <input
										type="hidden" name="articleNo"
										value="${forumArticleReportVO.articleNo}">
									<button type="submit" class="btn btn-danger">隱藏文章</button>
								</form>
						</div>
						</c:if>
						</button>
						</h2>
						<div id="collapse${count.index}"
							class="accordion-collapse collapse"
							aria-labelledby="heading${count.index}"
							data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<div class="insideTitle">
									<div class="reporter">檢舉人</div>
									<div class="reason">檢舉原因</div>
								</div>
								<div class="insideContent">
									<div class="reporter">${forumArticleReportVO.memVO.memAccount}</div>
									<div class="reason">${forumArticleReportVO.reportReason}</div>
								</div>

								<div class="insideTitle">
									<div class="reporter">文章作者</div>
									<div class="reason">文章內容</div>
								</div>
								<div class="insideContent">
									<div class="reporter">${forumArticleReportVO.articleVO.memVO.memAccount}</div>
									<div class="reason">${forumArticleReportVO.articleVO.content}</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="tab-pane fade" id="profile-tab-pane" role="tabpanel"
				aria-labelledby="profile-tab" tabindex="0">
				<div class="container" id="container2">
					<div class="title">
						<div class="status">狀態</div>
						<div class="sort">分類</div>
						<div class="atitle astitle">標題</div>
						<div class="repTime">檢舉時間</div>
						<div class="repResult">審核結果</div>
					</div>
					<c:forEach var="forumArticleReportVO" items="${list2}">
					<c:if test="${forumArticleReportVO.articleReportState == 1}">
						<div class="evenarticle">
							<div class="status">
								<c:if test="${forumArticleReportVO.articleReportState == 1}">
									<span class="slvStatus">已處理</span>
								</c:if>
							</div>
							<div class="sort">${forumArticleReportVO.articleVO.forumVO.forumName}</div>
							<div class="atitle astitle">${forumArticleReportVO.articleVO.title}</div>
							<div class="repTime"><fmt:formatDate value="${forumArticleReportVO.reportTime}"
									pattern="yyyy-MM-dd" />&emsp;</div>
							<div class="repResult">
								<c:if test="${forumArticleReportVO.articleReportResult == 0}">
									<span class="repBack">檢舉駁回</span>
								</c:if>
								<c:if test="${forumArticleReportVO.articleReportResult == 1}">
									<span class="repScs">隱藏</span>
								</c:if>
							</div>
						</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</center>
<%@ include file="/back-end/footer.jsp"%>