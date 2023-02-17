<%@page import="com.admin.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String code = (String)request.getAttribute("code"); 
%>
<!-- 0207版面已調好 -->
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="121; 
url=<%=request.getContextPath()%>/back-end/adminForgetPassword/getPassword.jsp">
<title>驗證信已發送!</title>
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
	<!-- Outer Row -->
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
                                       <h4> 您好，驗證信已發送，請至信箱查收驗證碼：</h4>
											<form class="user" method="get"
												action="<%=request.getContextPath()%>/adminLogin.do">
												<div class="form-group">
												<input type="text" class="form-control form-control-user" 
                                        			name="confirmCode" aria-describedby="confirmCodeHelp"
                                        			placeholder="請輸入驗證碼">
												</div>
												<div class="row justify-content-center">
                                    			<div class="col-lg-6" >
												<input type="hidden" name="action" value="sendConfirmation">
												<input type="hidden" name="code" value="<%= code %>">
												<input id="1" type="submit" value="確認送出" class="btn btn-primary btn-user btn-block">
												</div>
												</div>
											</form>
												<div style="color: red">${errorMsgs}</div>
												<div class="row justify-content-center">
                                    			<div class="col-lg-6" >
											<form class="user" method="get"
												action="<%=request.getContextPath()%>/back-end/admin/adminLogin.jsp">
												<input id="cancel" type="submit" value="取消" class="btn btn-primary btn-user btn-block">
											</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
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
</body>
</html>