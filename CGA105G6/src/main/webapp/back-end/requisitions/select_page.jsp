<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>

<%@ include file="/back-end/header.jsp"%>

<title>採購單功能管理</title>

<style>
		body {
			font-family: Arial, sans-serif;
		}

		#table-1 {
			width: 100%;
			margin: 20px 0;
			border-collapse: collapse;
		}

		#table-1 tr td {
			text-align: center;
			padding: 10px 0;
		}

		table {
			width: 100%;
			margin: 20px 0;
			border-collapse: collapse;
		}

		table tr th {
			background-color: lightgray;
			text-align: center;
			padding: 10px;
		}

		table tr td {
			text-align: center;
			padding: 10px;
			border: 1px solid lightgray;
		}

		form {
			display: inline-block;
			margin-bottom: 0;
		}

		input[type="submit"] {
			background-color: lightgray;
			border: none;
			padding: 5px 10px;
			border-radius: 5px;
			cursor: pointer;
		}

		input[type="submit"]:hover {
			background-color: yellow;
		}
	</style>


	<h1>採購單管理列表</h1>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<ul>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/requisitions.do" >
        <b>輸入採購單編號 (如1):</b>
        <input type="text" name="reqNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="requisitionsSvc" scope="page" class="com.requisitions.model.RequisitionsService"/>
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/requisitions.do" >
       <b>選擇採購單編號:</b>
       <select size="1" name="reqNo">
         <c:forEach var="requisitionsVO" items="${requisitionsSvc.all}" > 
          <option value="${requisitionsVO.reqNo}">${requisitionsVO.reqNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="adminSvc" scope="page" class="com.admin.model.AdminService" />
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/requisitions.do" >
       <b>採購人員查詢:</b>
       <select size="1" name="adminNo">
 	<c:forEach var="adminVO" items="${adminSvc.all}">
	<option value="${adminVO.adminNo}" ${(requisitionsVO.adminNo==adminVO.adminNo)? 'selected':'' }>${adminVO.adminName}
	</c:forEach>  
       </select>
       <input type="hidden" name="action" value="getReq_By_Admin">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<ul>
  <li><a href='<%=request.getContextPath() %>/back-end/requisitions/listAllRequisitions.jsp'>檢視採購單列表</a></li>
</ul>

<ul>
  <li><a href='<%=request.getContextPath() %>/back-end/requisitions/addRequisitions.jsp'>新增採購單</a></li>
</ul>


 <%@ include file="/back-end/footer.jsp"%> 