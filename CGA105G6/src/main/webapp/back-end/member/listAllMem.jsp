<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ include file="/back-end/header.jsp"%>

<%
MemService memSvc = new MemService();
List<MemVO> list = memSvc.getAll();
pageContext.setAttribute("list", list);
%>


<style>
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
	padding: 12px 10px;
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


</style>

<style>
table {
	margin-left: auto;
	margin-right: auto;
	width: 80%;
	margin-top: 5px;
	margin-bottom: 5px;
}

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
</head>

<div class="container-fluid">
<!-- 標頭 -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
     <h1 class="h3 mb-0 text-gray-800">會員管理功能</h1>
     
</div>
<!-- 中間區塊 -->
<div class="row">
<div class="col-xl-3 col-lg-12 col-md-9">
<a href="<%=request.getContextPath()%>/back-end/member/select_page.jsp" class="btn btn-success">回到會員資料查詢</a>
<p></p>
</div>
 <div class="col-xl-12 col-lg-12 col-md-9">
   <div class="card shadow mb-10">
        <div class="card-header py-3">
             <h3 class="m-0 font-weight-bold text-primary">全體會員管理</h3>
        </div>
        <div class="card-body">
             <div class="text-left">
						<table class="table table table-bordered"">
							<thead>
								<tr>	
									<th>會員編號</th>
									<th>會員帳號</th>
									<th>會員姓名</th>
									<th>會員地址</th>
									<th>會員電話</th>
									<th>會員信箱</th>
									<th>會員生日</th>
									<th>會員權限</th>
									<th>修改</th>
									<th>刪除</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="memVO" items="${list}">
								<tr class="active-row" align='center' valign='middle'>
									<td>${memVO.memNo}</td>
									<td>${memVO.memAccount}</td>
									<td>${memVO.memName}</td>
									<td>${memVO.memAdd}</td>
									<td>${memVO.memTel}</td>
									<td>${memVO.memMail}</td>
									<td>${memVO.memBirth}</td>
							<c:if test="${memVO.memAccess==0}">
									<td><c:out value="未驗證"></c:out></td>
							</c:if>
							<c:if test="${memVO.memAccess==1}">
									<td><c:out value="已驗證"></c:out></td>
							</c:if>
							<c:if test="${memVO.memAccess==2}">
									<td><c:out value="停權"></c:out></td>
							</c:if>
									<td>
									<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/memServlet1.do"
									style="margin-bottom: 0px;">
									<input type="submit"  value="修改" class="btn btn-warning"> 
									<input type="hidden" name="memNo" value="${memVO.memNo}"> 
									<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
									<td>
									<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/memServlet1.do" style="margin-bottom: 0px;">
									<input type="submit"  value="刪除" class="btn btn-danger btnIn"> 
									<input type="hidden" name="memNo" value="${memVO.memNo}"> 
									<input type="hidden" name="action" value="delete">
									</FORM>
									</td>
									</tr>
							</c:forEach>
						</tbody>
					</table>	
					</div>						
				</div>						
			</div>							
		</div>								
	</div>									
</div>										
																											
<!-- <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.1/dist/sweetalert2.all.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.6.7/dist/sweetalert2.all.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.1/dist/sweetalert2.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.6.7/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.1.js"
            integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
   
<script>

	
	$('.btn-danger').click(function(e){
		e.preventDefault();
		var form = $(this).parents('FORM');
		Swal.fire({
			  title: '確認要刪除會員資料嗎？',
			  showCancelButton: true,
			  cancelButtonText: "取消",
			  confirmButtonText: '確定',
			  confirmButtonColor: 'green',
			}).then((result) => {
			  if (result.isConfirmed) {
			    Swal.fire('刪除成功', '', 'success'),
			    setTimeout(function(){
			    	form.submit();
				},1000);
			  }
			})
	})
	
	$('.btn-warning').click(function(e){
		e.preventDefault();
		var form = $(this).parents('FORM');
		Swal.fire({
			  title: '確認要修改會員資料嗎？',
			  showCancelButton: true,
			  cancelButtonText: "取消",
			  confirmButtonText: '確定',
			  confirmButtonColor: 'green',
			}).then((result) => {
			  if (result.isConfirmed) {
			    Swal.fire('前往修改', '', 'success'),
			    setTimeout(function(){
			    	form.submit();
				},1000);
			  }
			})
	})
</script>
<%@ include file="/back-end/footer.jsp"%>