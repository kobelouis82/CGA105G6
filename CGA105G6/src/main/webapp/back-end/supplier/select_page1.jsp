<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/back-end/header.jsp"%>

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
			background-color: gray;
		}
	</style>

<h3>廠商功能管理列表</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>

  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/supplier.do" >
        <b>輸入廠商編號: </b>
        <input type="text" name="supplyNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="supplierSvc" scope="page" class="com.supplier.model.SupplierService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/supplier.do" >
       <b>選擇廠商編號:</b>
       <select size="1" name="supplyNo">
         <c:forEach var="supplierVO" items="${supplierSvc.all}" > 
          <option value="${supplierVO.supplyNo}">${supplierVO.supplyNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/supplier.do" >
       <b>選擇廠商姓名:</b>
       <select size="1" name="supplyNo">
         <c:forEach var="supplierVO" items="${supplierSvc.all}" > 
          <option value="${supplierVO.supplyNo}">${supplierVO.supplyName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<ul>
<li><a href='listAllSupplier.jsp'>查詢所有廠商</a></li>
</ul>

<ul>
<li><a href='addSupplier.jsp'>新增採購廠商資料</a></li>
</ul>
<%@ include file="/back-end/footer.jsp"%> 
