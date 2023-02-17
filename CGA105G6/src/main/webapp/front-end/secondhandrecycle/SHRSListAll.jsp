<%@page import="com.member.model.MemVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shpe.model.*"%>
<%@ include file="/front-end/header.jsp" %>
<%
Integer memNo = memVO.getMemNo();
ShpeService shpeSvc = new ShpeService();
List<ShpeVO> list = shpeSvc.selectRecycleMem(memNo);
pageContext.setAttribute("list", list);

%>






<div class="heading-banner">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<div class="breadcrumb">
					<a title="Return to Home" href="index.html"> <i
						class="icon-home"></i>
					</a> <span class="navigation-page"> <span
						class="navigation-pipe"></span> 二手估價進度查詢
					</span>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- heading-banner-end -->
<!-- shop-area-start -->
<div class="card shadow mb-4">
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable" width="100%"
				cellspacing="0">
				<thead class="thead-dark">
					<tr>
						<th>申請單編號</th>
						<th>商品名稱</th>
						<th>盒子狀況</th>
						<th>光碟狀況</th>
						<th>購買來源</th>
						<th>二手回收價格</th>
						<th>二手流程進度</th>
					</tr>
				</thead>
				<%@ include file="/front-end/page1.file" %>
				<c:forEach var="shpeVO" items="${list}" varStatus="statusName" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tbody>
						<tr>
							<td>${shpeVO.applicationNo}</td>
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
								<c:otherwise>
									<td>很差</td>
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
								<c:otherwise>
									<td>二手回收完成</td>
								</c:otherwise>
							</c:choose>

						</tr>
					</tbody>
				</c:forEach>
			</table>
			<%@ include file="/front-end/page2.file" %>
			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			</div>
		</div>
	</div>
</div>

<%@ include file="/front-end/footer.jsp" %>