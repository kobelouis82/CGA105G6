<%@page import="com.shop.model.ShopVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%@ page import="com.shpe.model.*"%>
<%@ page import="com.member.model.*"%>

<%
ShpeVO shpeVO = (ShpeVO) request.getAttribute("shpeVO");
ShopVO shopVO = (ShopVO) session.getAttribute("shopVO");
%>



<%@ include file="/front-end/header.jsp" %>

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
			<div class=" col-xs-12 "></div>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<FORM METHOD="post" ACTION="shr.do" enctype="form-data" name="form1">
				<div class=" col-xs-12 diskbox-radioGroup">
					<div class="diskboxOne col-1">會員編號 :</div>
					<div class="col-3">
						<input type="text" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-default" name="memNo"
							value="<%=memVO.getMemNo()%>" id="sentmemNo" disabled>
					</div>
				</div>
				<div class=" col-xs-12 diskbox-radioGroup">
					<div class="diskboxOne col-1">產品條碼 :</div>
					<div class="col-3">
						<input type="text" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-default" name="gameCode"
							value="<%=shopVO.getGameCode()%>" id="sentGameCode" disabled>
					</div>
				</div>
				<div class=" col-xs-12 diskbox-radioGroup">
					<div class="diskboxOne col-1">商品名稱 :</div>
					<div class="col-3">
						<input type="text" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-default" name="itemName"
							value="<%=shopVO.getItemName()%>" id="sentName" disabled>
					</div>
				</div>
				<div class=" col-xs-12 diskbox-radioGroup">
					<div class="diskboxOne col-1">新品價格 :</div>
					<div class="col-3">
						<input type="text" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-default" name="itemPrice"
							value="<%=shopVO.getItemPrice()%>" id="sentPrice" disabled>
					</div>
				</div>
				<!-- 這是盒況的radio -->
				<div class=" col-xs-12 diskbox-radioGroup">
					<div class="diskboxOne">盒子狀況 :</div>
					<div
						class="custom-control custom-radio custom-control-inline diskboxOne">
						<input type="radio" id="diskbox5" name="diskBox"
							class="custom-control-input" checked value="5"> <label
							class="custom-control-label" for="diskbox5">非常好</label>
					</div>
					<div
						class="custom-control custom-radio custom-control-inline diskboxOne">
						<input type="radio" id="diskbox4" name="diskBox"
							class="custom-control-input" value="4"> <label
							class="custom-control-label" for="diskbox4">好</label>
					</div>
					<div
						class="custom-control custom-radio custom-control-inline diskboxOne">
						<input type="radio" id="diskbox3" name="diskBox"
							class="custom-control-input" value="3"> <label
							class="custom-control-label" for="diskbox3">普通</label>
					</div>
					<div
						class="custom-control custom-radio custom-control-inline diskboxOne">
						<input type="radio" id="diskbox2" name="diskBox"
							class="custom-control-input" value="2"> <label
							class="custom-control-label" for="diskbox2">差</label>
					</div>
					<div
						class="custom-control custom-radio custom-control-inline diskboxOne">
						<input type="radio" id="diskbox1" name="diskBox"
							class="custom-control-input" value="1"> <label
							class="custom-control-label" for="diskbox1">很差</label>
					</div>
				</div>
				<!-- 這是片況的radio -->
				<div class=" col-xs-12 diskbox-radioGroup">
					<div class="diskboxOne">光碟狀況 :</div>
					<div
						class="custom-control custom-radio custom-control-inline diskboxOne">
						<input type="radio" id="disk5" name="disk"
							class="custom-control-input" checked value="5"> <label
							class="custom-control-label" for="disk5">非常好</label>
					</div>
					<div
						class="custom-control custom-radio custom-control-inline diskboxOne">
						<input type="radio" id="disk4" name="disk"
							class="custom-control-input" value="4"> <label
							class="custom-control-label" for="disk4">好</label>
					</div>
					<div
						class="custom-control custom-radio custom-control-inline diskboxOne">
						<input type="radio" id="disk3" name="disk"
							class="custom-control-input" value="3"> <label
							class="custom-control-label" for="disk3">普通</label>
					</div>
					<div
						class="custom-control custom-radio custom-control-inline diskboxOne">
						<input type="radio" id="disk2" name="disk"
							class="custom-control-input" value="2"> <label
							class="custom-control-label" for="disk2">差</label>
					</div>
					<div
						class="custom-control custom-radio custom-control-inline diskboxOne">
						<input type="radio" id="disk1" name="disk"
							class="custom-control-input" value="1"> <label
							class="custom-control-label" for="disk1">很差</label>
					</div>
				</div>
				<!-- 這是來源的radio -->
				<div class=" col-xs-12 diskbox-radioGroup">
					<div class="diskboxOne">購買來源 :</div>
					<div
						class="custom-control custom-radio custom-control-inline diskboxOne">
						<input type="radio" id="diskfrom5" name="diskFrom"
							class="custom-control-input" checked value="0"> <label
							class="custom-control-label" for="diskfrom5">本店購買</label>
					</div>
					<div
						class="custom-control custom-radio custom-control-inline diskboxOne">
						<input type="radio" id="diskfrom4" name="diskFrom"
							class="custom-control-input" value="1"> <label
							class="custom-control-label" for="diskfrom4">別店購買</label>
					</div>
				</div>
				<!-- 這是試算區塊 -->
				<div class=" col-xs-12 col-12 diskbox-radioGroup">
					<button type="button" class="btn btn-outline-secondary selectBtn "
						id="secondPrice" style="border: 1px solid gray">二手回收估價試算</button>


					<input type="text" class="form-control col-3 ml-2"
						aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-default" name="estimate"
						value="請點擊試算按鈕" id="estimate" disabled>
				</div>
				<!-- 這是提交/取消區塊 -->
				<div class=" col-xs-12 diskbox-radioGroup btnGroup">
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
						<input type="hidden" class="btn btn-outline-primary"
							style="border: 1px solid blue" name="action" value="recycleAdd">
						<input type="hidden" name="memNo" value="1"> <input
							type="submit" class="btn btn-outline-primary"
							style="border: 1px solid blue" value="送出申請" id="sent"> <a
							href="<%=request.getContextPath()%>/front-end/secondrecycle/secondHandRecycle.jsp"
							type="button" class="btn btn-outline-danger ml-5"
							style="border: 1px solid red">取消申請</a> <a
							href="<%=request.getContextPath()%>/front-end/secondrecycle/secondHandRecycle.jsp"
							type="button" class="btn btn-outline-info ml-5"
							style="border: 1px solid green">重新查詢</a>
					</div>
				</div>

			</form>

		</div>

	</div>
</div>



<%@ include file="/front-end/footer.jsp"%>

<script>
	$("#sent").click(function() {
		$("#sentmemNo").prop("disabled", false);
		$("#sentGameCode").prop("disabled", false);
		$("#sentName").prop("disabled", false);
		$("#sentPrice").prop("disabled", false);
		$("#estimate").prop("disabled", false);

	});

	$("#secondPrice")
			.click(
					function() {

						console.log("secondPrice");
						let price = document.getElementById("sentPrice").value;
						console.log(price);
						let diskbox = document
								.querySelector('input[name="diskBox"]:checked').value;
						// 							console.log(diskbox);
						let disk = document
								.querySelector('input[name="disk"]:checked').value;
						// 							console.log(disk);
						let diskfrom = document
								.querySelector('input[name="diskFrom"]:checked').value;
						console.log(diskfrom);
						let diskfromscole;
						if (diskfrom == 0) {
							diskfromscole = 5;
						} else {
							diskfromscole = 0;
						}
						// 							console.log(diskfromscole);
						let sum = parseInt(diskbox) + parseInt(disk)
								+ parseInt(diskfromscole);
						let estimate;
						if (sum > 10) {
							estimate = price * 0.6;
						} else if (10 >= sum > 6) {
							estimate = price * 0.5;
						} else {
							estimate = price * 0.4;
						}
						// 							console.log(sum);
						// 							console.log(estimate);

						document.getElementById("estimate").value = estimate;

					})
</script>
