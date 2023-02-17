<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ include file="/front-end/header.jsp"%>
<%
CartService cartService = new CartService();
List<CartVO> list = cartService.getAll();
pageContext.setAttribute("list", list);

ShopService shopSvc = new ShopService();
List<ShopVO> list1 = shopSvc.getAll();
pageContext.setAttribute("list1", list1);
ShopVO shopVO = (ShopVO) request.getAttribute("shopVO");
CartVO cartVO = (CartVO) request.getAttribute("cartVO");
%>
<link href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css"
	rel="stylesheet">
<style>
.picture {
	height: 500px;
	/* 	  background-color : green; */
	/* 	border: 1px solid black; */
}

.info {
	height: 500px;
	/* 	background-color: blue; */
}

.singleimg {
	max-height: 80%;
	margin: 50px 5px;
}

.itemName {
	margin: 30px;
	font-size: 25px;
	font-weight: bold;
}

.itemPrice {
	font-size: 25px;
	margin: 30px;
	font-weight: bold;
}

.form-group {
	/* 	width: 30%; */
	margin: 30px;
}

.breadcrumb {
	padding: 8px 15px;
	margin-bottom: 20px;
	list-style: none;
	background-color: rgba(0, 0, 0, 0.1);
	border-radius: 4px;
}

.itemNumber {
	font-size: 25px;
	margin-bottom: 30px
}

.shopping {
	margin: 30px;
}

img {
	width: 150px;
	height: 150px;
}

body {
	width: 100% !important;
	overflow-x: hidden !important;
}

#displayTotal {
	font-family: ‘cwTeXYen’, sans-serif;	
	color: red;
	font-size: 30px;
}
</style>


<!-- shop-area-start -->
<div class="shop-area">
	<div class="container">
		<div class="row">

			<div class=" col-xs-12">
				<div class="shop-right-col wow fadeIn" data-wow-duration=".5s"
					data-wow-delay=".5s">
					<div class="category-products">

						<div class="shop-category-product">
							<div class="row">
							<div class="col-12">
								<div class="category-product">
									<!-- Tab panes -->
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane active fade in"
											id="gried_view">

											<div class="card shadow mb-4">
												<div class="card-body">
													<div class="table-responsive">
														<table class="table table-bordered" id="dataTable"
															width="100%" cellspacing="0">
															<thead class="thead-dark">
																<tr>
																	<th>遊戲圖片</th>
																	<th>遊戲名稱</th>
																	<th>遊戲價錢</th>
																	<th>數量</th>
																	<th>更改數量</th>
																	<th>總金額$</th>
																	<th>刪除</th>
																</tr>
															</thead>
															<%@ include file="/front-end/page1.file"%>
															<c:forEach var="cartVO" items="${list}"
																begin="<%=pageIndex%>"
																end="<%=pageIndex+rowsPerPage-1%>">
																<tbody>
																	<tr>

																		<td><Img
																			src="${pageContext.request.contextPath}/back-end/shop/shop.do?action=getPhoto&serialNo=${cartVO.serialNo}">
																		</td>


																		<td>${cartVO.itemName}</td>
																		<td>${cartVO.itemPrice}</td>
																		<td>${cartVO.itemSale}</td>

																		<td>
																			<FORM METHOD="post"
																				ACTION="<%=request.getContextPath()%>/back-end/shop/shop.do"
																				style="margin-bottom: 0px;">
																				<input type="hidden" name=serialNo
																					value="${cartVO.serialNo}"> <select
																					size="1" name="itemSale">
																					<option value=1>1</option>
																					<option value=2>2</option>
																					<option value=3>3</option>
																					<option value=4>4</option>
																					<option value=5>5</option>
																				</select> <input type="submit" value="送出"> <input
																					type="hidden" name="action" value="updateCount">
																			</FORM>
																		</td>
																		<td class="totalPrice">${cartVO.itemPrice * cartVO.itemSale}</td>
																		<td>

																			<FORM METHOD="post"
																				ACTION="<%=request.getContextPath()%>/back-end/shop/shop.do"
																				style="margin-bottom: 0px;">
																				<input type="submit" value="刪除"> <input
																					type="hidden" name=serialNo
																					value="${cartVO.serialNo}"> <input
																					type="hidden" name="action" value="removeOneItem">
																			</FORM>
																		</td>
																	</tr>
																</tbody>
															</c:forEach>
														</table>
														<span id="displayTotal">小記NT$</span>	
														<div>
															<Form
																action="<%=request.getContextPath()%>/OrderServlet.do"
																method="post">
																運送方式: <select size="1" name="orderShip">
																	<option value=0>超商取貨</option>
																	<option value=1>宅配</option>
																	<option value=2>郵寄</option>
																</select> 付款方式: <select size="1" name="orderPay">
																	<option value=0>信用卡付款</option>
																	<option value=1>匯款</option>
																	<option value=2>貨到付款</option>
																</select>

																<c:forEach var="cartVO" items="${list}"
																	begin="<%=pageIndex%>"
																	end="<%=pageIndex+rowsPerPage-1%>">
																	<input id="price123" type="hidden" name=orderTotal>
																	<input type="hidden" name=memNo
																		value="${sessionScope.memNo}">
																	<input type="hidden" name=orderState value="0">
																	<!--訂單明細新增 -->
																	<input type="hidden" name=serialNo
																		value="${cartVO.serialNo}">
																	<input type="hidden" name=itemPrice
																		value="${cartVO.itemPrice}">
																	<input type="hidden" name=itemSale
																		value="${cartVO.itemSale}">
																	<input type="hidden" name=status value="0">
																	<input type="hidden" name="action" value="toMoneyPay">
																</c:forEach>
																<input type="submit" value="結帳">
															</FORM>
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
			</div>
		</div>
	</div>
</div>


<%@ include file="/front-end/footer.jsp"%>