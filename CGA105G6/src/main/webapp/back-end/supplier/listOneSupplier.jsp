<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.supplier.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  SupplierVO supplierVO = (SupplierVO) request.getAttribute("supplierVO"); //EmpServlet.java(Concroller), �s�Jreq��supplierVO����
%>



<title>�t�Ӹ�Ƭd�ߵ��G</title>

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
<thead>
	<tr>
		<th>�t�ӽs��</th>
		<th>�t�ӦW��</th>
		<th>�t���p���H</th>
		<th>�t�ӹq��</th>
		<th>�t�Ӧa�}</th>
	</tr>
</thead>
	<tr>
		<td><%=supplierVO.getSupplyNo()%></td>
		<td><%=supplierVO.getSupplyName()%></td>
		<td><%=supplierVO.getSupplyContact()%></td>
		<td><%=supplierVO.getSupplyPhone()%></td>
		<td><%=supplierVO.getSupplyAddress()%></td>
	</tr>
</table>

<%@ include file="/back-end/footer.jsp"%>	