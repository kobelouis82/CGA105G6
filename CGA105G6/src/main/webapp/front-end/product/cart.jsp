<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ include file="/back-end/header.jsp"%>
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
<style>
table#table-1 {
	background-color: darkgray;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

img {
	width: 150px;
	height: 150px;
}
</style>
					<table id="table-1">
						<tr>
							<td>
								<h3>購物車 - cart.jsp</h3>
								<h4>
									<a href="/CGA105G6/back-end/shop/listAllShop.jsp">回商品頁面</a>
								</h4>

							</td>
						</tr>
					</table>
			
					<table>
						<tr>
							<th>遊戲圖片</th>
							<th>遊戲名稱</th>
							<th>遊戲價錢</th>
							<th>數量</th>
							<th>更改數量</th>
							<th>總金額$</th>
							<th>刪除</th>

							<%@ include file="/front-end/page1.file"%>
							<c:forEach var="cartVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
							
							<tr>
							
								<td>
									<Img src="${pageContext.request.contextPath}/back-end/shop/shop.do?action=getPhoto&serialNo=${cartVO.serialNo}">
								</td>
								
							
									<td>${cartVO.itemName}</td>
									<td>${cartVO.itemPrice}</td>
									<td>${cartVO.itemSale}</td>
									
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back-end/shop/shop.do"
											style="margin-bottom: 0px;">
											<input type="hidden" name=serialNo value="${cartVO.serialNo}">
											
											<select size="1" name="itemSale">
												<option value=1>1</option>
												<option value=2>2</option>
												<option value=3>3</option>
												<option value=4>4</option>
												<option value=5>5</option>
											</select> <input type="submit" value="送出" > <input
												type="hidden" name="action" value="updateCount">
										</FORM>
									</td>
									<td class="totalPrice">${cartVO.itemPrice * cartVO.itemSale}</td>
									<td>

										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/back-end/shop/shop.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="刪除"> <input type="hidden"
												name=serialNo value="${cartVO.serialNo}"> <input
												type="hidden" name="action" value="removeOneItem">
										</FORM>
									</td>
								</tr>
							</c:forEach>						
					</table>										
					<%@ include file="/front-end/page2.file"%>
					<!-- /.container-fluid -->
					
					<Form action="<%=request.getContextPath()%>/OrderServlet.do" method="post">
				運送方式:
				<select size="1" name="orderShip">
						<option value=0>超商</option>
						<option value=1>宅配</option>
						<option value=2>郵寄</option>
				</select>
                            
                            
				付款方式:
				<select size="1" name="orderPay">
						<option value=0>信用卡付款</option>
						<option value=1>匯款</option>
						<option value=2>貨到付款</option>
				</select>
			
			<c:forEach var="cartVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
						<input id="price123" type="hidden" name=orderTotal >						
                        <input type="hidden" name=memNo value="1">
                        <input type="hidden" name=orderState value="0">
										<!--訂單明細新增 -->
						<input type="hidden" name=serialNo value="${cartVO.serialNo}">
						<input type="hidden" name=itemPrice value="${cartVO.itemPrice}">
						<input type="hidden" name=itemSale value="${cartVO.itemSale}">
						<input type="hidden" name=status value="0">	
                        <input type="hidden" name="action" value="insertOrder">
					</c:forEach>
					<input type="submit" value="結帳">
					</FORM>

	<script type="text/javascript">
		function show() {
			alert("添加成功");			
		}
	</script>
	<script type="text/javascript">
	
	var totalPrices = [];
	$('.totalPrice').each(function() {
	    totalPrices.push($(this).text());
	});
	let total = 0;
	for(let i of totalPrices){		
		total += Number(i);
	}
	$('#price123').val(total);
	</script>
	
<%@ include file="/back-end/footer.jsp"%>