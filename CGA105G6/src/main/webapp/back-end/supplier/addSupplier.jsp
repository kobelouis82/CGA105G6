<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.supplier.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%
SupplierVO supplierVO = (SupplierVO) request.getAttribute("supplierVO");
%>

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

#addbtn {
	margin-left: 50%;
}
</style>

<title>�s�W�t�Ӹ��</title>

<!-- Page Heading -->
<c:if test="${not empty errorMsgs}">
	<font style="color: red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/supplier.do" name="form1">
	<table>
		<tr>
			<td>�t�ӦW��:</td>
			<td><input type="TEXT" name="supplyName" size="45"
				value="<%=(supplierVO == null) ? "PS�M�橱" : supplierVO.getSupplyName()%>" /></td>
		</tr>
		<tr>
			<td>�t���p���H:</td>
			<td><input type="TEXT" name="supplyContact" size="45"
				value="<%=(supplierVO == null) ? "Peter" : supplierVO.getSupplyContact()%>" /></td>
		</tr>
		<tr>
			<td>�t�ӹq��:</td>
			<td><input type="TEXT" name="supplyPhone" size="45"
				value="<%=(supplierVO == null) ? "0988123456" : supplierVO.getSupplyPhone()%>" /></td>
		</tr>
		<tr>
			<td>�t�Ӧa�}:</td>
			<td><input type="TEXT" name="supplyAddress" size="45"
				value="<%=(supplierVO == null) ? "�ж�ԲӦa�}" : supplierVO.getSupplyAddress()%>" /></td>
		</tr>

	</table>
	<br> 
	<input type="hidden" name="action" value="insert"> 
	<input type="submit" value="�e�X�s�W" id="addbtn">

</FORM>
<%@ include file="/back-end/footer.jsp"%> 