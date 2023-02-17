<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ include file="/back-end/header.jsp"%>


<style>
/* <!-- ===========================================樣式欄位================================================================== --> */

/* tr:nth-child(odd){ */
/*   background:white; */
/* } */

/* tr:nth-child(even){ */
/*   background:#a4a9ad; */
/* } */
.styled-table {
	margin-left: auto;
	margin-right: auto;
	border-collapse: collapse;
	margin: auto;
	font-size: 0.9em;
	font-family: sans-serif;
	min-width: 400px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

.styled-table thead tr {
	background-color: #212529;
	color: #ffffff;
	text-align: left;
}

.styled-table th, .styled-table td {
	padding: 12px 15px;
}

.styled-table tbody tr {
	border-bottom: 1px solid #dddddd;
}

.styled-table tbody tr:nth-of-type(even) {
	background-color: #f3f3f3;
}

.styled-table tbody tr:last-of-type {
	border-bottom: 2px solid #212529;
}

.styled-table tbody tr.active-row {
	font-weight: bold;
	color: #212529;
}

/* <!-- ===========================================樣式欄位================================================================== --> */
table#table-1 {
	background-color: #212529;
	text-align: center;
	margin-left: auto;
	margin-right: auto;
	width: 1000px;
	margin-top: 20px;
	margin-bottom: 5px;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h3 {
	color: black;
	font-weight: 700 !important;
}

h4 {
	color: blue;
	display: inline;
}

/*   a{ */
/*     color: white; */
/*     display: inline; */
/*   } */
</style>

<style>
table {
	margin-left: auto;
	margin-right: auto;
	width: 80%;
	margin-top: 5px;
	margin-bottom: 5px;
}
/*    table, th, td {  */
/*      border: 1px solid #212529;  */
/*    }  */
th, td {
	padding: 5px;
	text-align: center;
}

.btnIn {
	border-radius: 20px !important;
}

.thTitle {
	background-color: #B5FFFC !important;
	border: none;
}

.btnBlock {
	margin-top: 30px;
	display: flex;
	align-items: center;
	justify-content: center;
}

section {
	height: 100%;
	background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
	background-color: #FFDEE9;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>

<table >
	<tr>
		<th >
			<h3>複合會員查詢</h3>
		</th>
	</tr>
</table>

<table class="styled-table">
	<thead>
		<tr>
			<th>會員編號</th>
			<th>會員帳號</th>
			<th>會員姓名</th>
			<th>會員權限</th>
			<th>會員生日</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="memVO" items="${MemSerchPro}">
			<tr class="active-row" align='center' valign='middle'>
				<td>${memVO.memNo}</td>
				<td>${memVO.memAccount}</td>
				<td>${memVO.memName}</td>

				<c:if test="${memVO.memAccess==0}">
					<td><c:out value="未驗證"></c:out></td>
				</c:if>
				<c:if test="${memVO.memAccess==1}">
					<td><c:out value="已驗證"></c:out></td>
				</c:if>
				<c:if test="${memVO.memAccess==2}">
					<td><c:out value="停權"></c:out></td>
				</c:if>
				<td>${memVO.memBirth}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/memServlet1.do"
						style="margin-bottom: 0px;">
						<input type="submit" onclick="return up_confirm()" value="修改"
							class="btn btn-warning btnIn"> <input type="hidden"
							name="memNo" value="${memVO.memNo}"> <input type="hidden"
							name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/memServlet1.do"
						style="margin-bottom: 0px;">
						<input type="submit" onclick="return de_confirm()" value="刪除"
							class="btn btn-danger btnIn"> <input type="hidden"
							name="memNo" value="${memVO.memNo}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</tbody>

</table>
<div class="btnBlock">
	<a class="btn btn-success"
		href="<%=request.getContextPath()%>/back-end/member/select_page.jsp">回到會員資料查詢</a>
</div>

<script>
	function up_confirm() {
		var r = confirm("你確定要修改嗎?")
		if (r == true) {
			return true;
		} else {
			return false;
		}
	}

	function de_confirm() {
		var r = confirm("你確定要刪除嗎?")
		if (r == true) {
			alert("成功刪除");
		} else {
			return false;
		}
	}
</script>
<%@ include file="/back-end/footer.jsp"%>