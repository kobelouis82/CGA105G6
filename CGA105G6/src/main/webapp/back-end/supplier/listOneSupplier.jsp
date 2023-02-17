<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.supplier.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  SupplierVO supplierVO = (SupplierVO) request.getAttribute("supplierVO"); //EmpServlet.java(Concroller), 存入req的supplierVO物件
%>



<title>廠商資料查詢結果</title>

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
<thead>
	<tr>
		<th>廠商編號</th>
		<th>廠商名稱</th>
		<th>廠商聯絡人</th>
		<th>廠商電話</th>
		<th>廠商地址</th>
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