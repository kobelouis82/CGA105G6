<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/back-end.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/static/css/back-endStyle.css">
<title>員工權限首頁</title>
<style>
section{
	text-align: center;
}
</style>

</head>
<body>
<%-- <nav><%@include file="/back-end/topNavbar.jsp"%></nav> --%>
<!-- 	<main> -->
<%-- 		<%@include file="/back-end/leftside.jsp"%> --%>
		<section>
		<div class="btnTitle">
			<button onclick="location.href='<%=request.getContextPath()%>/back-end/admin/listAllEmp.jsp'" class="btn btn-primary btnIn">回查詢員工資料</button>
		</div>
	<div class="titleBlock">員工權限管理首頁</div></br>	
		
  <font color="red">${errorMsgs.fail}</font>
  
  <jsp:useBean id="accessSvc" scope="page" class="com.access.model.AccessService" />
    <jsp:useBean id="functionSvc" scope="page" class="com.function.model.FunctionService" />
     <jsp:useBean id="adminSvc" scope="page" class="com.admin.model.AdminService" />
     	
     <FORM METHOD="post" ACTION="AccessServlet.do" >
       <b>選擇權限編號:</b>
       <select size="1" name="adminFunction">
         <c:forEach var="functionVO" items="${functionSvc.all}" > 
          <option value="${functionVO.adminFunction}">[${functionVO.adminFunction}]-${functionVO.adminFunctionName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_DisplayEffect">
       <input type="submit" value="送出" class="btn btn-info btnIn btnSmall">
    </FORM><br>
  
     <FORM METHOD="post" ACTION="AccessServlet.do" >
       <b>選擇員工名稱:</b>
       <select size="1" name="adminno">
        <c:forEach var="adminVO" items="${adminSvc.all}" > 
          <option value="${adminVO.adminno}">[${adminVO.adminno}]-${adminVO.adminName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出" class="btn btn-info btnIn btnSmall">
     </FORM><br>
					&ensp;&ensp;
		<button onclick="location.href='<%=request.getContextPath()%>/back-end/access/listAllAccess.jsp'" class="btn btn-dark btnL">查詢所有員工權限</button>	
		</section>
	</main>

</body>
</html>