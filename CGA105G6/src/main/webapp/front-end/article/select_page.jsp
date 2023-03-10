<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
ForumArticleService articleSvc = new ForumArticleService();
List<ForumArticleVO> list = articleSvc.getAll();
pageContext.setAttribute("list", list);
%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<!-- import icon -->
<script src="https://kit.fontawesome.com/b5ef6b60f3.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.1.js"
	integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
	crossorigin="anonymous">
</script>

<%@ include file="/front-end/header.jsp"%>

<style>
.styled-table {
	margin-left: auto;
	margin-right: auto;
	border-collapse: collapse;
	margin: auto;
	font-size: 0.9em;
	font-family: sans-serif;
	min-width: 400px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

.styled-table thead tr {
	background-color: #212529;
	color: #ffffff;
	text-align: left;
}

.styled-table th, .styled-table td {
	padding: 12px 10px;
}

.styled-table tbody tr {
	border-bottom: 1px solid #dddddd;
}

.styled-table tbody tr:nth-of-type(even) {
	background-color: #f3f3f3;
}

.styled-table tbody tr:last-of-type {
	border-bottom: 2px solid #212529;
}

.styled-table tbody tr.active-row {
	font-weight: bold;
	color: #212529;
}

table#table-1 {
	background-color: #212529;
	text-align: center;
	margin-left: auto;
	margin-right: auto;
	width: 1000px;
	margin-top: 20px;
	margin-bottom: 5px;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h3 {
	color: black;
	font-weight: 700 !important;
}

h4 {
	color: blue;
	display: inline;
}


table {
	margin-left: auto;
	margin-right: auto;
	width: 80%;
	margin-top: 5px;
	margin-bottom: 5px;
}

th, td {
	padding: 5px;
	text-align: center;
}

.btnIn {
	border-radius: 20px !important;
}

.thTitle {
	background-color: #B5FFFC !important;
	border: none;
}

.btnBlock {
	margin-top: 30px;
	display: flex;
	align-items: center;
	justify-content: center;
}

section {
	height: 100%;
	background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
	background-color: #FFDEE9;
	background-repeat: no-repeat;
	background-size: cover;
}
.atitle_but{
	border : none;
	background : none;
}
.atitle_but:hover{
	border-bottom : blue 1px solid;
	color : blue ;
	
}

</style>

<center>
            <div class="container">
                <div class="row">
                <div class="col-md-3"></div>
                    <div class="col-md-6 mt-5">
                    	<div class="text-center">       
                        <div class="searchBlock">
							<form method="post" action="<%=request.getContextPath()%>/ForumArticleServlet" name="form2">
							       <table class="table table-borderless">
			<!-- 			<div class="sortSearch"> -->
			<!-- 				?????? <select name="forumNo" class="select"> -->
			<%-- 					<option value="" ${(memVO.memAccess=="")? 'selected':'' }>?????????</option> --%>
			<%-- 					<option value="1" ${(memVO.memAccess==0)? 'select':'' }></option> --%>
			<%-- 					<option value="2" ${(memVO.memAccess==1)? 'select':'' }></option> --%>
			<%-- 					<option value="3" ${(memVO.memAccess==2)? 'select':'' }></option> --%>
			<!-- 				</select> -->
			<!-- 			</div> -->
								<tr>
								<th>??????????????????????????????</th>
								<td><input type="text" name="title" class="searchInput" value="" style="background-color:#F5F5F5;border-color:#F5F5F5" size="30"></td>
								<td><input type="hidden" name="action" value="ArtSearch"></td>
								<td><input type="submit" class="btn btn-success btnSmall" value="??????"></td>
								</tr>
								</table>
							</form>
                    	</div>
                	</div>
				</div>
			</div>
			<div class="col-md-4">	
		<form
			action="<%=request.getContextPath()%>/front-end/article/addArticle.jsp">
			<button class="btn btn-warning text-nowrap act" type="submit">????????????</button>
		</form>
	</div>
	<div class="col-md-12">
	<table class="styled-table">
		<thead>
			<tr>
				<th>??????</th>
				<th>??????</th>
				<th>??????</th>
				<th>????????????</th>
			</tr>
		</thead>
		<tbody>
			<%@ include file="page1.file"%>
			<jsp:useBean id="forumSvc" scope="page" class="com.forum.model.ForumService" />
			<c:forEach var="forumArticleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr class="active-row" align='center' valign='middle'>
					<td>???${forumArticleVO.forumVO.forumName}???</td>
					<td>
						<form method="post" action="<%=request.getContextPath()%>/ForumArticleServlet">
							<input type="hidden" name="articleNo" value="${forumArticleVO.articleNo}"> 
							<input type="hidden" name="action" value="getOne_For_Display">
							<input class="atitle_but" type="submit" value="${forumArticleVO.title}">
						</form>
					</td>
					<td>${forumArticleVO.memVO.memName}</td>
					<td><fmt:formatDate value="${forumArticleVO.publishTime}"
								pattern="yyyy-MM-dd" />&emsp;</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>
	<%@ include file="page2.file"%>
</center>
<%@ include file="/front-end/footer.jsp"%>