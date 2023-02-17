<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.goodsFig.model.*"%>
<%@ include file="/back-end/header.jsp"%>

<%
ShopVO shopVO = (ShopVO) request.getAttribute("shopVO");
%>

<center>
	<!-- Page Heading -->
	<FORM METHOD="post" ACTION="shop.do" name="form1"
		enctype="multipart/form-data">
		<table>
			<h3>資料新增:</h3>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<tr>
				<td>遊戲商品條碼:</td>
				<td><input type="TEXT" name="gameCode" size="45"
					value="<%=(shopVO == null) ? "12345678" : shopVO.getGameCode()%>" /></td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="itemName" size="45"
					value="<%=(shopVO == null) ? "商品名稱" : shopVO.getItemName()%>" /></td>
			</tr>
			<tr>
				<td>商品價格:</td>
				<td><input type="TEXT" name=itemPrice size="45"
					value="<%=(shopVO == null) ? "1300" : shopVO.getItemPrice()%>" /></td>
			</tr>
			<tr>
				<td>遊戲類別編號:</td>
				<td><select size="1" name="gameClassNo">
						<option value=1>動作類</option>
						<option value=2>射擊類</option>
						<option value=3>角色扮演類</option>
						<option value=4>格鬥類</option>
						<option value=5>激情類</option>
				</select></td>
			</tr>
			<tr>
				<td>遊戲平台編號:</td>
				<td><select size="1" name="gamePlatformNo">
						<option value=1>PlayStation5</option>
						<option value=2>XBOX</option>
						<option value=3>SWITCH</option>
						<option value=4>PC</option>
						<option value=5>小拳王</option>
				</select></td>
			</tr>
			
			<tr>
				<td>遊戲狀態:</td>
				<td><select size="1" name="status">
						<option value=1>現貨</option>
						<option value=2>二手</option>
						<option value=3>預購</option>
				</select></td>
			</tr>
			<tr>
				<td>遊戲公司編號:</td>
				<td><input type="TEXT" name="comNo" size="45"
					value="<%=(shopVO == null) ? "123" : shopVO.getComNo()%>" /></td>
			</tr>
			<tr>
				<td>商品敘述:</td>
				<td><input type="TEXT" name="itemDes" size="45"
					value="<%=(shopVO == null) ? "這是一款運作在Ps5上的遊戲!!" : shopVO.getItemDes()%>" /></td>
			</tr>
			<tr>
				<td>商品庫存:</td>
				<td><input type="TEXT" name="inventoryStock" size="45"
					value="<%=(shopVO == null) ? "0" : shopVO.getInventoryStock()%>" /></td>
			</tr>

			<tr>
				<td>預購商品數量:</td>
				<td><input type="TEXT" name="preorderQty" size="45"
					value="<%=(shopVO == null) ? "0" : shopVO.getPreorderQty()%>" /></td>
			</tr>

			<tr>
				<td>上架狀態:<font color=red><b>*</b></font></td>
				<td><select size="1" name="itemState">
						<option value=1>上架商品</option>
						<option value=0>下架商品</option>
				</select></td>
			</tr>
			<tr>
				<td>新增圖片:</td>
				<td><input type="file" name="upfile1"></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</center>
<%@ include file="/back-end/footer.jsp"%>