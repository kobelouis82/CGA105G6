<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@page import="com.member.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Integer memNo = (Integer) session.getAttribute("memNo");
    String memMail = (String) session.getAttribute("memMail");
    MemService memSvc = new MemService();
    MemVO memVO = memSvc.getOneMem(memNo);
    String code = (String) request.getAttribute("code");
%>
<%int memID = memVO.getMemNo(); %>
<%String memEmail = memVO.getMemMail(); %>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>會員登入</title>

<!-- Custom fonts for this template-->
<link
	href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css"
	rel="stylesheet">
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

<body class="bg-gradient-primary">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-3 d-none d-lg-block "></div>
							<!-- <div class="col-lg-6 d-none d-lg-block bg-login-image"></div> -->
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">驗證信已發送，請至信箱查收驗證碼：</h1>
									</div>
									<br>
                    <strong style="color:red; font-size: 18px">${errorMsgs}</strong>
                    <br>        
                   <form method="post" action="<%=request.getContextPath()%>/memServlet1.do">
                        請輸入驗證碼: <input class="form-control" type="text" name="regpasschk"> <br>
                        <br>

                        <input type="hidden" name="action" value="regconfirm">
                        <input type="hidden" name="memNo" value="<%= memID %>">
                         <input type="hidden" name="code" value="<%= code %>">
                        <button id="1" class="btn btn-light" type="submit" style="float: right">確認並送出</button>
                    </form>
                    <button style="float: right; visibility: hidden">T</button>
                    <form method="post" action="<%=request.getContextPath()%>/memServlet1.do">
                        <input type="hidden" name="action" value="resend">
                        <input type="hidden" name="memMail" value="<%=memMail%>">
                        <input type="hidden" name="memNo" value="<%= memID %>">
                         <input type="hidden" name="code" value="<%= code %>">
                        <button id="2" class="btn btn-light" type="submit" style="float: right">重寄驗證信</button>
                    </form>
 					<a href="<%=request.getContextPath()%>/memServlet1.do?action=logout" style="text-decoration: none;">登出並返回首頁</a>
                </div>
								</div>
							</div>
							<div class="col-lg-3 d-none d-lg-block "></div>
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
	<script type="text/javascript">
		var a = document.getElementById("eye");
		var b = document.getElementById("pass");
		function showhide() {
			if (b.type == "password") {
				b.type = 'text';
				a.className = 'fa fa-eye-slash'
			} else {
				b.type = 'password';
				a.className = 'fa fa-eye'
			}
		}
	</script>
</body>

</html>