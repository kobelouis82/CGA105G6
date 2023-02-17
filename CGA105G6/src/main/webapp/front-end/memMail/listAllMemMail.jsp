<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*" %>
<%@ page import="com.memMail.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ include file="/front-end/header.jsp"%>

<jsp:useBean id="memMailSvc" class="com.memMail.model.MemMailService" />
<jsp:useBean id="adminSvc" class="com.admin.model.AdminService" />
<jsp:useBean id="memberSvc"	class="com.member.model.MemService" />
<%
	MemMailService memMailSvc2 = new MemMailService();
	List<MemMailVO> list = memMailSvc2.getAll();
	Set<String> set = new HashSet<String>();
	for(MemMailVO mailVO : list){
		if(mailVO.getRcptNo().equals(memVO.getMemNo())){
			MemVO member = memberSvc.getOneMem(mailVO.getSendNo());
				set.add(member.getMemNo().toString());
		}
		if(mailVO.getSendNo().equals(memVO.getMemNo())){
			MemVO member = memberSvc.getOneMem(mailVO.getRcptNo());
				set.add(member.getMemNo().toString());
			}
		}
			
	request.setAttribute("set",set);
%>



<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
body{
	background-color: #4e5452;
	color: #4e5452;
}
div.left{
	margin-top: 20px;
}
div.right{
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
}
a.content{
	color: #80c344;
	font-size: 0.6em;
}
a.content:hover {
	color: #4B7F52;
}
input.confirm {
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}
input.confirm:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
#confirmTop:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
div.forSearchs{
	margin: 0 auto;
	width: 70%;
	hieght: 50px;
	position: relative;
}
div.forSearchsMore{
	top: 110%;
	left: 15%;
	width: 70%;
	position: absolute;
	background-color: #fff;
	box-shadow: 0 1px 5px 0 #4e5452;
	display: none
}
#mailCont{
	border-radius:5px;
	background-color:#eee;
	border:none;
	padding:5px 15px;
	width:50%;
}
span{
	 font-size:0.8em;
	 font-weight:444;
	 padding: 7px;
	 background-color: #eee;
	 border-radius:5px;
}
span:hover{
	cursor: pointer;
	background-color: #4e5452;
	color: #eee;
}
label, select, input {
	font-size: 0.8em;
}
table{
	width: 700px;
	margin: 30px auto;
/* 	border: 1px solid #4e5452; */
}
th, td{
	text-align: center;
/* 	border: 1px solid #4e5452; */
	padding: 10px 15px;
}
td.function{
	text-align: justify;	
}
label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}
input.change{
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
}
input.change:hover{
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
#focus{
	margin-right: -5px;
}
tr {
/* 	border-top: 1px solid #eee; */
	border-bottom: 2px solid #eee;
}
tr:hover {
	box-shadow: 0 1px 5px 0 #4e5452 inset;
	cursor: pointer;
}
</style>

<center>
<div class="container">
	<div class="row">
		<div class="right col">
			<h5 style="color: #80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
			<h3>�|�������H�C��&nbsp;
			<a class="content" href="<%=request.getContextPath()%>/front-end/memMail/addMemMail.jsp">�H�H</a>&nbsp;
			<a class="content" href="<%=request.getContextPath()%>/front-end/memMail/listAllMemMailSend.jsp">�H��ƥ��C��</a>
			</h3>
			<hr>
			
				<div style="text-align:center;font-weight:555;">
					<div style="width: 100px;display:inline-block;">�H��H</div>
					<div style="width: 150px;display:inline-block;">���D</div>
					<div style="width: 250px;display:inline-block;">�H�󤺮e</div>
					<div style="width: 100px;display:inline-block;">�H����</div>
				</div>
			<c:if test="${memMailVO != null}">
			<!-- insert�^�Ǫ�VO�S���H��s�� -->
			</c:if>
			<table>
			<tbody id="mailTable">
				<c:forEach var="memMailVO" items="${memMailSvc.all}">
					<c:if test="${memVO.memNo == memMailVO.rcptNo && memMailVO.mailStat == 0}">
					<tr>
						<td style="display:none;">${memMailVO.mailNo}</td>
						
						<td style="width: 100px">
						${memberSvc.getOneMem(memMailVO.sendNo).memName}
						</td>
						<td style="width: 150px">${memMailVO.mailTitle}</td>
						<c:set var="mailCont" value="${memMailVO.mailCont}" />
							<c:if test="${mailCont.length() > 10}">
								<td>${fn:substring(mailCont, 0, 10)}...</td>
							</c:if>
							<c:if test="${mailCont.length() <= 10}">
								<td>${mailCont}</td>
							</c:if>
						<td style="display:none;">${memMailVO.mailStat}</td>
						<td class="mailReadStat" style="display:none;">${memMailVO.mailReadStat}</td>
						<c:set var="mailTime" value="${memMailVO.mailTime}" />
							<td style="width: 100px">${fn:substring(mailTime, 0, 10)}</td>
					</tr>
					</c:if>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
</center>
<script>
	$("body").on("click","tr",function(e){
		let mailNo = e.currentTarget.children[0].innerText;
		window.location.href="<%=request.getContextPath()%>/memMail/memMail.do?mailNo="+ mailNo + "&action=read";
	});
	for (let i = 0; i < $(".mailReadStat").length; i++) {
		if ($(".mailReadStat")[i].innerText === '1') {
			$($(".mailReadStat")[i]).parent()[0].style.backgroundColor = '#eee';
		}
		if ($(".mailReadStat")[i].innerText === '0') {
			$($(".mailReadStat")[i]).parent()[0].style.fontWeight = '555';
		}
	}
	
	
	
</script>
<%@ include file="/front-end/footer.jsp"%>