<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.requisitions.model.*"%>
<%@ page import="com.supplier.model.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.title.model.*"%>
<%@ page import="com.function.model.*"%>
<%@ include file="/back-end/header.jsp"%>

<%
RequisitionsVO requisitionsVO = (RequisitionsVO) request.getAttribute("requisitionsVO");
AdminVO adminVO=(AdminVO)session.getAttribute("adminVO");
%>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���ʳ�ק�</title>

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
	
				<h4>
					<a href="<%=request.getContextPath() %>/back-end/requisitions/select_page.jsp">�^����</a>
				</h4>
	

	<h3>���ʳ�ק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/requisitions.do" name="form1">
		<table>
			<tr>
				<td>���ʳ�s��:</td>
				<td><%=requisitionsVO.getReqNo()%></td>
			</tr>
			<jsp:useBean id="adminSvc" scope="page"
				class="com.admin.model.AdminService" />
			<tr>
				<td>�޲z���m�W<font color=red><b>*</b></font></td>
				<td><select size="1" name="adminNo">
						<c:forEach var="adminVO" items="${adminSvc.all}">
							<option value="${adminVO.adminNo}"
								${(requisitionsVO.adminNo==adminVO.adminNo)? 'selected':'' }>${adminVO.adminName}
						</c:forEach>
				</select></td>
			</tr>
			<jsp:useBean id="supplierSvc" scope="page"
				class="com.supplier.model.SupplierService" />
			<tr>
				<td>�t�ӦW��<font color=red><b>*</b></font></td>
				<td><select size="1" name="supplyNo">
						<c:forEach var="supplierVO" items="${supplierSvc.all}">
							<option value="${supplierVO.supplyNo}"
								${(requisitionsVO.supplyNo==supplierVO.supplyNo)? 'selected':'' }>${supplierVO.supplyName}
						</c:forEach>
				</select></td>
			</tr>
											<c:choose>
										<c:when test="<%=adminVO.getAdminTitleNo()==1%>">
										<tr>
											<td>���ʳ檬�A:</td>
											<td>
											<select name="reqStatus">
													<option value="0">���e�X</option>
													<option value="1">�ݼf��</option>
													<option value="2">�w�֭�</option>
													<option value="3">�w�i�f</option>
													<option value="4">�w�I��</option>
													<option value="5">����</option>
													<option value="6">�@�o</option>
											</select>
											</td>
										</tr>
										</c:when>
										<c:when test="<%=adminVO.getAdminTitleNo()==2%>">
										<tr>
											<td>���ʳ檬�A:</td>
											<td>
											<select name="reqStatus">
													<option value="0">���e�X</option>
													<option value="1">�ݼf��</option>
													<option value="2">�w�֭�</option>
													<option value="4">�w�I��</option>
											</select>
											</td>
										</tr>
										</c:when>
										<c:when test="<%=adminVO.getAdminTitleNo()==3%>">
										<tr>
											<td>���ʳ檬�A:</td>
											<td>
											<select name="reqStatus">
													<option value="3">�w�i�f</option>
											</select>
											</td>
										</tr>
										</c:when>
										<c:when test="<%=adminVO.getAdminTitleNo()==4%>">
										<tr>
											<td>���ʳ檬�A:</td>
											<td>
											<select name="reqStatus">
													<option value="0">���e�X</option>
													<option value="1">�ݼf��</option>
													<option value="5">����</option>
													<option value="6">�@�o</option>
											</select>
											</td>
										</tr>
										</c:when>
										<c:otherwise>
										<input type="hidden" name="reqStatus" value="1">
										    <td>�ݼf��</td>
										</c:otherwise>
										</c:choose>
			<tr>
				<td>���ʳ�I�ڤ覡:</td>
				<td><select name="reqPay" value="${requisitionsVO.reqPay}">
						<option value="0" ${(requisitionsVO.reqPay==0)? 'selected':''}>�{��</option>
						<option value="1" ${(requisitionsVO.reqPay==1)? 'selected':''}>�״�</option>
						<option value="2" ${(requisitionsVO.reqPay==2)? 'selected':''}>�H�Υd</option>
				</select></td>
			</tr>
			<tr>
				<td>�`����:</td>
				<td><%=requisitionsVO.getTotalPrice()%></td>
			</tr>
	</table>
	<br>
	<br>
	<input type="hidden" name="totalPrice" value="<%=requisitionsVO.getTotalPrice()%>">
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="reqNo" value="<%=requisitionsVO.getReqNo()%>">
	<input type="submit" value="�e�X�ק�">
	</FORM>

<%@ include file="/back-end/footer.jsp"%>