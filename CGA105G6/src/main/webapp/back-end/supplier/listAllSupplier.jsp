<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.supplier.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    SupplierService supplierSvc = new SupplierService();
    List<SupplierVO> list = supplierSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<head>
<title>���ʼt�ӦC��</title>
</head>

 <style>
    table {
      width: 100%;
      border-collapse: collapse;
    }
    th, td {
      border: 1px solid black;
      padding: 8px;
      text-align: left;
    }
    th {
      background-color: #dddddd;
    }
  </style>

<h4><a href="<%=request.getContextPath()%>/back-end/supplier/select_page.jsp">�^����</a></h4>

<table>
	<tr>
		<th>�t�ӽs��</th>
		<th>�t�ӦW��</th>
		<th>�t���p���H</th>
		<th>�t�ӹq��</th>
		<th>�t�Ӧa�}</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<c:forEach var="supplierVO" items="${list}">
		
		<tr>
			<td>${supplierVO.supplyNo}</td>
			<td>${supplierVO.supplyName}</td>
			<td>${supplierVO.supplyContact}</td>
			<td>${supplierVO.supplyPhone}</td>
			<td>${supplierVO.supplyAddress}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/supplier.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="supplyNo"  value="${supplierVO.supplyNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/supplier.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="supplyNo"  value="${supplierVO.supplyNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/back-end/footer.jsp"%> 