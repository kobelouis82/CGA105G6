<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>



<!-- Bootstrap CSS onlineCDN -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">

<%@ include file="/front-end/header.jsp" %>

<div class="heading-banner">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<div class="breadcrumb">
					<a title="Return to Home" href="index.html"> <i
						class="icon-home"></i>
					</a> <span class="navigation-page"> <span
						class="navigation-pipe">></span> 二手估價
					</span>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- heading-banner-end -->
<!-- shop-area-start -->
<div class="shop-area ">
	<div class="container ">
		<div class="row ">
			<div class=" col-xs-12 ">
				<form METHOD="post" action="<%=request.getContextPath()%>/ShopServlet.do">
					<b>請輸入產品條碼 :</b> 
					<div class="input-group input-select mb-3 mx-auto">						
						<input type="text" class="form-control col-10"
							placeholder="請輸入產品條碼" name="gameCode"> 
						<input type="hidden" name="action" value="selectItemInf">
						<div class="input-group-append">
						<input class="btn btn-outline-secondary selectBtn" type="submit"
								value="查詢" style="border: 1px solid gray">
						</div>
					</div>

					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
				</form>

			</div>

			<div class=" col-xs-12 ">
				<form METHOD="post" action="<%=request.getContextPath()%>/ShopServlet.do">
				<b>請輸入遊戲名稱 :</b> 
					<div class="input-group input-select mb-3 mx-auto">
						<input type="text" class="form-control col-10"
							placeholder="請輸入遊戲名稱，也可以查詢全部" name="item_Name"> <input
							type="hidden" name="action" value="SecondHandRecycle_ByCompositeQuery">
						<div class="input-group-append">
							<input class="btn btn-outline-secondary selectBtn" type="submit"
								value="查詢" style="border: 1px solid gray">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- shop-area-end -->

<%@ include file="/front-end/footer.jsp" %>