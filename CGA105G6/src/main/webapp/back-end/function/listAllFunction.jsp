<%@page import="com.access.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.function.model.*"%>
<%@ page import="com.admin.model.*"%>
<jsp:useBean id="adminSvc" scope="page" class="com.admin.model.AdminService" />
<jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService" />
<%
AccessService accessSvc = new AccessService();
List<AccessVO> list = accessSvc.getAll();
pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>所有員工資料</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/back-end.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/back-endStyle.css">
<style>
  table {
	width: 800px;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 10%;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  
  tr:nth-child(even) {
	background-color: rgba(255,255,255,0.4);
}
  
    .btn-warning{
  	margin-top: 15px;
  }
  
  .btnTitle{
  	margin-top: 25px;
  }
  
  .btnSmall{
  	margin-top:0;
  }
</style>

</head>
<body>


		<section>
		<div class="btnTitle">
			<button onclick="location.href='<%=request.getContextPath()%>/back-end/access/select_page.jsp'" class="btn btn-primary btnIn">回員工權限管理首頁</button>
		</div>
		<div class="titleBlock">所有員工權限列表</div>
			<table>
	<tr>
		<th>員工姓名</th>
		<th>權限編號</th>
		<th colspan="2">更改資料</th>
	

	</tr>

	
		
	<c:forEach var="accessVO" items="${list}" >
	
		<tr>
			<td>[${accessVO.adminno}]${accessVO.adminVO.adminName}</td>
			<td>[${accessVO.adminFunction}]${accessVO.functionVO.adminFunctionName}</td>
			<td>
			  <FORM METHOD="post" ACTION="AccessServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="新增" class="btn btn-warning btnIn btnSmall">
			     <input type="hidden" name="adminno"  value="${accessVO.adminno}">
			     <input type="hidden" name="action"	value="go_Insert"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="AccessServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn btn-danger btnIn btnSmall">
			     <input type="hidden" name="adminFunction"  value="${accessVO.adminFunction}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
		</section>
	</main>


</body>