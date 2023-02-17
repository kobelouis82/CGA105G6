<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>
<%
AdminVO adminVO1 = (AdminVO) session.getAttribute("adminVO");
if (adminVO1 == null) {
	response.sendRedirect(request.getContextPath() + "/back-end/admin/adminLogin.jsp");
	return;
}
Integer adminNo = adminVO1.getAdminNo();
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

<!-- Custom styles for this template -->
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
				href="<%=request.getContextPath()%>/back-end/main.jsp">
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
				href="<%=request.getContextPath()%>/back-end/main.jsp"> <i
					class="fas fa-home"></i> <span>首頁</span></a></li>

			<!-- -------左側功能列表欄------- -->

			<!-- Divider -->

			<hr class="sidebar-divider">
			<jsp:useBean id="accessSvc1" class="com.access.model.AccessService" />
			<c:forEach var="accessVO" items="${accessSvc1.getbyAdmin(adminNo)}">

				<!-- Heading -->
				<c:if test="${accessVO.adminFunction ==1}">
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
								<a href="<%=request.getContextPath()%>/back-end/shop/addShop.jsp" class="collapse-item">新增商品</a>
								<a href="<%=request.getContextPath()%>/back-end/shop/select_page.jsp" class="collapse-item">查詢商品</a> 
								<a href="<%=request.getContextPath()%>/back-end/order/select_page.jsp" class="collapse-item">查詢訂單</a> 
								<a href="<%=request.getContextPath()%>/back-end/orderDetail/select_page.jsp" class="collapse-item" >查詢訂單明細</a>
							</div>
						</div></li>
				</c:if>
				<!-- -----商品管理功能結束----- -->
				<!-- -----二手商品管理功能----- -->
				<c:if test="${accessVO.adminFunction ==2}">
					<li class="nav-item"><a class="nav-link collapsed" href="#"
						data-toggle="collapse" data-target="#collapsesecond"
						aria-expanded="true" aria-controls="collapsesecond"> <i
							class="fas fa-tag"></i> <span>二手估價管理</span>
					</a>
						<div id="collapsesecond" class="collapse"
							aria-labelledby="headingTwo" data-parent="#accordionSidebar">
							<div class="bg-white py-2 collapse-inner rounded">
								<h6 class="collapse-header">功能選單:</h6>
								<a class="collapse-item"
									href="<%=request.getContextPath()%>/back-end/secondhandrecycle/secondHandRecycleReview.jsp">二手估價審核</a>
							</div>
						</div></li>
				</c:if>
				<!-- -----二手商品管理功能結束----- -->
				<!-- -----採購管理功能----- -->
				<c:if test="${accessVO.adminFunction ==3}">
					<li class="nav-item"><a class="nav-link collapsed" href="#"
						data-toggle="collapse" data-target="#collapsereq"
						aria-expanded="true" aria-controls="collapsereq"> <i
							class="fas fa-dolly-flatbed"></i> <span>採購管理</span>
					</a>
						<div id="collapsereq" class="collapse"
							aria-labelledby="headingTwo" data-parent="#accordionSidebar">
							<div class="bg-white py-2 collapse-inner rounded">
								<h6 class="collapse-header">功能選單:</h6>
								<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/supplier/select_page.jsp">廠商管理</a> 
								<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/requisitions/select_page.jsp">採購單管理</a> 
							</div>
						</div></li>
				</c:if>
				<!-- 				-----採購管理功能結束----- -->
				<!-- 				-----會員管理功能----- -->
				<c:if test="${accessVO.adminFunction ==4}">
					<li class="nav-item"><a class="nav-link collapsed" href="#"
						data-toggle="collapse" data-target="#collapsemember"
						aria-expanded="true" aria-controls="collapsemember"> <i
							class="fas fa-user-circle"></i> <span>會員管理</span>
					</a>
						<div id="collapsemember" class="collapse"
							aria-labelledby="headingTwo" data-parent="#accordionSidebar">
							<div class="bg-white py-2 collapse-inner rounded">
								<h6 class="collapse-header">功能選單:</h6>
								<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/member/select_page.jsp">會員資料查詢</a>
							</div>
						</div></li>
				</c:if>
				<!-- 				-----會員管理功能結束----- -->
				<!-- 				-----討論區管理功能----- -->
				<c:if test="${accessVO.adminFunction ==5}">
					<li class="nav-item"><a class="nav-link collapsed" href="#"
						data-toggle="collapse" data-target="#collapseforum"
						aria-expanded="true" aria-controls="collapseforum"> <i
							class="fas fa-user-friends"></i> <span>討論區管理</span>
					</a>
						<div id="collapseforum" class="collapse"
							aria-labelledby="headingTwo" data-parent="#accordionSidebar">
							<div class="bg-white py-2 collapse-inner rounded">
								<h6 class="collapse-header">功能選單:</h6>
								<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/article/select_page.jsp">文章檢舉管理</a>
								
							</div>
						</div></li>
				</c:if>
				<!-- 				-----討論區管理功能結束----- -->
				<!-- 				-----管理員權限管理功能----- -->
				<c:if test="${accessVO.adminFunction ==6}">
					<li class="nav-item"><a class="nav-link collapsed" href="#"
						data-toggle="collapse" data-target="#collapseadmin"
						aria-expanded="true" aria-controls="collapseadmin"> <i
							class="fas fa-id-card"></i> <span>管理員資料管理</span>
					</a>
						<div id="collapseadmin" class="collapse"
							aria-labelledby="headingTwo" data-parent="#accordionSidebar">
							<div class="bg-white py-2 collapse-inner rounded">
								<h6 class="collapse-header">功能選單:</h6>
								<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/admin/select_page.jsp">管理員帳號管理</a>
								<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/access/select_page1.jsp">管理員權限管理</a>
							</div>
						</div></li>
				</c:if>
				<!-- 				-----管理員權限管理功能結束----- -->		
				<!-- 				-----FQ管理功能----- -->
				<!-- 				Nav Item - Utilities Collapse Menu -->
				<c:if test="${accessVO.adminFunction ==8}">
					<li class="nav-item"><a class="nav-link collapsed" href="#"
						data-toggle="collapse" data-target="#collapsefq"
						aria-expanded="true" aria-controls="collapsefq"> <i
							class="fas fa-question-circle"></i> <span>FQ管理</span>
					</a>
						<div id="collapsefq" class="collapse"
							aria-labelledby="headingUtilities"
							data-parent="#accordionSidebar">
							<div class="bg-white py-2 collapse-inner rounded">
								<h6 class="collapse-header">功能選單:</h6>
								<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/faqcontent/listAllFaqcontent.jsp">FQ頁面問題管理</a>
								<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/faqcontent/select_page.jsp">FQ頁面問題首頁</a>
								<a class="collapse-item" href="<%=request.getContextPath()%>/NameServlet?userName=admin">客服管理</a>
							</div>
						</div></li>
				</c:if>
			</c:forEach>
			<!-- -----FQ管理功能結束----- -->

			<!-- Divider -->
			<hr class="sidebar-divider">

		
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
					<!-- 				

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<!-- 					

        <!-- heading end -->
						<center>
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
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/back-end/admin/admin.do?action=getOne_For_Display&adminNo=${adminVO.adminNo}">
										<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
										管理員資訊
									</a>
							
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/back-end/admin/admin.do?action=getOne_For_Update&adminNo=${adminVO.adminNo}">
										<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
										修改資料
									</a>
			
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