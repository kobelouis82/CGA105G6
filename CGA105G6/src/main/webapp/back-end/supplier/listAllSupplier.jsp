<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.supplier.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    SupplierService supplierSvc = new SupplierService();
    List<SupplierVO> list = supplierSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<head>
<title>採購廠商列表</title>
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

<h4><a href="<%=request.getContextPath()%>/back-end/supplier/select_page.jsp">回首頁</a></h4>

<table>
	<tr>
		<th>廠商編號</th>
		<th>廠商名稱</th>
		<th>廠商聯絡人</th>
		<th>廠商電話</th>
		<th>廠商地址</th>
		<th>修改</th>
		<th>刪除</th>
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
			     <input type="submit" value="修改">
			     <input type="hidden" name="supplyNo"  value="${supplierVO.supplyNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/supplier.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="supplyNo"  value="${supplierVO.supplyNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/back-end/footer.jsp"%> 