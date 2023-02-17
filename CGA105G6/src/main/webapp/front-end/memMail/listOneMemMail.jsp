<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.memMail.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ include file="/front-end/header.jsp"%>
<% MemMailVO memMailVO = (MemMailVO)request.getAttribute("memMailVO"); %>


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


label.spotlight{
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}
form{
	text-align: center;
}


div.mail{
	text-align: left;
	margin: 50px auto;
	width: 70%;
}

input.confirm{
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
	margin: 0px 10px;
}
input.confirm:hover{
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
img.info{
	max-width:30%;
	margin: 3px;
}
</style>

<center>
<div class="container">
	<div class="row">
		<div class="right col">
			<h2>信件內容&nbsp;<a class="content" href="<%=request.getContextPath()%>/front-end/memMail/listAllMemMail.jsp">回會員站內信列表</a></h2>
			<hr>
			<h5 style="color:#80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
			<div class="mail">
			
			<jsp:useBean id="memMailSvc" class="com.memMail.model.MemMailService"/>
			<jsp:useBean id="memberSvc" class="com.member.model.MemService"/>
				<p>寄件人：&nbsp;
				${memberSvc.getOneMem(memMailVO.sendNo).memName}
				</p>
					
				<p>收件人：&nbsp;${memberSvc.getOneMem(memMailVO.rcptNo).memName}</p>
				<p>標題：</p>
				<p>${memMailVO.mailTitle.trim()}</p>	
				<p>內容：</p>
				<p>${memMailVO.mailCont.trim()}</p>
				<hr>
				<p style="font-size:0.5em;margin-left:70%;">發信時間：&nbsp;${memMailVO.mailTime}</p>
						
			</div>
			<form method="post" action="<%=request.getContextPath()%>/front-end/memMail/addMemMail.jsp">		
					<input type="hidden" name="mailNo" value="${memMailVO.mailNo}">
					<input type="hidden" name="sendNo" value="${memMailVO.sendNo}">
					<input type="hidden" name="rcptNo" value="${memMailVO.rcptNo}">
					<input type="submit" value="回覆" class="confirm">
			</form>
			
		</div>
	</div>
</div>
</center>
<%@ include file="/front-end/footer.jsp"%>