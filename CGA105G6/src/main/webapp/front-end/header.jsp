<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shpe.model.*"%>
<%
MemVO memVO = (MemVO) session.getAttribute("memVO");
%>

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
<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" -->
<!-- 	crossorigin="anonymous"> -->

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


<!-- 0211新增 -->
<style>
.memPicture {
	width: 25px;
	height: 25px;
	position: relative;
}
.hellomem{
	font-size : 13px;
}
</style>


</head>
<body class="contact">

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
								<li>
								
								<a class="my-account" href="<%=request.getContextPath()%>/front-end/member/update_member_input.jsp"> <img
										class="btn-header"
										src="<%=request.getContextPath()%>/front-end/assets/img/icon/user.png"
										alt="會員專區" title="修改會員資料">
								</a>
								</li>
								<li>
								
								<a class="my-wishlist" href="<%=request.getContextPath()%>/front-end/product/FavoriteList.jsp"> <img
										class="btn-header"
										src="<%=request.getContextPath()%>/front-end/assets/img/icon/wish_list.png"
										alt="我的收藏" title="我的收藏">
								</a>
								
								</li>
								<li><a class="checkout"
									href="<%=request.getContextPath()%>/front-end/product/CartList.jsp">
										<img class="btn-header"
										src="<%=request.getContextPath()%>/front-end/assets/img/icon/shopping_cart_y.png"
										alt="購物車" title="購物車">
								</a></li>
									<c:if test="<%= memVO==null %>">
										<li><a class="login"
											href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp">
												<img class="btn-header"
												src="<%=request.getContextPath()%>/front-end/assets/img/icon/login.png"
												alt="我要登入" title="我要登入">
										</a></li>
									</c:if>
									<c:if test="<%= memVO!=null %>">
										<li><a class="logout" href="<%=request.getContextPath()%>/memServlet1.do?action=logout">
										<img class="btn-header" src="<%=request.getContextPath()%>/front-end/assets/img/icon/logout.png"
												alt="我要登出" title="登出">
										</a></li>
									</c:if>
								
								<!--0211新增 -->
								<c:if test="${memVO!=null}">
								<li><img class="memPicture"
									src="${pageContext.request.contextPath}/memServlet1.do?action=getPhoto&memNo=${memVO.memNo}"></li>
								<li class="hellomem"><strong>${memVO.memName}</strong> 你好!</li>
								<!--0211新增 -->
								</c:if>
								<c:if test="${memVO==null}">
<!-- 								<li><img class="memPicture" -->
<%-- 									src="${pageContext.request.contextPath}/memServlet1.do?action=getPhoto&memNo=${memVO.memNo}"></li> --%>
								<li class="hellomem"><strong>訪客</strong> 你好!</li>
								<!--0211新增 -->
								</c:if>
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
							<a href="<%=request.getContextPath()%>/front-end/product/index.jsp"><img
								class="logo"
								src="<%=request.getContextPath()%>/front-end/assets/img/icon/funplaylogo.png"
								alt="FUN" title="FUN電玩" /> </a>
						</div>
					</div>
					<div class="col-md-5 col-sm-6 hidden-xs">
						<div class="search-box">
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/ShopServlet.do">
                                    <input class="form-control search-form" type="text" placeholder="請輸入想要搜尋的商品或遊戲"name="itemName" />                                   
                                    <input type="hidden" name="action" value="serach_For_Product">
                                    <button class="search-button" value="Search" type="submit"><i class="fa fa-search"></i></button>
                             </form>
						</div>
					</div>
				<div class="col-md-3 col-xs-4 col-sm-6">
                     <div class="header-btn-bar">
                          <div class="shopping-cart">
                              <a href="<%=request.getContextPath()%>/front-end/product/CartList.jsp" class="btn-shopping-cart">
                                  <img src="${pageContext.request.contextPath}/front-end/assets/img/icon/shopping_cart.png" class="pic-shopping-cart"> 
                                    </a>                                
                                </div>
                                
                                <a href="<%=request.getContextPath()%>/OrderServlet.do?action=getOne_For_MemNo_Front_Display&memNo=${sessionScope.memNo}" class="btn-my-order">
                                <img src="${pageContext.request.contextPath}/front-end/assets/img/icon/list.png" title="我的訂單" class="pic-my-order">
                                </a>
                                
                                <a href="<%=request.getContextPath()%>/CusNameServlet?userName=${memVO.memAccount}" class="btn-support">
                                <img src="${pageContext.request.contextPath}/front-end/assets/img/icon/support.png" title="客服中心" class="pic-support">
                                </a>
                                 <a href="#" class="btn-affiche">
                                <img src="${pageContext.request.contextPath}/front-end/assets/img/icon/affiche.png" title="公告" class="pic-affiche">
                                </a>
                            </div>
                        </div>
				</div>
			</div>
		</div>
		<div class="main-menu-area hidden-xs hidden-sm">
			<div class="container">
				<div class="row">
					<div class="colo-md-12">
						<div class="main-menu">
							<ul class="drop-down-menu">
								
									<ul>
										<li><a
											href="<%=request.getContextPath()%>/front-end/product/index.jsp"
											class="drop-down-menu">PS專區</a></li>
										<li><a href="<%=request.getContextPath()%>/front-end/product/AllSwitch.jsp" class="drop-down-menu">NS專區</a></li>
										<li><a href="<%=request.getContextPath()%>/front-end/product/AllXbox.jsp" class="drop-down-menu">XBOX專區</a></li>
									</ul></li>
								<li><a href="#" class="drop-down-menu">商品列表</a>
									<ul>
										<li><a href="<%=request.getContextPath()%>/front-end/product/AllShop.jsp" class="drop-down-menu">PS專區</a>
											</li>
										<li><a href="<%=request.getContextPath()%>/front-end/product/AllSwitch.jsp" class="drop-down-menu">NS專區</a>
											</li>
										<li><a href="<%=request.getContextPath()%>/front-end/product/AllXbox.jsp" class="drop-down-menu">XBOX專區</a>
 											</li>
										
									</ul></li>
								<li>
								<li><a href="#" class="drop-down-menu">玩家專區</a>
									<ul>
										<li><a
											href="<%=request.getContextPath()%>/front-end/article/select_page.jsp"
											class="drop-down-menu">遊戲討論區</a></li>
										<li>
										<c:if test="<%=memVO!=null %>">
										<a
											href="<%=request.getContextPath()%>/FavoriteArt.do?action=listAllFavorite"
											class="drop-down-menu">收藏文章</a>
										</c:if>
										<c:if test="<%=memVO==null %>">
											
										<a href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp" class="drop-down-menu">收藏文章</a>
											</c:if>		
										</li>
											
										<li>
										<c:if test="<%=memVO!=null %>">
										<a
											href="<%=request.getContextPath()%>/FavoriteArt.do?action=myArt"
											class="drop-down-menu">我的文章</a>
										</c:if>
										<c:if test="<%=memVO==null %>">
											
										<a href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp" class="drop-down-menu">我的文章</a>
											</c:if>	
									    </li>
										<li>
										<c:if test="<%=memVO!=null %>">
										<a
											href="<%=request.getContextPath()%>/front-end/memMail/listAllMemMail.jsp"
											class="drop-down-menu">站內信件</a>
										</c:if>
										<c:if test="<%=memVO==null %>">
											
										<a href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp" class="drop-down-menu">站內信件</a>
											</c:if>	
									    </li>
									</ul>
								<li><a href="#" class="drop-down-menu">二手回收專區</a>
									<ul>
										<li>
										<c:if test="<%=memVO!=null %>">
										<a
											href="<%=request.getContextPath()%>/front-end/secondrecycle/secondHandRecycle.jsp"
											class="drop-down-menu">二手估價</a>
											</c:if>
											<c:if test="<%=memVO==null %>">
											
										<a href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp" class="drop-down-menu">二手估價</a>
											</c:if>	
											
											</li>
										<li>
										<c:if test="<%=memVO!=null %>">
										<a href="<%=request.getContextPath()%>/front-end/secondhandrecycle/SHRSListAll.jsp"
											class="drop-down-menu">二手估價進度查詢</a>
											</c:if>
											
										<c:if test="<%=memVO==null %>">
											
										<a href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp" class="drop-down-menu">二手估價進度查詢</a>
											</c:if>	
										</li>
									</ul></li>
								<li><a href="#" class="drop-down-menu">聯絡我們</a>
									<ul>
										<li><a
											href="<%=request.getContextPath()%>/CusNameServlet?userName=${memVO.memAccount}"
											class="drop-down-menu">聯繫客服</a></li>
										<li>
										<li>
										<c:if test="<%=memVO!=null %>">
										<a href="<%=request.getContextPath()%>/front-end/faqcontent/fq.jsp" class="drop-down-menu">常見問題</a>
											</c:if>
											
											<c:if test="<%=memVO==null %>">
											
										<a href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp" class="drop-down-menu">常見問題</a>
											</c:if>
											
											</li>
										<li>
									</ul></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
</header>