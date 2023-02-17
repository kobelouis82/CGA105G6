<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shpe.model.*"%>
<%@ page import="com.title.model.*"%>
<%@ page import="com.function.model.*"%>

<%
AdminVO adminVO=(AdminVO)session.getAttribute("adminVO");
ShpeService shpeSvc = new ShpeService();
List<ShpeVO> list = shpeSvc.selectRecycleInf();
pageContext.setAttribute("list", list);
%>

<%@ include file="/back-end/header.jsp"%>


					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">二手估價審核</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>申請單編號</th>
											<th>遊戲商品條碼</th>
											<th>商品名稱</th>
											<th>盒子狀況</th>
											<th>光碟狀況</th>
											<th>購買來源</th>
											<th>二手回收價</th>
											<th>二手流程進度</th>
											<th></th>
										</tr>
									</thead>
									<%@ include file="/back-end/page1.file" %>
									<c:forEach var="shpeVO" items="${list}" varStatus="statusName" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									
										<tbody>
											<tr>
												<td>${shpeVO.applicationNo}</td>
												<td>${shpeVO.gameCode}</td>
												<td>${shpeVO.itemName}</td>
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
												<%-- 												<td>${shpeVO.diskBox}</td> --%>
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
												<%-- 												<td>${shpeVO.disk}</td> --%>

												<c:choose>
													<c:when test="${shpeVO.diskFrom}">
														<td>他處購買</td>
													</c:when>
													<c:otherwise>
														<td>本店購買</td>
													</c:otherwise>
												</c:choose>
												<td>${shpeVO.estimate}</td>
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
												<td style="display:none;">${acccessVO.adminFunction}</td>

												<!-- 													<td><select name="recycleState"> -->
												<!-- 															<option value="1">審核</option> -->
												<!-- 															<option value="2">審核完畢</option> -->
												<!-- 															<option value="3">撥款完畢</option> -->
												<!-- 															<option value="4">二手回收完成</option> -->
												<!-- 													</select> -->
												<!-- 													</td> -->
												<!-- 													<td> -->						
												<td>		
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/shrrs.do">
														<input type="hidden" name="action" value="getRecycleState">
														<input type="hidden" name="applicationNo" value="${shpeVO.applicationNo}">
														<input type="hidden" name="adminTitleNo" value="${titleVO.adminTitleNo}">
														<input type="hidden" name="adminFunction" value="${acccessVO.adminFunction}">
<%-- 														<input type="hidden" name="gameCode" value="${shpeVO.gameCode}">	 --%>
<%-- 														<input type="hidden" name="itemName" value=" ${shpeVO.itemName}">	 --%>
<%-- 														<input type="hidden" name="diskBox" value="${shpeVO.diskBox}">	 --%>
<%-- 														<input type="hidden" name="disk" value=" ${shpeVO.disk}">	 --%>
<%-- 														<input type="hidden" name="diskFrom" value=" ${shpeVO.diskFrom}">	 --%>
<%-- 														<input type="hidden" name="estimate" value=" ${shpeVO.estimate}">																									 --%>
<%-- 														<input type="hidden" name="recycleState" value="${shpeVO.recycleState}"> --%>
															<input type="submit" value="修改狀態" class="btn btn-primary btn-sm" >
													</FORM>
												</td>






												<!-- 												<td> -->
												<!-- 													<form METHOD="post" -->
												<%-- 														action="<%=request.getContextPath()%>/shrrs.do"> --%>
												<!-- 														<input type="hidden" name="recycleState" -->
												<%-- 															value="${shpeVO.recycleState}"> <input --%>
												<!-- 															type="hidden" name="action" value="updateState"> -->
												<%-- 														<input id="checkbtn${s.count}" type="button" --%>
												<!-- 															class="btn btn-primary btn-sm" data-toggle="modal" -->
												<!-- 															data-target="#staticBackdrop" value="修改"> -->
												<%-- 																												<input id="checkbtn${s.count}" type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#staticBackdrop" value="審核" ${(shpeVO.recycleState==1)? "" : "disabled"} > --%>
												<!-- 													</form> -->
												<!-- 												</td> -->

											</tr>
										</tbody>
									</c:forEach>
								</table>
								<%@ include file="/back-end/page2.file" %>
								<div class="d-grid gap-2 d-md-flex justify-content-md-end">
									<!-- 									<button type="button" class="btn btn-primary" -->
									<!-- 										data-toggle="modal" data-target="#staticBackdrop"> -->
									<!-- 										送出審核</button> -->
									<!-- 									<button class="btn btn-primary" type="button">送出審核</button> -->
								</div>
							</div>
						</div>
					</div>
				

				<!-- /.container-fluid -->

<%@ include file="/back-end/footer.jsp"%>