<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/back-end/header.jsp"%>

<li><a href='listAllOrderDetail.jsp'>查詢</a> 所有訂單明細<br>
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
		<FORM METHOD="post" action="<%=request.getContextPath()%>/OrderDetailServlet.do">
			<b>輸入訂單明細編號 :</b> <input type="text" name="orderNo"> <input
				type="hidden" name="action" value="getOne_For_Display"> <input
				type="submit" value="送出">
		</FORM>
	</li>

	<jsp:useBean id="orderDetailSvc" scope="page"
		class="com.orderDetail.model.OrderDetailService" />
	<li>
		<FORM METHOD="post" action="<%=request.getContextPath()%>/OrderDetailServlet.do">
			<b>選擇訂單明細編號:</b> <select size="1" name="orderNo">
				<c:forEach var="orderDetailVO" items="${orderDetailSvc.all}">
					<option value="${orderDetailVO.orderNo}">${orderDetailVO.orderNo}
				</c:forEach>
			</select> <input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="送出">
		</FORM>
	</li>
</ul>
<%@ include file="/back-end/footer.jsp"%>