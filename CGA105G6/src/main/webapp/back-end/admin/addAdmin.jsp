<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%
AdminVO adminVO = (AdminVO) request.getAttribute("adminVO"); //EmpServlet.java (Concroller) 存入req的adminVO物件 (包括幫忙取出的adminVO, 也包括輸入資料錯誤時的adminVO物件)
%>

<div class="container-fluid">
	<!-- 標頭 -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">管理人員管理功能</h1>
	</div>
	<!-- 中間區塊 -->
	<div class="row">
		<div class="col-xl-3 col-lg-12 col-md-9">
			<a
				href="<%=request.getContextPath()%>/back-end//admin/listAllAdmin.jsp"
				class="btn btn-success">回管理員資料清單</a>
			<p></p>
		</div>
		<!-- 錯誤訊息 -->
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="col-xl-12 col-lg-12 col-md-9">
			<div class="card shadow mb-10">
				<div class="card-header py-3">
					<h3 class="m-0 font-weight-bold text-primary">新增管理員</h3>
				</div>
				<div class="card-body">
					<div class="text-left">
					<div id="picPreview" style="display: flex; width: 100%; height: 100%; flex-wrap: wrap; position: relative;"></div>	
						<FORM METHOD="post" ACTION="admin.do"
							enctype="multipart/form-data">
							<table class="table table table-borderless">
								<tr>
									<td>員工姓名:</td>
									<td><input type="TEXT" name="adminName" size="45"
										value="<%=(adminVO == null) ? "王大明" : adminVO.getAdminName()%>" /></td>
								</tr>
								<jsp:useBean id="titleSvc" scope="page"
									class="com.title.model.TitleService" />
								<tr>
									<td>職位名稱:<font color=red><b>*</b></font></td>
									<td><select size="1" name="adminTitleNo">
											<c:forEach var="titleVO" items="${titleSvc.all}">
												<option value="${titleVO.adminTitleNo}"
													${(adminVO.adminTitleNo==titleVO.adminTitleNo)?'selected':'' }>${titleVO.adminTitle}
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td>電話:</td>
									<td><input type="TEXT" name="phone" size="45"
										value="<%=(adminVO == null) ? "0123456789" : adminVO.getPhone()%>" /></td>
								</tr>
								<tr>
									<td>信箱:</td>
									<td><input type="TEXT" name="mail" size="45"
										value="<%=(adminVO == null) ? "new@gmail.com" : adminVO.getMail()%>" /></td>
								</tr>

								<tr>
									<td>帳號:</td>
									<td><input type="TEXT" name="account" size="45"
										value="<%=(adminVO == null) ? "" : adminVO.getAccount()%>" /></td>
								</tr>
								<tr>
									<td>密碼:</td>
									<td><input type="TEXT" name="password" size="45"
										value="<%=(adminVO == null) ? "" : adminVO.getPassword()%>" /></td>
								</tr>
								<tr>
									<td>在職狀態:</td>
									<td><select size="1" name="state" size="45">
											<option value="0" selected="selected">在職

											
											<option value="1" >離職

											
									</select></td>
								</tr>
								<tr>
									<td>頭像上傳:</td>
									<td><input type="file" name="upfile1"
										class="btn btn-outline-secondary" id="upfile"></td>
										<td><input class="acess" type="reset" id="upload" value="重設"></td>
								</tr>
							</table>
							<br>
							<div class="text-center">
								<input type="hidden" name="action" value="insert"> <input
									type="submit" value="送出新增" class="btn btn-dark btnIn">
							</div>
						</FORM>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.6.1.js"
            integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<script>
var filereader_support = typeof FileReader != 'undefined';
if (!filereader_support) {
	alert("No FileReader support");
}
acceptedTypes = {
	'image/png' : true,
	'image/jpeg' : true,
	'image/gif' : true
};
let upfile = document.getElementById("upfile");
upfile.addEventListener("change", function(event) {
	let files = event.target.files || event.dataTransfer.files;
	for (let i = 0; i < files.length; i++) {
		previewfile(files[i])
	}
}, false);
function previewfile(file) {
	if (filereader_support === true
			&& acceptedTypes[file.type] === true) {
		let reader = new FileReader();
		reader.onload = function(event) {
			let image = new Image();
			image.src = event.target.result;
			image.width = 200;
//				image.classList.add("imgCss");
			picPreview.appendChild(image);
		};
		reader.readAsDataURL(file);
	} else {
		picPreview.innerHTML += "<p>" + "filename: <strong>"
				+ file.name + "</strong><br>" + "ContentTyp: <strong>"
				+ file.type + "</strong><br>" + "size: <strong>"
				+ file.size + "</strong> bytes</p>";
	}
}
$("#upload").click(function() {
	$("#picPreview").empty() // 清空當下預覽
	previewfile(this.files) // this即為<input>元素
})
</script>
<%@ include file="/back-end/footer.jsp"%>
