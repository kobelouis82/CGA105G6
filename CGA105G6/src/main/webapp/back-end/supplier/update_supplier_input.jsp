<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.supplier.model.*"%>


<%
  SupplierVO supplierVO = (SupplierVO) request.getAttribute("supplierVO"); //SupplierVOServlet.java (Concroller) �s�Jreq��supplierVO���� (�]�A�������X��supplierVO, �]�]�A��J��ƿ��~�ɪ�supplierVO����)
%>


<%@ include file="/back-end/header.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���ʼt�Ӹ�ƭק�</title>
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

<h4><a href="<%=request.getContextPath()%>/back-end/supplier/select_page.jsp">�^����</a></h4>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/supplier.do" name="form1">
<table>
<tr>
		<td>�t�ӽs��:</td>
		<td><input type="TEXT" name="supplyNo" size="45" 
			 value="<%= (supplierVO==null)? "001" : supplierVO.getSupplyNo()%>" /></td>
	</tr>
	<tr>
		<td>�t�ӦW��:</td>
		<td><input type="TEXT" name="supplyName" size="45"
			 value="<%= (supplierVO==null)? "PS�M�橱" : supplierVO.getSupplyName()%>" /></td>
	</tr>
	<tr>
		<td>�t���p���H:</td>
		<td><input type="TEXT" name="supplyContact" size="45"
			 value="<%= (supplierVO==null)? "Peter" : supplierVO.getSupplyContact()%>" /></td>
	</tr>
	<tr>
		<td>�t�ӹq��:</td>
		<td><input type="TEXT" name="supplyPhone" size="45"
			 value="<%= (supplierVO==null)? "0988123456" : supplierVO.getSupplyPhone()%>" /></td>
	</tr>
	<tr>
		<td>�t�Ӧa�}:</td>
		<td><input type="TEXT" name="supplyAddress" size="45"
			 value="<%= (supplierVO==null)? "�ж�ԲӦa�}" : supplierVO.getSupplyAddress()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="supplyNo" value="<%=supplierVO.getSupplyNo()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

<%@ include file="/back-end/footer.jsp"%>