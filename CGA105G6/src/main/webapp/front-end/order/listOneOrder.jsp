<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ include file="/front-end/header.jsp"%>

<%
//EmpServlet.java(Concroller), 存入req的empVO物件
List<OrderVO> orderVOList = (List<OrderVO>) request.getAttribute("orderVO");
%>

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
																	<th>訂單編號</th>
																	<th>會員編號</th>
																	<th>訂單成立時間</th>
																	<th>訂單金額</th>
																	<th>訂單狀態</th>
																	<th>運送方式</th>
																	<th>付款方式</th>
																	<th>訂單明細</th>
																</tr>
															</thead>

															<c:forEach var="orderVO" items="<%=orderVOList%>">
																<tbody>
																	<tr>


																		<td>${orderVO.orderNo}</td>
																		<td>${orderVO.memNo}</td>
																		<td>${orderVO.orderTime}</td>
																		<td>${orderVO.orderTotal}</td>

																		<td><c:choose>
																				<c:when test="${orderVO.orderState == 0}">未出貨</c:when>
																				<c:when test="${orderVO.orderState == 1}">已出貨</c:when>
																				<c:when test="${orderVO.orderState == 2}">已完成訂單</c:when>
																				<c:when test="${orderVO.orderState == 3}">訂金已收</c:when>
																				<c:when test="${orderVO.orderState == 4}">訂金未收</c:when>
																				<c:when test="${orderVO.orderState == 5}">訂單作廢</c:when>
																				<c:when test="${orderVO.orderState == 4}">已完成訂單</c:when>
																				<c:when test="${orderVO.orderState == 6}">退貨</c:when>
																			</c:choose></td>
																		<td><c:choose>
																				<c:when test="${orderVO.orderShip == 0}">超商取貨</c:when>
																				<c:when test="${orderVO.orderState == 1}">宅配</c:when>
																				<c:when test="${orderVO.orderState == 2}">郵寄</c:when>
																			</c:choose></td>
																		<td><c:choose>
																				<c:when test="${orderVO.orderPay == 0}">信用卡</c:when>
																				<c:when test="${orderVO.orderPay == 1}">匯款</c:when>
																				<c:when test="${orderVO.orderPay == 2}">貨到付款</c:when>
																			</c:choose></td>
																		<jsp:useBean id="orderDetailSvc" scope="page"
																			class="com.orderDetail.model.OrderDetailService" />
																		<td>
																			<FORM METHOD="post"
																				action="<%=request.getContextPath()%>/OrderDetailServlet.do">
																				<input type="hidden" name="orderNo"
																					value="${orderVO.orderNo}"> <input
																					type="hidden" name="action"
																					value="getOneFront_For_Display"> <input
																					type="submit" value="送出">
																			</FORM>
																		</td>
																	</tr>
																</tbody>
															</c:forEach>
														</table>
													</div>

												</div>
											</div>
										</div>

									</div>
									<%@ include file="/front-end/footer.jsp"%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>