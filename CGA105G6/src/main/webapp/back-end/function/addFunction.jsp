<%@page import="com.access.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.function.model.*"%>
<jsp:useBean id="adminSvc" scope="page" class="com.admin.model.AdminService" />
<jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService"/>

<%
Integer adminno = (Integer)session.getAttribute("adminno");

AccessVO accessVO = (AccessVO) request.getAttribute("AccessVO");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/back-end.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/back-endStyle.css">
<title>權限新增資料 </title>

<style>
  table {
	width: 450px;
	margin-top: 1px;
	margin-bottom: 1px;
	margin-left: 30%;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }

</style>

</head>
<body>

<%-- <nav><%@include file="/back-end/topNavbar.jsp"%></nav> --%>
<!-- 	<main> -->
<%-- 		<%@include file="/back-end/leftside.jsp"%> --%>
		<section>
			<%-- 錯誤表列 --%>
			<div class="btnTitle">
			<button onclick="location.href='<%=request.getContextPath()%>/back-end/admin/listAlladmin.jsp'" class="btn btn-primary btnIn">回員工查詢</button>
		</div>
		<div class="btnTitle">
			<button onclick="location.href='<%=request.getContextPath()%>/back-end/access/select_page.jsp'" class="btn btn-primary btnIn">查詢權限</button>
		</div>
		<div class="titleBlock">新增員工權限</div>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/access/AccessServlet.do" name="form1">
<table>
	<tr>
		<td>員工姓名:</td>
		<td><input type="${accessVO.adminno ==null? 'TEXT':'hidden'}" name="adminno" size="45" 
			 value="${accessVO.adminno}"  />${accessVO.adminVO.adminName}</td>
	</tr>
	<tr>
		<td>權限:</td>
		
		<td>
		<select class="function" name="adminFunction">
		<c:forEach var="functionVO" items="${functionSvc.all}">
		<option value="${functionVO.adminFunction}">${functionVO.adminFunctionName}</option>
		</c:forEach>
		</select>
		</br><font color="red">${errorMsgs.adminFunction}</font>
		</td>
		
	</tr>


</table>
<br>
<div class="subBlock">
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" class="btn btn-success btnIn"></FORM>
</div>
		</section>
	</main>



</body>