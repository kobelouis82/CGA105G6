<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shpe.model.*"%>



<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8">
<title>FUN購物</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="keywords" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Mobile Specific Meta  -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<!-- Google Fonts -->
<link
	href='https://fonts.googleapis.com/css?family=Lato:400,300,700,900,100'
	rel='stylesheet' type='text/css'>
<!-- Bootstrap CSS onlineCDN -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/assets/css/bookstrapchange.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/assets/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/assets/css/font-awesome.min.css" />
<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/front-end/assets/css/style.css"
	rel="stylesheet" type="text/css">
<link href="favicon.ico" rel="shortcut icon">

<!-- Bootstrap CSS onlineCDN -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
	crossorigin="anonymous"></script>



<style>
.pic {
	width: 300px;
	height: 500px;
}

.memPicture {
	width: 25px;
	height: 25px;
	position: relative;
}

.hellomem {
	font-size: 13px;
}
</style>
<header>
	<div class="header-top">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-xs-12 col-sm-12">
					<div class="header-top-left">
						<div class="welcome-msg">
							<span class="default-msg hidden-xs"> <img
								class="default-msg hidden-xs"
								src="<%=request.getContextPath()%>/front-end/assets/img/icon/logo_small.png">FUN電玩
							</span>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-xs-12 col-sm-12">
					<div class="header-top-right">
						<ul class="header-links hidden-xs">
							<li><a class="my-account" href="#"> <img
									class="btn-header"
									src="<%=request.getContextPath()%>/front-end/assets/img/icon/user.png"
									alt="會員專區" title="會員專區">
							</a></li>
							<li><a class="my-wishlist" href="#"> <img
									class="btn-header"
									src="<%=request.getContextPath()%>/front-end/assets/img/icon/wish_list.png"
									alt="我的收藏" title="我的收藏">
							</a></li>
							<li><a class="checkout" href="#"> <img
									class="btn-header"
									src="<%=request.getContextPath()%>/front-end/assets/img/icon/shopping_cart_y.png"
									alt="購物車" title="購物車">
							</a></li>
							<c:choose>
								<c:when test="${memVO==null} ">
									<li><a class="login"
										href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp">
											<img class="btn-header"
											src="<%=request.getContextPath()%>/front-end/assets/img/icon/login.png"
											alt="我要登入" title="我要登入">
									</a></li>
								</c:when>
								<c:when test="${memVO!=null}">
									<li><a class="logout"
										href="<%=request.getContextPath()%>//memServlet1.do?action=logout">
											<img class="btn-header"
											src="<%=request.getContextPath()%>/front-end/assets/img/icon/logout.png"
											alt="我要登出" title="登出">
									</a></li>
								</c:when>
							</c:choose>

							<!--0211新增 -->
							<li><img class="memPicture"
								src="/0211_CGA105G6/front-end/S__155648009.jpg"></li>
							<li class="hellomem"><strong>${memVO.memName}</strong> 你好!</li>
							<!--0211新增 -->

						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="header-middle">
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-sm-12 col-xs-8">
					<div class="logo">
						<a href="<%=request.getContextPath()%>/front-end/main.jsp"><img
							class="logo"
							src="<%=request.getContextPath()%>/front-end/assets/img/icon/funplaylogo.png"
							alt="FUN" title="FUN電玩" /> </a>
					</div>
				</div>
				<div class="col-md-5 col-sm-6 hidden-xs"></div>
				<div class="col-md-3 col-xs-4 col-sm-6">
					<div class="header-btn-bar">
						<div class="btn-my-order">
							<a href="#" class="btn-my-order"> <img
								src="<%=request.getContextPath()%>/front-end/assets/img/icon/list.png"
								class="pic-my-order">
							</a> <a href="#" class="btn-support"> <img
								src="<%=request.getContextPath()%>/front-end/assets/img/icon/support.png"
								class="pic-support">
							</a> <a href="#" class="btn-affiche"> <img
								src="<%=request.getContextPath()%>/front-end/assets/img/icon/affiche.png"
								class="pic-affiche">
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>
<body>
	<div class="container">
		<div class="">

			<div id="carouselExampleInterval" class="carousel slide"
				data-bs-ride="carousel">

				<div class="carousel-inner">
					<div class="carousel-item active" data-bs-interval="10000">
						<a
							href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp">
							<img
							src="/0211_CGA105G6/front-end/040198003414%E8%87%A5%E9%BE%8D.jpg"
							class="d-block w-50 pic mx-auto" alt="臥龍">
						</a>
					</div>
					<div class="carousel-item" data-bs-interval="2000">
						<a
							href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp">
							<img src="/0211_CGA105G6/front-end/00045496598969寶可夢紫.jpg"
							class="d-block w-50 pic mx-auto" alt="寶可夢紫">
						</a>
					</div>
					<div class="carousel-item">
						<a
							href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp">
							<img src="/0211_CGA105G6/front-end/47875103528決勝時刻：現代戰爭2.jpg"
							class="d-block w-50 pic mx-auto" alt="決勝時刻：現代戰爭2">
						</a>
					</div>
				</div>

				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleInterval" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</div>
	</div>
</body>
<%@ include file="/front-end/footer.jsp"%>
<script>
	var myCarousel = document.querySelector('#carouselExampleInterval')

	var carousel = new bootstrap.Carousel(myCarousel, {
		interval : 2000,
		wrap : true
	})
</script>