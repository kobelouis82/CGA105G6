<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memMail.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ include file="/front-end/header.jsp"%>
<%
MemMailVO memMailVO = (MemMailVO) request.getAttribute("memMailVO");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>新增信件</title>
<style>
body {
	background-color: #4e5452;
	color: #4e5452;
}

div.left {
	margin-top: 20px;
}

div.right {
	background-color: #fff;
	margin-top: 40px;
	padding: 50px 50px;
	border-radius: 5px;
}

a.content {
	color: #80c344;
	font-size: 0.6em;
}

a.content:hover {
	color: #4B7F52;
}

table {
	width: 700px;
	margin: 30px auto;
	border: 1px solid #4e5452;
}

th, td {
	text-align: center;
	border: 1px solid #4e5452;
	padding: 10px 15px;
}

td.function {
	text-align: justify;
}

label.spotlight {
	background-color: #80c344;
	padding: 2px 5px;
	border-radius: 5px;
	color: #fff;
}

form {
	text-align: center;
}

textarea {
	resize: none;
}

input.confirm {
	background-color: #80c344;
	color: #4e5452;
	padding: 5px 10px;
	border-radius: 5px;
	border: none;
	font-weight: 999;
	margin: 0px 10px;
}

input.confirm:hover {
	background-color: #4B7F52;
	color: #80c344;
	cursor: pointer;
}
</style>
<style>
#container {
	padding: 10px;
	max-width: 250px;
	margin: 0px auto;
}

.align {
	display: inline;
	vertical-align: text-top;
}

#preview, .change {
	margin: 10px 0px;
}

img {
	max-width: 100%;
	margin: 10px;
}

.delete {
	display: none;
}
</style>
<center>
	<div class="container">
		<div class="row">
			<div class="right col">
				<h2>
					新增會員站內信&nbsp;<a class="content"
						href="<%=request.getContextPath()%>/front-end/memMail/listAllMemMail.jsp">回會員信件列表</a>
				</h2>
				<hr>
				<h5 style="color: #80c344;">${errorMsgs.notFound[0]}${errorMsgs.exception[0]}</h5>
				<h3>信件撰寫:</h3>

				<form method="post"
					action="<%=request.getContextPath()%>/memMail/memMail.do"
					enctype="multipart/form-data">
					<div class="asort">
						<jsp:useBean id="memSvc" class="com.member.model.MemService" />
						<%
						MemMailService memMailSvc = new MemMailService();
						List<MemMailVO> list = memMailSvc.getAll();
						Set<String> set = new HashSet<String>();
						for (MemMailVO mailVO : list) {
							if (mailVO.getRcptNo().equals(memVO.getMemNo())) {
								MemVO member = memSvc.getOneMem(mailVO.getSendNo());
								set.add(member.getMemNo().toString());
							}
							if (mailVO.getSendNo().equals(memVO.getMemNo())) {
								MemVO member = memSvc.getOneMem(mailVO.getRcptNo());
								set.add(member.getMemNo().toString());
							}
						}
						request.setAttribute("set", set);
						%>
						<input type="hidden" name="sendNo" value="${memVO.memNo}">
					</div>
					<div class="atitle">
						<select size="1" name="rcptNo" id="rcpt_no">
							<option value="99">--請選擇--</option>
							<c:forEach var="memVO" items="${memSvc.all}">
								<c:if test="${memVO.memNo == param.memNo}">
									<option value="${memVO.memNo}"
										${memVO.memNo == param.memNo || memVO.memNo == param.memNo? 'selected':''}>${memVO.memName}</option>
								</c:if>
							</c:forEach>
							<c:forEach var="rcptNo" items="${set}">
								<c:if test="${memVO.memNo != rcptNo}">
									<option value="${rcptNo}">
										${memSvc.getOneMem(Integer.valueOf(rcptNo)).memName}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div class="acontent">
						<label for="mailTitle">標題</label> <br>
						<h5 style="color: #80c344;">${errorMsgs.mailTitle[0]}</h5>
						<input name="mailTitle" class="mail_cont">${param.mailTitle.trim().isEmpty()? '':param.mailTitle.trim()}
					</div>
					<div>
						<label for="mailCont">內容</label> <br>
						<h5 style="color: #80c344;">${errorMsgs.mailCont[0]}</h5>
						<textarea name="mailCont" rows="10" cols="45" class="editor">${param.mailCont.trim().isEmpty()? '':param.mailCont.trim()}</textarea>
					</div>
					<input type="hidden" name="mailStat" value="0"> <input
						type="hidden" name="mailReadStat" value="0"> <input
						type="hidden" name="action" value="insert"> <input
						type="submit" value="發送" class="confirm">
				</form>
			</div>
		</div>
	</div>
</center>
<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.6.7/dist/sweetalert2.all.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.1.js"
	integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
	crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/ckeditor5/build/ckeditor.js"></script>
<script>
	ClassicEditor
    .create(document.querySelector('.editor'), {
        toolbar: {
            items: [
                'heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList',
                '|', 'alignment', 'outdent', 'indent', '|', 'fontColor',
                '|', 'blockQuote','undo', 'redo','imageUpload'
            ]
        }
    })
    .then(editor => {
        console.log(editor);
    });
</script>

<%@ include file="/front-end/footer.jsp"%>