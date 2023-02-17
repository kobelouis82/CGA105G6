<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/back-end/header.jsp"%>

<li><a href='listAllOrder.jsp'>查詢</a> 所有訂單 <br>
<br></li>
<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>

	<li>
		<FORM METHOD="post" ACTION="order.do">
			<b>輸入訂單編號 :</b> <input type="text" name="orderNo"> <input
				type="hidden" name="action" value="getOne_For_Display"> <input
				type="submit" value="送出">
		</FORM>
	</li>

	<jsp:useBean id="orderSvc" scope="page"
		class="com.order.model.OrderService" />

	<li>
		<FORM METHOD="post" ACTION="order.do">
			<b>選擇訂單編號:</b> <select size="1" name="orderNo">
				<c:forEach var="orderVO" items="${orderSvc.all}">
					<option value="${orderVO.orderNo}">${orderVO.orderNo}
				</c:forEach>
			</select> <input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="送出">
		</FORM>
	</li>
</ul>
<%@ include file="/back-end/footer.jsp"%>