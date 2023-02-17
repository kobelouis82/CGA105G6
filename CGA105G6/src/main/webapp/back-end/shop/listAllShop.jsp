<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%
ShopService shopSvc = new ShopService();
List<ShopVO> list = shopSvc.getAll();
pageContext.setAttribute("list", list);
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
			<h3>所有商品資料 - listAllShop.jsp</h3>
			<h4>
				<a href="select_page.jsp">回首頁</a>
			</h4>
			<h5>
				<a href="/CGA105G6/front-end/product/cart.jsp">查看購物車</a>
			</h5>
		</td>
	</tr>
</table>
<table>
	<tr>
		<th>遊戲流水編號</th>
		<th>遊戲商品條碼</th>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>遊戲類別編號</th>
		<th>遊戲平台編號</th>
		<th>遊戲公司編號</th>
		<th>遊戲狀態編號</th>
		<th>商品敘述</th>
		<th>商品庫存</th>
		<th>預購商品數量</th>
		<th>上架狀態</th>
		<th>商品圖片</th>
		<th>修改</th>		
	</tr>
	<%@ include file="/back-end/page1.file"%>
	<c:forEach var="shopVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">

		<tr>
			<td>${shopVO.serialNo}</td>
			<td>${shopVO.gameCode}</td>
			<td>${shopVO.itemName}</td>
			<td>${shopVO.itemPrice}</td>
			<td><c:choose>
					<c:when test="${shopVO.gameClassNo == 1}">動作類</c:when>
					<c:when test="${shopVO.gameClassNo == 2}">射擊類</c:when>
					<c:when test="${shopVO.gameClassNo == 3}">角色扮演類</c:when>
					<c:when test="${shopVO.gameClassNo == 4}">格鬥類</c:when>
					<c:when test="${shopVO.gameClassNo == 5}">激情類</c:when>
				</c:choose></td>
			<td><c:choose>
					<c:when test="${shopVO.gamePlatformNo == 1}">PlayStation5</c:when>
					<c:when test="${shopVO.gamePlatformNo == 2}">XBOX</c:when>
					<c:when test="${shopVO.gamePlatformNo == 3}">SWITCH</c:when>
					<c:when test="${shopVO.gamePlatformNo == 4}">PC</c:when>
					<c:when test="${shopVO.gamePlatformNo == 5}">小拳王</c:when>
				</c:choose></td>
			<td>${shopVO.comNo}</td>
			<td><c:choose>
					<c:when test="${shopVO.status == 1}">現貨</c:when>
					<c:when test="${shopVO.status == 2}">二手</c:when>
					<c:when test="${shopVO.status == 3}">預購</c:when>
				</c:choose></td>
			<td>${shopVO.itemDes}</td>
			<td>${shopVO.inventoryStock}</td>
			<td>${shopVO.preorderQty}</td>
			<td><c:choose>
					<c:when test="${shopVO.itemState == 1}">上架</c:when>
					<c:when test="${shopVO.itemState == 0}">下架</c:when>
				</c:choose></td>
			<td><Img
				src="${pageContext.request.contextPath}/back-end/shop/shop.do?action=getPhoto&serialNo=${shopVO.serialNo}"></td>

			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/back-end/shop/shop.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="serialNo" value="${shopVO.serialNo}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/back-end/page2.file"%>
<script type="text/javascript">
	function show() {
		alert("添加成功");
		shopping.submit();
	}
</script>

<%@ include file="/back-end/footer.jsp"%>