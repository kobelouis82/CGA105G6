<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/front-end/header.jsp" %>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.shpe.model.*"%>


<jsp:useBean id="SecondHandRecycle_ByCompositeQuery" scope="request" type="java.util.List<ShopVO>" />
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />




	<!-- heading-banner-start -->
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
				</div>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>				
				<table class="table table-bordered" id="dataTable" width="100%"
					cellspacing="0">
					<thead class="thead-dark">
						<tr>
							
							<th>遊戲商品條碼</th>
							<th>遊戲名稱</th>
							<th>遊戲價格</th>
							<th>選擇</th>
						</tr>
					</thead>
					
					<c:forEach var="shopVO" items="${SecondHandRecycle_ByCompositeQuery}">
						<tbody>
							<tr>
								
								<td>${shopVO.gameCode}</td>
								<td>${shopVO.itemName}</td>
								<td>${shopVO.itemPrice}</td>
								
								<td>
								<form METHOD="post" action="<%=request.getContextPath()%>/ShopServlet.do">																					
								<input type="hidden" name="action" value="selectItemInf">
								<input type="hidden" name="gameCode" value="${shopVO.gameCode}">
								<input class="btn btn-outline-secondary selectBtn" type="submit" value="查詢" style="border: 1px solid gray">								
								</form>
								</td>
								
							</tr>
							
						</tbody>
					</c:forEach>
				</table>
				
				

				 <a
					href="<%=request.getContextPath()%>/front-end/secondrecycle/secondHandRecycle.jsp"
					type="button" class="btn btn-outline-info ml-5"
					style="border: 1px solid green">重新查詢</a>
			</div>

		</div>
	</div>
	
<%@ include file="/front-end/footer.jsp" %>