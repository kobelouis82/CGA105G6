<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.goodsFig.model.*"%>
<%@ include file="/front-end/header.jsp"%>
<%
ShopVO shopVO = (ShopVO) request.getAttribute("shopVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<!-- 0205 引入bootstrap3.3 -->
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 0205 font awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
<!-- 	0205 CSS -->
<style>
	body{
		height: 100%;
	}
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

#itemDes {
	font-size: 20px;
}
</style>




												<!-- 此為商品單一下單頁面 -->
												<div class="container mt-10" style="margin-bottom: 230px; margin-top: 50px;">
												<div>
													<div class="col-md-6 picture">
														<img alt="" class="singleimg"
															src="${pageContext.request.contextPath}/back-end/shop/shop.do?action=getPhoto&serialNo=<%=shopVO.getSerialNo()%>">
													</div>
													<div class="col-md-6 info">
														<div class="itemName">
															產品名稱:<%=shopVO.getItemName()%></div>
														<div class="itemPrice">
															產品價格:<%=shopVO.getItemPrice()%></div>
														<div>
															<div class="form-group">

																<label id="itemDes">商品敘述:<%=shopVO.getItemDes()%></label>
																<div class="input-group">
																	<div class="input-group-btn">
																		<button id="down" class="btn btn-warning"
																			onclick=" down('1')">
																			<span class="glyphicon glyphicon-minus"></span>
																		</button>
																	</div>
																	<input type="text" id="myNumber"
																		class="form-control input-number" value="1" />
																	<div class="input-group-btn">
																		<button id="up" class="btn btn-warning"
																			onclick="up('5')">
																			<span class="glyphicon glyphicon-plus"></span>
																		</button>
																	</div>
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
													</div>
												</div>
											</div>
											
											<%@ include file="/front-end/footer.jsp"%>
	<!-- 0205加入 -->
	<script>
		function up(max) {
			document.getElementById("myNumber").value = parseInt(document
					.getElementById("myNumber").value) + 1;
			if (document.getElementById("myNumber").value >= parseInt(max)) {
				document.getElementById("myNumber").value = max;
			}
		}
		function down(min) {
			document.getElementById("myNumber").value = parseInt(document
					.getElementById("myNumber").value) - 1;
			if (document.getElementById("myNumber").value <= parseInt(min)) {
				document.getElementById("myNumber").value = min;
			}
		}

		let btn = document.getElementById('shoppingCart');
		btn.addEventListener('click', function(e) {
			console.log("23");

		});
	</script>
</body>
</html>
