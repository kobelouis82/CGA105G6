<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shpe.model.*"%>

<%
ShpeVO shpeVO = (ShpeVO) request.getAttribute("shpeVO");
%>

<%@ include file="/back-end/header.jsp"%>

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">二手估價審核</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<table>
									<tr>
										<td>申請單編號:<font color=red><b>*</b></font></td>
										<td>${shpeVO.applicationNo}</td>
									</tr>
									<tr>
										<td>遊戲商品條碼:</td>
										<td>${shpeVO.gameCode}</td>
									</tr>
									<tr>
										<td>商品名稱:</td>
										<td>${shpeVO.itemName}</td>
									</tr>
									<td>盒子狀況:</td>
									         <c:choose>
												    <c:when test="${shpeVO.diskBox==5}">
														<td>非常好</td>
													</c:when>
													<c:when test="${shpeVO.diskBox==4}">
														<td>好</td>
													</c:when>
													<c:when test="${shpeVO.diskBox==3}">
														<td>普通</td>
													</c:when>
													<c:when test="${shpeVO.diskBox==2}">
														<td>差</td>
													</c:when>
													<c:otherwise>
														<td>很差</td>
													</c:otherwise>
												</c:choose>
									</tr>
									<tr>
										<td>光碟狀況:</td>
										<c:choose>
													<c:when test="${shpeVO.disk==5}">
														<td>非常好</td>
													</c:when>
													<c:when test="${shpeVO.disk==4}">
														<td>好</td>
													</c:when>
													<c:when test="${shpeVO.disk==3}">
														<td>普通</td>
													</c:when>
													<c:when test="${shpeVO.disk==2}">
														<td>差</td>
													</c:when>
													<c:when test="${shpeVO.disk==1}">
														<td>很差</td>
													</c:when>
													<c:otherwise>
														<td>資料錯誤</td>
													</c:otherwise>
												</c:choose>
									</tr>
									<tr>
										<td>購買來源:</td>
										<c:choose>
													<c:when test="${shpeVO.diskFrom}">
														<td>他處購買</td>
													</c:when>
													<c:otherwise>
														<td>本店購買</td>
													</c:otherwise>
												</c:choose>
									</tr>
									<tr>
										<td>二手回收價格:</td>
										<td>${shpeVO.estimate}</td>
									</tr>
									<tr>
										<td>二手回收狀態:</td>
										<c:choose>
													<c:when test="${shpeVO.recycleState==1}">
														<td>審核中</td>
													</c:when>
													<c:when test="${shpeVO.recycleState==2}">
														<td>審核完畢</td>
													</c:when>
													<c:when test="${shpeVO.recycleState==3}">
														<td>撥款完畢</td>
													</c:when>
													<c:when test="${shpeVO.recycleState==4}">
														<td>二手回收完成</td>
													</c:when>
													<c:otherwise>
														<td>資料錯誤</td>
													</c:otherwise>
												</c:choose>
									</tr>
								<tr>
									<td style="display:none;">
										<input type="hidden" name="adminFunction" value="${acccessVO.adminFunction}">
									</td>
                                </tr>
								</table>

								<br>
								<div>									
									<h4><a href="<%=request.getContextPath()%>/back-end/secondhandrecycle/secondHandRecycleReview.jsp">回首頁</a></h4>
								</div>
							</div>
							<div class="d-grid gap-2 d-md-flex justify-content-md-end">
							</div>
						</div>
					</div>
				</div>
		
		<%@ include file="/back-end/footer.jsp"%>	