<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.goodsFig.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
ShopVO shopVO = (ShopVO) request.getAttribute("shopVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
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
			<h3>單一商品資料 - listOneShop.jsp</h3>
			<h4>
				<a href="select_page.jsp">回首頁</a>
			</h4>
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

	<tr>
		<td><%=shopVO.getSerialNo()%></td>
		<td><%=shopVO.getGameCode()%></td>
		<td><%=shopVO.getItemName()%></td>
		<td><%=shopVO.getItemPrice()%></td>

		<td><%=(shopVO.getGameClassNo() == 1) ? "動作類"
		: (shopVO.getGameClassNo() == 2) ? "射擊類"
				: (shopVO.getGameClassNo() == 3) ? "角色扮演類" : (shopVO.getGameClassNo() == 4) ? "格鬥類" : "激情類"%></td>

		<td><%=(shopVO.getGamePlatformNo() == 1) ? "PlayStation5"
		: (shopVO.getGamePlatformNo() == 2) ? "XBOX"
				: (shopVO.getGamePlatformNo() == 3) ? "SWITCH" : (shopVO.getGamePlatformNo() == 4) ? "PC" : "小拳王"%></td>

		<td><%=shopVO.getComNo()%></td>

		<td><%=(shopVO.getStatus() == 1) ? "現貨" : (shopVO.getStatus() == 2) ? "二手" : "預購"%></td>

		<td><%=shopVO.getItemDes()%></td>
		<td><%=shopVO.getInventoryStock()%></td>
		<td><%=shopVO.getPreorderQty()%></td>
		
		<td><%=(shopVO.getItemState() == 0) ? "下架" : "上架"%></td>
		<td><Img
			src="${pageContext.request.contextPath}/back-end/shop/shop.do?action=getPhoto&serialNo=<%=shopVO.getSerialNo()%>"></td>
		<td>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/back-end/shop/shop.do"
				style="margin-bottom: 0px;">
				<input type="submit" value="修改"> <input type="hidden"
					name="serialNo" value="${shopVO.serialNo}"> <input
					type="hidden" name="action" value="getOne_For_Update">
			</FORM>
		</td>
</table>
<%@ include file="/back-end/footer.jsp"%>