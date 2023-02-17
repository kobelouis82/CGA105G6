<%@page import="com.admin.model.*"%>
<%@page import="com.title.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String sessionId = (String) session.getAttribute("id");
%>
<%@ page import="java.util.*"%>
<%
AdminService adminSvc = new AdminService();
List<AdminVO> list = adminSvc.getAll();
pageContext.setAttribute("list", list);
%>
<%@ include file="/back-end/header.jsp"%>

<div class="container-fluid">
	<!-- 標頭 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">管理員清單</h1>

	</div>
	<!-- 中間區塊 -->
	<div class="row">
		<div class="col-xl-3 col-lg-12 col-md-9">
			<a
				href="<%=request.getContextPath()%>/back-end/admin/select_page.jsp"
				class="btn btn-success">回到管理員資料查詢</a>
			<p></p>
		</div>


		<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<!--                         <div class="card-header py-3"> -->
			<!--                             <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6> -->
			<!--                         </div> -->
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="dataTable" width="100%"
						cellspacing="0">
						<thead>
							<tr>
								<th>員工編號</th>
								<th>員工姓名</th>
								<th>職位</th>
								<th>電話</th>
								<th>信箱</th>
								<th>帳號</th>
								<th>密碼</th>
								<th>在職狀態</th>
								<th>照片</th>
								<th>修改</th>
<!-- 								<th>刪除</th> -->
							</tr>
						</thead>
						<tbody>
							<c:forEach var="adminVO" items="${list}">
								<tr>
									<td>${adminVO.adminNo}</td>
									<td>${adminVO.adminName}</td>
									<td>${adminVO.titleVO.adminTitle}</td>
									<td>${adminVO.phone}</td>
									<td>${adminVO.mail}</td>
									<td>${adminVO.account}</td>
									<td>${adminVO.password}</td>
									<td>${adminVO.state==0? "在職":"離職"}</td>
									<td><Img
										src="${pageContext.request.contextPath}/back-end/admin/admin.do?action=getPhoto&adminNo=${adminVO.adminNo}"
										width="100"></td>
									<td>
										<FORM METHOD="post" ACTION="admin.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="修改" class="btn btn-warning">
											<input type="hidden" name="adminNo"
												value="${adminVO.adminNo}"> <input type="hidden"
												name="action" value="getOne_For_Update">
										</FORM>
									</td>
<!-- 									<td> -->
<!-- 										<FORM METHOD="post" ACTION="admin.do" -->
<!-- 											style="margin-bottom: 0px;"> -->
<!-- 											<input type="submit" value="刪除" class="btn btn-danger btnIn"> -->
<!-- 											<input type="hidden" name="adminNo" -->
<%-- 												value="${adminVO.adminNo}"> <input type="hidden" --%>
<!-- 												name="action" value="delete"> -->
<!-- 										</FORM> -->
<!-- 									</td> -->
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script>

	
	$('.btn-danger').click(function(e){
		e.preventDefault();
		var form = $(this).parents('FORM');
		Swal.fire({
			  title: '確認要刪除管理員資料嗎？',
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
			  title: '確認要修改管理員資料嗎？',
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