<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>
<%
AdminVO adminVO = (AdminVO) request.getAttribute("adminVO"); //EmpServlet.java (Concroller) 存入req的adminVO物件 (包括幫忙取出的adminVO, 也包括輸入資料錯誤時的adminVO物件)
%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>後台管理系統</title>

<!-- Custom fonts for this template-->
<link
	href="<%=request.getContextPath()%>/back-end/assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="<%=request.getContextPath()%>/back-end/assets/css/sb-admin-2.min.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<!--標題小logo-->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="index.html">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-power-off"></i>
					<!--標題小logo-->
				</div>
				<div class="sidebar-brand-text mx-3">
					後台管理系統<sup></sup>
				</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="index.html"> <i class="fas fa-home"></i> <span>首頁</span></a></li>

			<!-- -------左側功能列表欄------- -->
			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">功能列表</div>
			<!-- -----商品管理功能----- -->
			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseproduct"
				aria-expanded="true" aria-controls="collapseproduct"> <i
					class="fas fa-archive"></i> <span>商城管理</span>
			</a>
				<div id="collapseproduct" class="collapse"
					aria-labelledby="headingTwo" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">功能選單:</h6>
						<a class="collapse-item" href="blank.html">功能1</a> <a
							class="collapse-item" href="#.html">功能2</a> <a
							class="collapse-item" href="#.html">功能3</a> <a
							class="collapse-item" href="#.html">功能4</a>
					</div>
				</div></li>
			<!-- -----商品管理功能結束----- -->
			<!-- -----二手商品管理功能----- -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsesecond"
				aria-expanded="true" aria-controls="collapsesecond"> <i
					class="fas fa-tag"></i> <span>二手商品管理</span>
			</a>
				<div id="collapsesecond" class="collapse"
					aria-labelledby="headingTwo" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">功能選單:</h6>
						<a class="collapse-item" href="#.html">功能1</a> <a
							class="collapse-item" href="#.html">功能2</a> <a
							class="collapse-item" href="#.html">功能3</a> <a
							class="collapse-item" href="#.html">功能4</a>
					</div>
				</div></li>
			<!-- -----二手商品管理功能結束----- -->
			<!-- -----採購管理功能----- -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsereq"
				aria-expanded="true" aria-controls="collapsereq"> <i
					class="fas fa-dolly-flatbed"></i> <span>採購管理</span>
			</a>
				<div id="collapsereq" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">功能選單:</h6>
						<a class="collapse-item" href="#.html">功能1</a> <a
							class="collapse-item" href="#.html">功能2</a> <a
							class="collapse-item" href="#.html">功能3</a> <a
							class="collapse-item" href="#.html">功能4</a>
					</div>
				</div></li>
			<!-- -----採購管理功能結束----- -->
			<!-- -----會員管理功能----- -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsemember"
				aria-expanded="true" aria-controls="collapsemember"> <i
					class="fas fa-user-circle"></i> <span>會員管理</span>
			</a>
				<div id="collapsemember" class="collapse"
					aria-labelledby="headingTwo" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">功能選單:</h6>
						<a class="collapse-item" href="#.html">功能1</a> <a
							class="collapse-item" href="#.html">功能2</a> <a
							class="collapse-item" href="#.html">功能3</a> <a
							class="collapse-item" href="#.html">功能4</a>
					</div>
				</div></li>
			<!-- -----會員管理功能結束----- -->
			<!-- -----討論區管理功能----- -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseforum"
				aria-expanded="true" aria-controls="collapseforum"> <i
					class="fas fa-user-friends"></i> <span>討論區管理</span>
			</a>
				<div id="collapseforum" class="collapse"
					aria-labelledby="headingTwo" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">功能選單:</h6>
						<a class="collapse-item" href="#.html">功能1</a> <a
							class="collapse-item" href="#.html">功能2</a> <a
							class="collapse-item" href="#.html">功能3</a> <a
							class="collapse-item" href="#.html">功能4</a>
					</div>
				</div></li>
			<!-- -----討論區管理功能結束----- -->
			<!-- -----管理員權限管理功能----- -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseadmin"
				aria-expanded="true" aria-controls="collapseadmin"> <i
					class="fas fa-id-card"></i> <span>管理員資料管理</span>
			</a>
				<div id="collapseadmin" class="collapse"
					aria-labelledby="headingTwo" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">功能選單:</h6>
						<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/admin/select_page.jsp">管理員帳號管理</a> <a
							class="collapse-item" href="#.html">管理員權限管理</a>
					</div>
				</div></li>
			<!-- -----管理員權限管理功能結束----- -->
			<!-- -----前台網站管理功能----- -->
			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsefront"
				aria-expanded="true" aria-controls="collapsefront"> <i
					class="fas fa-fw fa-wrench"></i> <span>前台管理</span>
			</a>
				<div id="collapsefront" class="collapse"
					aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">功能選單:</h6>
						<a class="collapse-item" href="#.html">功能1</a> <a
							class="collapse-item" href="#.html">功能2</a> <a
							class="collapse-item" href="#.html">功能3</a> <a
							class="collapse-item" href="#.html">功能4</a>
					</div>
				</div></li>
			<!-- -----前台網站管理功能結束----- -->
			<!-- -----FQ管理功能----- -->
			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsefq"
				aria-expanded="true" aria-controls="collapsefq"> <i
					class="fas fa-question-circle"></i> <span>FQ管理</span>
			</a>
				<div id="collapsefq" class="collapse"
					aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">功能選單:</h6>
						<a class="collapse-item" href="#.html">功能1</a> <a
							class="collapse-item" href="#.html">功能2</a> <a
							class="collapse-item" href="#.html">功能3</a> <a
							class="collapse-item" href="#.html">功能4</a>
					</div>
				</div></li>
			<!-- -----FQ管理功能結束----- -->

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">關聯頁面(暫時)</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-fw fa-folder"></i> <span>功能頁</span>
			</a>
				<div id="collapsePages" class="collapse"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">登入相關:</h6>
						<a class="collapse-item" href="login.html">Login</a> <a
							class="collapse-item" href="register.html">Register</a> <a
							class="collapse-item" href="forgot-password.html">Forgot
							Password</a> <a class="collapse-item" href="admin_login.html">Admin_Login</a>
						<div class="collapse-divider"></div>
						<h6 class="collapse-header">Other Pages:</h6>
						<a class="collapse-item" href="404.html">404 Page</a> <a
							class="collapse-item" href="blank.html">Blank Page</a>
					</div>
				</div></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="tables.html">
					<i class="fas fa-fw fa-table"></i> <span>Tables</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Search -->
					<form
						class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
						<div class="input-group">
							<input type="text" class="form-control bg-light border-0 small"
								placeholder="Search for..." aria-label="Search"
								aria-describedby="basic-addon2">
							<div class="input-group-append">
								<button class="btn btn-primary" type="button">
									<i class="fas fa-search fa-sm"></i>
								</button>
							</div>
						</div>
					</form>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none"><a
							class="nav-link dropdown-toggle" href="#" id="searchDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
								aria-labelledby="searchDropdown">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input type="text"
											class="form-control bg-light border-0 small"
											placeholder="Search for..." aria-label="Search"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div></li>

						<!-- Nav Item - Alerts -->
						<li class="nav-item dropdown no-arrow mx-1"><a
							class="nav-link dropdown-toggle" href="#" id="alertsDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-bell fa-fw"></i> <!-- Counter - Alerts -->
								<span class="badge badge-danger badge-counter">3+</span>
						</a> <!-- Dropdown - Alerts -->
							<div
								class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="alertsDropdown">
								<h6 class="dropdown-header">系統通知</h6>
								<a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-primary">
											<i class="fas fa-file-alt text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">December 12, 2019</div>
										<span class="font-weight-bold">A new monthly report is
											ready to download!</span>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-success">
											<i class="fas fa-donate text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">December 7, 2019</div>
										$290.29 has been deposited into your account!
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-warning">
											<i class="fas fa-exclamation-triangle text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">December 2, 2019</div>
										Spending Alert: We've noticed unusually high spending for your
										account.
									</div>
								</a> <a class="dropdown-item text-center small text-gray-500"
									href="#">Show All Alerts</a>
							</div></li>

						<!-- Nav Item - Messages -->
						<li class="nav-item dropdown no-arrow mx-1"><a
							class="nav-link dropdown-toggle" href="#" id="messagesDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-envelope fa-fw"></i>
								<!-- Counter - Messages --> <span
								class="badge badge-danger badge-counter">7</span>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="messagesDropdown">
								<h6 class="dropdown-header">Message Center</h6>
								
								</a> <a class="dropdown-item text-center small text-gray-500"
									href="#">Read More Messages</a>
							</div></li>

						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">${adminVO.adminName}</span>
								<img class="img-profile rounded-circle"
								src="${pageContext.request.contextPath}/back-end/admin/admin.do?action=getPhoto&adminNo=${adminVO.adminNo}">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/admin/admin.do?action=getOne_For_Display&adminNo=${adminVO.adminNo}"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> 管理員資訊
								</a> 
<%-- 								<form method="post" class="dropdown-item" action="<%=request.getContextPath()%>/back-end/admin/admin.do"> --%>
<!-- 								<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> -->
<!-- 									<div> -->
<%-- 										<input type="hidden" name="adminNo" value= "${adminVO.adminNo}" > --%>
<!-- 										<input type="hidden" name="action" value="getOne_For_Display">  -->
<!-- 										<input type="submit" value="使用者資訊" style="float: right"> -->
<!-- 									</div> -->
<!-- 								</form> -->
								<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/admin/admin.do?action=getOne_For_Update&adminNo=${adminVO.adminNo}"> <i
									class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
									修改資料
								</a>
<%-- 								<form method="post" class="dropdown-item" action="<%=request.getContextPath()%>/back-end/admin/admin.do"> --%>
<!-- 								<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> -->
<!-- 									<div> -->
<%-- 										<input type="hidden" name="adminNo" value= "${adminVO.adminNo}" > --%>
<!-- 										<input type="hidden" name="action" value="getOne_For_Update">  -->
<!-- 										<input type="submit" value="setting" style="float: right"> -->
<!-- 									</div> -->
<!-- 								</form> -->
<!-- 								<a class="dropdown-item" href="#"> <i -->
<!-- 									class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i> -->
<!-- 									Activity Log -->
<!-- 								</a> -->
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									登出
								</a>
							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">


                    <!-- Page Heading -->
                    <c:if test="${not empty errorMsgs}">
                        <font style="color: red">請修正以下錯誤:</font>
                        <ul>
                            <c:forEach var="message" items="${errorMsgs}">
                                <li style="color: red">${message}</li>
                            </c:forEach>
                        </ul>
                    </c:if>
                
                    <FORM METHOD="post" ACTION="admin.do" name="form1"
                        enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td>員工編號:<font color=red><b>*</b></font></td>
                                <td><%=adminVO.getAdminNo()%></td>
                            </tr>
                            <tr>
                                <td>員工姓名:</td>
                                <td><%=adminVO.getAdminName()%>></td>
                            </tr>
                            <tr>
                                <td>電話:</td>
                                <td><input type="TEXT" name="phone" size="45"
                                    value="<%=adminVO.getPhone()%>" /></td>
                            </tr>
                            <tr>
                                <td>信箱:</td>
                                <td><input type="TEXT" name="mail" size="45"
                                    value="<%=adminVO.getMail()%>" /></td>
                            </tr>
                
                            <tr>
                                <td>帳號:</td>
                                <td><%=adminVO.getAccount()%>></td>
                            </tr>
                            <tr>
                                <td>密碼:</td>
                                <td><input type="TEXT" name="password" size="45"
                                    value="<%=adminVO.getPassword()%>" /></td>
                            <tr>
                                <td>照片:</td>
                                <Img src="${pageContext.request.contextPath}/back-end/admin/admin.do?action=getPhoto&adminNo=<%=adminVO.getAdminNo()%>" width=200>
                                <td><input type="file" name="upfile1" /></td>
                            </tr>
                            <tr>
                                <td>在職狀態:</td>
                                <td><select size="1" name="state" size="45" disabled="disabled">
                                <option value="0" ${(adminVO.state == "0")? 'selected': '' }>離職
                                <option value="1" ${(adminVO.state == "1")? 'selected': '' }>在職
                                
                                </select>			
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
                
                        </table>
                        <br> <input type="hidden" name="action" value="update"> <input
                            type="hidden" name="adminNo" value="<%=adminVO.getAdminNo()%>">
                        <input type="submit" value="送出修改">
                    </FORM>
                    
                <!-- /.container-fluid -->

            </div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; CGA105_G6 Website 2023</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel" style="color: red">
						<b>確定要登出嗎? 
					</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">請再次確認是否要登出系統</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">取消</button>
					<form method="post" class="btn btn-primary" action="adminLogin.do">
						<div>
							<input type="hidden" name="action" value="LogOut"> <input
								type="submit" value="登出" style="float: right">
						</div>
					</form>
					<!-- 					<a class="btn btn-primary" href="adminLogin.jsp">登出</a> -->
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/assets/vendor/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="<%=request.getContextPath()%>/back-end/assets/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script
		src="<%=request.getContextPath()%>/back-end/assets/vendor/chart.js/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<script
		src="<%=request.getContextPath()%>/back-end/assets/js/demo/chart-area-demo.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/js/demo/chart-pie-demo.js"></script>

</body>

</html>