<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*" %>
<%@ page import="com.memMail.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ include file="/front-end/header.jsp"%>

<jsp:useBean id="memMailSvc" class="com.memMail.model.MemMailService" />
<jsp:useBean id="memberSvc"	class="com.member.model.MemService" />
<%
	MemMailService memMailSvc2 = new MemMailService();
	List<MemMailVO> list = memMailSvc2.getAll();
	Set<String> set = new HashSet<String>();
	for(MemMailVO mailVO : list){
		if(mailVO.getRcptNo().equals(memVO.getMemNo())){
			MemVO member = memberSvc.getOneMem(mailVO.getSendNo());
				set.add(member.getMemNo().toString());}
		if(mailVO.getSendNo().equals(memVO.getMemNo())){
			MemVO member = memberSvc.getOneMem(mailVO.getRcptNo());
				set.add(member.getMemNo().toString());}
		}	
	request.setAttribute("set",set);
%>


<head>
<meta charset="UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/images/campionLogoIcon.png" type="image/png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<title>會員寄件備份列表</title>
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
}
th, td{
	text-align: center;
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
	border-bottom: 2px solid #eee;
}
tr:hover {
	box-shadow: 0 1px 5px 0 #4e5452 inset;
	cursor: pointer;
}
</style>

</head>
<center>
<div class="container">
	<div class="row">
		<div class="right col">
			<h5 style="color: #80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
			<h3>寄件備份&nbsp;
			<a class="content" href="<%=request.getContextPath()%>/front-end/memMail/addMemMail.jsp">寄信</a>&nbsp;
			<a class="content" href="<%=request.getContextPath()%>/front-end/memMail/listAllMemMail.jsp">收件夾</a>
			</h3>
			<hr>
			
				<div style="text-align:center;font-weight:555;">
<!-- 					<div style="width: 150px;display:inline-block;">寄件人</div> -->
					<div style="width: 150px;display:inline-block;">收件人</div>
					<div style="width: 250px;display:inline-block;">信件標題</div>
					<div style="width: 150px;display:inline-block;">寄件日期</div>
				</div>
			<table>
				<c:forEach var="memMailVO" items="${memMailSvc.all}">
				
					<c:if test="${memVO.memNo == memMailVO.sendNo && memMailVO.mailStat == 1}">
					<tr>
						<td style="display:none;">${memMailVO.mailNo}</td>
<%-- 						<td>${memberSvc.getOneMem(memMailVO.sendNo).memName}</td> --%>
						<td>${memberSvc.getOneMem(memMailVO.rcptNo).memName}</td>
						<td>${memMailVO.mailTitle}</td>
<%-- 						<c:set var="mailCont" value="${memMailVO.mailCont}" /> --%>
<%-- 							<c:if test="${mailCont.length() > 10}"> --%>
<%-- 								<td>${fn:substring(mailCont, 0, 10)}...</td> --%>
<%-- 							</c:if> --%>
<%-- 							<c:if test="${mailCont.length() <= 10}"> --%>
<%-- 								<td>${mailCont}</td> --%>
<%-- 							</c:if> --%>
						<td style="display:none;">${memMailVO.mailStat}</td>
						<td class="mailReadStat" style="display:none;">${memMailVO.mailReadStat}</td>
						<c:set var="mailTime" value="${memMailVO.mailTime}" />
							<td>${fn:substring(mailTime, 0, 10)}</td>
					</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
</center>
<script>
	$("tr").click(function(e){
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
	
	let countSearch = 0;
	$("span").click(function(e){
		countSearch++;
		if (countSearch % 2 == 1) {
			$("#forSearchsMore")[0].style.display="inline";
			$("#confirmTop")[0].setAttribute("disabled","");
			$("#confirmTop")[0].style.backgroundColor="#4B7F52";
			$("#confirmTop")[0].style.color="#80c344";
			$("#confirmTop")[0].style.cursor="context-menu";
		} else {
			$("#forSearchsMore")[0].style.display="none";
			$("#confirmTop")[0].removeAttribute("disabled");
			$("#confirmTop")[0].style.backgroundColor="#80c344";
			$("#confirmTop")[0].style.color="#4e5452";
		}
	});
	
</script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
$.datetimepicker.setLocale('zh');
$(function(){
	$('#mailTime').datetimepicker({
		format:'Y-m-d',
		maxDate:'+1970/01/01',
		timepicker:false
	});
});
</script>
<%@ include file="/front-end/footer.jsp"%>