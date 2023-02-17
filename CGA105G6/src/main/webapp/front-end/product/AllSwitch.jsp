<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%@page import="com.member.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ include file="/front-end/header.jsp"%>
<%
ShopService shopSvc = new ShopService();
List<ShopVO> list = shopSvc.getAllGamePlatformNo(3);
pageContext.setAttribute("list", list);
%>




<body class="contact">
	<!-- shop-area-start -->
	<div class="shop-area">
		<div class="container " style="margin-bottom: 40px;">
			<div class="row">
				<div class=" col-xs-12">
					<div class="shop-right-col wow fadeIn" data-wow-duration=".5s"
						data-wow-delay=".5s">
						<div class="category-products">
							<div class="shop-category-product">

								<div class="row">
									<div class=" col-xs-12">
										<div class="category-product">
											<!-- Tab panes -->
											<div class="tab-content">
												<div role="tabpanel" class="tab-pane active fade in"
													id="gried_view">
													<!--將參數帶入產生產品列表 -->
													<%@ include file="/front-end/page1.file"%>
													<c:forEach var="shopVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
														<c:if test="${shopVO.itemState ==1}">
															<div class="col-md-4 col-sm-6 col-xs-12 mar-bot">
																<!-- single-product-start -->
																<div class="single-product">
																	<div class="single-product-img">
																		<a
																			href="<%=request.getContextPath()%>/ShopServlet.do?action=getOne_For_Display&serialNo=${shopVO.serialNo}">
																			<img
																			src="${pageContext.request.contextPath}/back-end/shop/shop.do?action=getPhoto&serialNo=${shopVO.serialNo}">
																		</a>
																	</div>
																	<div class="single-product-content">
																		<div class="product-title">
																			<h5>
																				<a href="#">${shopVO.itemName}</a>
																			</h5>
																		</div>
																		<div class="price-box">
																			<span class="price">價格$${shopVO.itemPrice}</span>
																		</div>

																		<div class="product-action">
																			<FORM METHOD="post"
																				ACTION="<%=request.getContextPath()%>/back-end/shop/shop.do"
																				style="margin-bottom: 0px;">
																				<button class="button btn btn-default add-cart"
																					type="submit" name="action" value="addItem"
																					onclick="addStandBy()" title="add to cart">放進購物車</button>
																				<input type="hidden" name="serialNo"
																					value="${shopVO.serialNo}"> <input
																					type="hidden" name="itemName"
																					value="${shopVO.itemName}"> <input
																					type="hidden" name="itemPrice"
																					value="${shopVO.itemPrice}"> <input
																					type="hidden" name="memNo"
																					value="${sessionScope.memNo}"> <input
																					type="hidden" name="itemSale" value="1">
																			</FORM>
																			<a class="add-wishlist"
																				href="<%=request.getContextPath()%>//ShopServlet.do?action=addItemToFavorite&serialNo=${shopVO.serialNo}&itemName=${shopVO.itemName}
										&itemPrice=${shopVO.itemPrice}&memNo=${sessionScope.memNo}&itemSale=1"
																				title="add to wishlist"> <i class="fa fa-heart"></i>
																			</a>
																		</div>
																	</div>
																</div>																
																<!-- single-product-end -->
															</div>
														</c:if>
													</c:forEach>
													
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
			<div class="container mx-auto"  style="width: 200px;">
				<div  class="mx-auto " style="width: 300px;">
					<%@ include file="/front-end/page2.file"%>
				</div>
			</div>
		</div>
	</div>
</body>								
<%@ include file="/front-end/footer.jsp"%>