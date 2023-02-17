<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.supplier.model.*"%>


<%
  SupplierVO supplierVO = (SupplierVO) request.getAttribute("supplierVO"); //SupplierVOServlet.java (Concroller) 存入req的supplierVO物件 (包括幫忙取出的supplierVO, 也包括輸入資料錯誤時的supplierVO物件)
%>


<%@ include file="/back-end/header.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>採購廠商資料修改</title>
<style>
table {
  width: 80%;
  margin: auto;
  border-collapse: collapse;
}

table, th, td {
  border: 1px solid black;
  padding: 8px;
  text-align: left;
}

.error {
  color: red;
}
</style>

<h4><a href="<%=request.getContextPath()%>/back-end/supplier/select_page.jsp">回首頁</a></h4>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/supplier.do" name="form1">
<table>
<tr>
		<td>廠商編號:</td>
		<td><input type="TEXT" name="supplyNo" size="45" 
			 value="<%= (supplierVO==null)? "001" : supplierVO.getSupplyNo()%>" /></td>
	</tr>
	<tr>
		<td>廠商名稱:</td>
		<td><input type="TEXT" name="supplyName" size="45"
			 value="<%= (supplierVO==null)? "PS專賣店" : supplierVO.getSupplyName()%>" /></td>
	</tr>
	<tr>
		<td>廠商聯絡人:</td>
		<td><input type="TEXT" name="supplyContact" size="45"
			 value="<%= (supplierVO==null)? "Peter" : supplierVO.getSupplyContact()%>" /></td>
	</tr>
	<tr>
		<td>廠商電話:</td>
		<td><input type="TEXT" name="supplyPhone" size="45"
			 value="<%= (supplierVO==null)? "0988123456" : supplierVO.getSupplyPhone()%>" /></td>
	</tr>
	<tr>
		<td>廠商地址:</td>
		<td><input type="TEXT" name="supplyAddress" size="45"
			 value="<%= (supplierVO==null)? "請填詳細地址" : supplierVO.getSupplyAddress()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="supplyNo" value="<%=supplierVO.getSupplyNo()%>">
<input type="submit" value="送出修改"></FORM>
</body>

<%@ include file="/back-end/footer.jsp"%>