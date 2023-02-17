<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%@page import="com.member.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ include file="/front-end/header.jsp"%>
<%
ShopService shopSvc = new ShopService();
List<ShopVO> list = shopSvc.getAllGamePlatformNo(1);
pageContext.setAttribute("list", list);
%>

<!-- 燈箱廣告區slider-start -->

<div>
	<a href="<%=request.getContextPath()%>/front-end/memLogin/login.jsp">
		<img src="3.png" class="d-block w-100 mx-auto headpic" alt="臥龍">
	</a>
</div>

<!-- <!-- banner-area-start -->
<div class="banner-area hidden-sm hidden-xs">
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-xs-12">
				<div class="single-banner wow fadeIn" data-wow-duration=".5s"
					data-wow-delay=".5s">
					<a href="<%=request.getContextPath()%>/ForumArticleServlet?action=getOne_For_Display&articleNo=5"> <img src="img/banner/newstest1.jfif" alt="" /> <span>武士道發表《哥布林殺手》《無職轉生》《超時空要塞》等多款人氣動畫改編遊戲</span>
					</a>
				</div>
			</div>
			<div class="col-md-4 col-xs-12">
				<div class="single-banner wow fadeIn" data-wow-duration=".5s"
					data-wow-delay=".5s">
					<a href="#"> <img src="img/banner/newstest2.jfif" alt="" /> <span>騰訊發布
							2023 年寒假暨春節假期未成年人遊戲限玩日曆 總共僅可遊玩 14 小時</span>
					</a>
				</div>
			</div>
			<div class="col-md-4 col-xs-12">
				<div class="single-banner wow fadeIn" data-wow-duration=".5s"
					data-wow-delay=".5s">
					<a href="#"> <img src="img/banner/newstest3.jfif" alt="" /> <span>《電馭叛客
							2077》開發商在美國遭集體訴訟 案件以 185 萬美元達成和解</span>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- <!-- banner-area-end -->

<!-- feature-area-start -->

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="section-heading">
				<h3>推薦商品</h3>
			</div>
		</div>
	</div>
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
												<%@ include file="page1.file"%>
												<c:forEach var="shopVO" items="${list}"
													begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
		<div class="container mx-auto" style="width: 200px;">
			<div class="mx-auto " style="width: 300px;">
				<%@ include file="page2.file"%>
			</div>
		</div>
	</div>
</div>
<script>
	var myCarousel = document.querySelector('#carouselExampleInterval')

	var carousel = new bootstrap.Carousel(myCarousel, {
		interval : 2000,
		wrap : true
	})
</script>
<%@ include file="/front-end/footer.jsp"%>