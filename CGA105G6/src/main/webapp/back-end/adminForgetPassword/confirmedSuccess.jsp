<%@page import="com.admin.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
// Integer adminno = (Integer) request.getAttribute("adminno");
//      String memMail = (String) session.getAttribute("memMail");
//     AdminService adminSvc = new AdminService();
//     AdminVO adminVO = adminSvc.getOneAdmin(adminno);
//      String code = (String) request.getAttribute("code");
//    session.setAttribute("memVO", memVO);
 --%>
<%-- <%=adminVO %> --%>
<%String password=(String)session.getAttribute("password"); %>
<!-- 0207版面已調好 -->
<html>
<head>
<meta http-equiv="refresh"
	  content="121; 
url=<%=request.getContextPath()%>/back-end/adminForgetPassword/passwordForgotten.jsp">
<title>信箱驗證成功頁面</title>
<!-- Custom fonts for this template-->
    <link href="<%=request.getContextPath()%>/back-end/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/back-end/assets/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-primary">
	<div class="container">
	 <div class="row justify-content-center">
			<div class="col-xl-10 col-lg-12 col-md-9">
				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-2 d-none d-lg-block "></div>
                            <div class="col-lg-8">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h4 class="h3 text-gray-900 mb-2">驗證碼已驗證成功</h4>
                                        <h4 class="h3 text-gray-900 mb-2">請牢記原密碼：</h4>
                                        <h1><%=password%></h1>
                                        <h4 class="h3 text-gray-900 mb-2">並至首頁重新登入！</h4>
									</div>
									<div class="text-center">
                                        <a class="small" href="<%=request.getContextPath()%>/back-end/admin/adminLogin.jsp">回登入頁面</a>
                                    </div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


</body>
</html>