<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.supplier.model.*"%>

<%@ include file="/back-end/header.jsp"%>
<%
    SupplierService supplierSvc = new SupplierService();
    List<SupplierVO> list = supplierSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<style>
	#add{
		margin-left : 50%;
	}
</style>

<div class="card-body">
	<div class="table-responsive">
		<table class="table table-bordered" id="dataTable" width="100%"
			cellspacing="0">
			<thead class="thead-dark">
				<tr>
					<th>廠商編號</th>
		            <th>廠商名稱</th>
		            <th>廠商聯絡人</th>
		            <th>廠商電話</th>
		            <th>廠商地址</th>
		            <th>修改</th>
				</tr>
			</thead>
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
			     <input type="hidden" name="action"	value="getOne_For_Update">
			    </FORM>
			    </td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<input id="add" type ="button" onclick="javascript:location.href='http://localhost:8081/CGA105G6/back-end/supplier/addSupplier.jsp'" value="新增廠商資料"></input> 
	</div>
</div>



<%@ include file="/back-end/footer.jsp"%>