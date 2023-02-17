<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shpe.model.*"%>
<%@ page import="com.title.model.*"%>
<%@ page import="com.function.model.*"%>

<%
AdminVO adminVO=(AdminVO)session.getAttribute("adminVO");
%>

<%@ include file="/back-end/header.jsp"%>

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">二手估價審核</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">

								<FORM METHOD="post">
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
										<tr>
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

										<c:choose>
										<c:when test="<%=adminVO.getAdminTitleNo()==1%>">
										<tr>
											<td>二手回收狀態:</td>
											<td>
											<select name="recycleState">
													<option value="1">審核中</option>
													<option value="2">審核完畢</option>
													<option value="3">撥款完畢</option>
													<option value="4">二手回收完成</option>
											</select>
											</td>
										</tr>
										</c:when>
										<c:when test="<%=adminVO.getAdminTitleNo()==2%>">
										<tr>
											<td>二手回收狀態:</td>
											<td>
											<select name="recycleState">
													<option value="2">審核完畢</option>
													<option value="3">撥款完畢</option>
											</select>
											</td>
										</tr>
										</c:when>
										<c:when test="<%=adminVO.getAdminTitleNo()==4%>">
										<tr>
											<td>二手回收狀態:</td>
											<td>
											<select name="recycleState">
													<option value="1">審核中</option>
													<option value="2">審核完畢</option>
											</select>
											</td>
										</tr>
										</c:when>
										<c:when test="<%=adminVO.getAdminTitleNo()==3%>">
										<tr>
											<td>二手回收狀態:</td>
											<td>
											<select name="recycleState">
													<option value="3">撥款完畢</option>
													<option value="4">二手回收完成</option>
											</select>
											</td>
										</tr>
										</c:when>
										<c:otherwise>
										    <td>沒有權限審核</td>
										</c:otherwise>
										</c:choose>
										<tr>
										<td style="display:none;">${acccessVO.titleVO.adminFunction}</td>
										</tr>
									</table>
									
									
									<input type="hidden" name="action" value="updateState">
									<input type="hidden" name="applicationNo" value="${shpeVO.applicationNo}"> 
									<input type="hidden" name="gameCode" value="${shpeVO.gameCode}"> 
									<input type="hidden" name="itemName" value="${shpeVO.itemName}">
									<input type="hidden" name="diskBox" value="${shpeVO.diskBox}">
									<input type="hidden" name="disk" value="${shpeVO.disk}">
									<input type="hidden" name="diskFrom" value="${shpeVO.diskFrom}">
									<input type="hidden" name="estimate" value="${shpeVO.estimate}">
									<input type="hidden" name="adminTitleNo" value="${titleVO.adminTitleNo}">
									<input type="hidden" name="adminFunction" value="${acccessVO.adminFunction}">
									<input type="hidden" name="adminTitleNo" value="${titleVO.adminTitleNo}">

									<input type="submit" value="送出修改">
								</FORM>

							</div>

							<div class="d-grid gap-2 d-md-flex justify-content-md-end">
							</div>
						</div>
					</div>
		
		<%@ include file="/back-end/footer.jsp"%>	