<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.requisitions_detail.model.*"%>
<%@ include file="/back-end/header.jsp"%>

<%
	Requisitions_DetailVO requisitions_detailVO = (Requisitions_DetailVO ) request.getAttribute("Requisitions_DetailVO"); //Requisitions_DetailServlet.java(Concroller), �s�Jreq��requisitions_detailVO����
%>



<title>���ʳ���Ӹ��</title>

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

	</style>


<body>


<table id="table-1">
	<tr><td>
		 <h4><a href="<%=request.getContextPath()%>/back-end/requisitions/select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���ʳ���ӽs��</th>
		<th>���ʳ�s��</th>
		<th>�C���y����</th>
		<th>�C�����A�s��</th>
		<th>�ӫ~�ƶq</th>
		<th>�ӫ~���</th>
	</tr>
	<tr>
		<td><%=requisitions_detailVO.getReqDetailNo()%></td>		
		<td><%=requisitions_detailVO.getReqNo()%></td>
		<td><%=requisitions_detailVO.getSerialNo()%>-[<%=requisitions_detailVO.getShopVO().getItemName()%>]</td>
		<td><%=requisitions_detailVO.getStatus()%></td>
		<td><%=requisitions_detailVO.getQty()%></td>
		<td><%=requisitions_detailVO.getPrice()%></td>
	</tr>
</table>

</body>
 <%@ include file="/back-end/footer.jsp"%> 