<%@page import="com.member.model.*"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ page import="com.forumArticlePhoto.model.*"%>
<%@ page import="com.forumArticleMessage.model.*"%>
<%@ page import="com.favoriteArticle.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="memberSvc" class="com.member.model.MemService" />
<%
ForumArticleVO forumArticleVO = (ForumArticleVO) request.getAttribute("forumArticleVO");
ForumArticleMessageService ForumArticleMessageSvc = new ForumArticleMessageService();
Integer articleNo = forumArticleVO.getArticleNo();
List<ForumArticleMessageVO> list = ForumArticleMessageSvc.getByArticle(articleNo);
pageContext.setAttribute("list", list);
ForumArticlePhotoService ForumArticlePhotoSvc = new ForumArticlePhotoService();
List<ForumArticlePhotoVO> forumArticlePhotoVOs = (List<ForumArticlePhotoVO>) request.getAttribute("forumArticlePhotoVO");
%>
<% 
	boolean like_status=false; // 設一個布林值變數為like_status為false
	boolean collection_status=false;
	int ajax_mbr_no = 0;
	MemVO memberVO = (MemVO)session.getAttribute("memberVO");
	//如果memberVO不為空，代表他有登入，接著查詢他是否對這篇文章按過讚
	if(memberVO!=null){
		ajax_mbr_no = memberVO.getMemNo();
		
		
		ForumFavoriteArticleService article_collectionSvc = new ForumFavoriteArticleService();
		//取得我收藏過的清單
		List<ForumFavoriteArticleVO> my_collection_list = article_collectionSvc.getOneAllFavorite(memberVO.getMemNo());
		//從我收藏過的清單搜尋有沒有這篇文章
		for(ForumFavoriteArticleVO element2 : my_collection_list){
			if(element2.getArticleNo()==forumArticleVO.getArticleNo()){
				collection_status=true;
			}
		};
		
	}
	if(memberVO==null){
		ajax_mbr_no=0;
	}
	pageContext.setAttribute("collection_status", collection_status);
	pageContext.setAttribute("ajax_mbr_no", ajax_mbr_no);
%>
<%@ include file="/front-end/header.jsp"%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>文章查詢結果2</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<style>
.none {
	height: 80px;
}
.collection{ 
 background-image:url('/CGA105G6/images/bookmark_notyet.png');
   width:40px; 
   height:40px;
   border:0px; 
   background-size: 40px 40px; 
   background-repeat: no-repeat;
   float:right;
   margin-right: 55px; 
   } 
    .uncollection{ 
   background-image:url('/CGA105G6/images/bookmark_already.png'); 
   width:40px; 
   height:40px; 
   border:0px; 
   background-size: 40px 40px; 
   background-repeat: no-repeat;
   float:right;
   margin-right: 55px;
   }
.title {
	width: 100%;
	height: 40px;
	background-color: black;
	position: relative;
	left: 50%;
	transform: translate(-50%);
	display: flex;
	align-items: center;
	padding: 0 10px;
}

#sort {
	color: white;
	font-size: 24px;
	font-weight: 700;
}

.author_block {
	width: 100%;
	height: 25px;
	position: relative;
	left: 50%;
	transform: translate(-50%);
	padding: 0 20px;
}

#autBlock {
	color: gray;
	font-weight: 500;
}

.row {
	margin: 0;
}

.col-9, .col-3 {
	padding: 10px 0;
}

.popular {
	text-align: right;
}

.like {
	font-size: 12px;
	color: lightcoral;
}

.dislike {
	font-size: 12px;
	color: gray;
}

.ptime {
	padding: 0;
	font-size: 8px;
	color: rgb(137, 137, 137);
	margin-left: 20px;
}

hr {
	width: 95%;
	position: relative;
	left: 50%;
	transform: translate(-50%);
}

.content {
	width: 100%;
	min-height: 200px;
	position: relative;
	left: 50%;
	transform: translate(-50%);
	padding: 0 20px;
}

.comment {
	width: 100%;
	position: relative;
	left: 50%;
	transform: translate(-50%);
	background-color: rgb(230, 230, 230);
	align-items: center;
	padding: 15px;
	border-radius : 50px;
	margin-bottom:10px;
}

.insert {
	width: 700px;
}

.edit_block {
	width: 100%;
	position: relative;
	left: 50%;
	transform: translate(-50%);
	padding: 0 20px;
}

.edit {
	display: flex;
	text-align: right;
	white-space: nowrap;
	align-items: center;
}

#delete {
	display: inline;
}

#aedit {
	display: inline;
}

.addComment {
	width: 100%;
	white-space: nowrap;
	display: flex;
	padding: 0 20px;
	margin-top: 10px;
	margin-bottom: 10px;
}

div.comments {
	display: grid;
	grid-template-columns: 60px 80px auto;
	padding: 10px;
}

.comments:hover {
	background-color: rgb(215, 215, 215);
}

div.comments>div {
	text-align: left;
}

div.comments>div:first-child {
	/* 	background: #1E90FF; */
	grid-row-start: 1;
	grid-row-end: 3;
}

.cimg {
	display: flex;
	height: 60px;
	align-items: center;
	justify-content: center;
	color: #1E90FF;
}

.cname, .ccontent {
	height: 30px;
	display: flex;
	align-items: flex-end;
}

.ctime, .c {
	height: 30px;
	display: flex;
	align-items: top;
}

.cname {
	width: 100px;
	color: #1E90FF;
	font-weight: 700;
}

.ccontent {
	padding-left: 20px;
}

.ctime {
	color: gray;
	font-size: 12px;
}

#logo {
	width: 100px;
	height: 40px;
}

.upPic {
	width: 60px;
	height: 60px;
}

.author {
	color: #1E90FF;
	font-weight: 700;
	display: contents;
}

form {
	display: flex;
}

.lhbtn {
	width: 50px;
	display: inline;
}

.report {
	margin-bottom: 15px;
}

#reportSubmit {
	white-space: nowrap;
}

.chr {
	margin: 0;
	width: 100%;
	color: rgb(165, 165, 165);
}

.block {
	width: 260px;
}

.container {
	width: 75%;
}

.displayBox {
	background-color: white;
	margin-right: 20px;
	box-shadow: -3px -3px 9px gray;
	margin-bottom: 20px;
}

.photo {
	width: 50px;
	height: 50px;
}

.img {
	margin-top: 5px;
	width: 400px;
}
.userphoto{
	width:25px;
	height:25px;
}
</style>
</head>
<center>

	<c:if test="${forumArticleVO.articleState==1}">
		<div class="text-center">
			<p class="fs-3">
				<span class="text-danger" style="font-size: 60px;">已隱藏</span>
			</p>
			<p class="lead">擁抱當下，遠離專題</p>
			<a
				href="<%=request.getContextPath()%>/front-end/article/select_page.jsp"
				class="btn btn-warning">回討論區</a>
		</div>
	</c:if>
	<c:if test="${forumArticleVO.articleState==0}">
		<div class="container">
			<div class="displayBox">
				<div class="title">
					<jsp:useBean id="forumSvc" scope="page"
						class="com.forum.model.ForumService" />
					<jsp:useBean id="forumArticlePhotoSvc"
						class="com.forumArticlePhoto.model.ForumArticlePhotoService" />
					<span id="sort">【${forumArticleVO.forumVO.forumName}】${forumArticleVO.title}</span>
					<a
						href="<%=request.getContextPath()%>/front-end/article/select_page.jsp"
						class="btn btn-warning">回討論區</a>
				</div>

				<div class="author_block row">
					<div class="author col-9">

						<Img class="userphoto"
							src="${pageContext.request.contextPath}/memServlet1.do?action=getPhoto&memNo=${forumArticleVO.memVO.memNo}"
							width="60"> <span id="autBlock">作者</span>&ensp;<a
							style="text-decoration: none"
							href='${pageContext.request.contextPath}/front-end/memMail/addMemMail.jsp?memNo=${forumArticleVO.memVO.memNo}'>
							${forumArticleVO.memVO.memName}</a>
						<div class="ptime">
						發文時間:<fmt:formatDate value="${forumArticleVO.publishTime}"
									pattern="yyyy-MM-dd HH:mm" />&emsp;
						</div>
					</div>



				</div>
				<br>
				<div class="row edit_block">
					<div class="edit col-4 mr-1">
						
						<c:if test="${forumArticleVO.memNo==memVO.memNo}">
							<form method="post"
								action="<%=request.getContextPath()%>/ForumArticleServlet">
								<button type="submit" class="btn btn-outline-warning" value="修改">編輯文章</button>
								<input type="hidden" name="articleNo" value="${forumArticleVO.articleNo}"> 
								<input type="hidden" name="action" value="getOne_For_Update">
							</form>
							<form method="post"
								action="<%=request.getContextPath()%>/ForumArticleServlet">
								<button type="submit" class="btn btn-outline-danger" value="刪除">刪除文章</button>
								<input type="hidden" name="articleNo" value="${forumArticleVO.articleNo}"> 
								<input type="hidden" name="action" value="hideArticle">
							</form>
						</c:if>
						<c:if test="${forumArticleVO.memNo!=memVO.memNo}">
									<button type="button" class="btn btn-outline-dark" id="reportBTN" value="檢舉">檢舉文章</button>
						</c:if>
							<c:if test="${collection_status eq true }">
	<div class="collection" data-toggle="tooltip" data-placement="top" title="收藏" style="display:none;"></div>
	<div class="uncollection" data-toggle="tooltip" data-placement="top" title="取消收藏"></div>
	</c:if>
	<c:if test="${collection_status eq false }">
	<div class="collection" data-toggle="tooltip" data-placement="top" title="收藏"></div>
	<div class="uncollection" data-toggle="tooltip" data-placement="top" title="取消收藏" style="display:none;"></div>
							</c:if>	
					</div>
				</div>
				<hr>
				<div class="content" style="text-align:justify">${forumArticleVO.content}</div>
				<div>
					<c:if test="true">
						<c:forEach var="forumArticlePhotoVO"
							items="${forumArticlePhotoVO}">
							<img 
								src="<%=request.getContextPath()%>/ForumArticlePhotoGetByPhotoNo?ForumArticlePhotoNo=${forumArticlePhotoVO.photoNo}"
								width="50%" class="uploadedImg"
								style="border-radius: 15px; box-shadow: 3px 3px 12px gray; padding: 2px;">
							<div style="height: 10px"></div>
						</c:forEach>
					</c:if>
				</div>
				<div class="report">
					<form method='post'
						action='<%=request.getContextPath()%>/ForumArticleReportServlet'>
						<input type="hidden" name="articleNo" value='${param.articleNo}'>
						<input type="hidden" name="memNo" value="${memVO.memNo}">
						<input type="hidden" name="action" value='insert'> <input
							type="hidden" name="articleReportState" value=0> <input
							type="hidden" name="articleReportResult" value=0> <input
							type="hidden" class='form-control insert' id="reportReason"
							name="reportReason" value='${param.reportReason}'
							placeholder='請輸入檢舉原因' required>&ensp;&ensp;&ensp;
						<button type='submit' class='btn btn-danger' id="reportSubmit"
							style="visibility: hidden">送出檢舉</button>
					</form>
				</div>
			</div>
			<div class="addComment">
				<form method="post"
					action="<%=request.getContextPath()%>/ForumArticleMessageServlet">
					<input type="hidden" name="articleNo" value="${param.articleNo}">
					<input type="hidden" name="memNo" value="${memVO.memNo}"> <input
						type="hidden" name="action" value="insert"> <input
						type="hidden" name="messageState" value=0> <input
						type="text" class="form-control insert" name="messageContent"
						value="${param.messageContent}" placeholder="內容" required>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
					<button type="submit" class="btn btn-info">發表留言</button>
				</form>
			</div>
			<div class="comment">
				<c:forEach var="forumArticleMessageVO" items="${list}">
					<div class="comments">
						<div class="cimg">
							<c:if test="${forumArticleMessageVO.memVO.memPhoto==null}">
								<img class="photo"
									src="<%=request.getContextPath()%>/front-end/article/img/tomcat.gif">
							</c:if>
							<c:if test="${forumArticleMessageVO.memVO.memPhoto!=null}">
								<Img
									src="${pageContext.request.contextPath}/memServlet1.do?action=getPhoto&memNo=${forumArticleMessageVO.memVO.memNo}"
									width="100">
							</c:if>
						</div>
						<div class="cname">&ensp;${forumArticleMessageVO.memVO.memAccount}</div>
						<div class="ccontent">${forumArticleMessageVO.messageContent}</div>
						<div class="ctime">&ensp;${forumArticleMessageVO.editTime}</div>
						<div class="c"></div>
					</div>
					<hr class="chr">
				</c:forEach>
			</div>

		</div>

	</c:if>
	<script
		src="https://cdn.jsdelivr.net/npm/sweetalert2@11.6.7/dist/sweetalert2.all.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.1.js"
		integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
		crossorigin="anonymous"></script>
	<script>

            
            $('#reportBTN').click(function(){
            	$('#reportReason').attr('type', 'text');
            	$('#reportSubmit').attr('style', '');
            });
            
            $('#reportSubmit').click(function(e){
        		e.preventDefault();
        		var form = $(this).parents('form');
        		Swal.fire({
        			  title: '確認要檢舉文章嗎？',
        			  showCancelButton: true,
        			  cancelButtonText: "取消",
        			  confirmButtonText: '確定',
        			  confirmButtonColor: 'green',
        			}).then((result) => {
        			  if (result.isConfirmed) {
        			    Swal.fire('檢舉成功，自動轉跳回首頁', '', 'success'),
        			    setTimeout(function(){
        			    	form.submit();
        				},1000);
        			  } 
        			})
        	})
        	
        	$('.btn-outline-danger').click(function(e){
        		e.preventDefault();
        		var form = $(this).parents('form');
        		Swal.fire({
        			  title: '確定要刪除文章嗎？',
        			  showCancelButton: true,
        			  cancelButtonText: "取消",
        			  confirmButtonText: '確定',
        			  confirmButtonColor: 'red',
        			}).then((result) => {
        			  if (result.isConfirmed) {
        			    Swal.fire('文章已刪除!', '', 'success'),
        			    setTimeout(function(){
        			    	form.submit();
        				},1000);
        			  } 
        			})
        	})
        	
  var memJS ="<c:out value='${memVO.memNo}'/>";
	let submitA = document.querySelector("#submitA");
	if(submitA){
		submitA.addEventListener("click",function(){
			swal({ 
				title: "刪除文章?",
				  text: "",
				  type: "warning",
				  showCancelButton: true,
				  confirmButtonColor: "#DD6B55",
				  confirmButtonText: "刪除",
				  cancelButtonText: "取消",
				  closeOnConfirm: false,
				  showLoaderOnConfirm: true,
				  closeOnCancel: true,
				  allowOutsideClick: true
				}).then(
				function(isConfirm){ 
				  if (isConfirm) {
				    swal("刪除成功！", "","success")
				    .then(() => {
					setTimeout(postDelete, 3000);
					function postDelete(){
						document.querySelector("#formA").submit();
					}
				 }); 
				  } else { 
				    swal("刪除取消！", "",
				"error"); 
				  } 
				});
		})
	}
	
	$(".collection").click(function(){
 		$(this).hide();
 		$(".uncollection").show();
 	 	
 				$.ajax({ //負責傳到article_collectionServlet 新增某人對某文章的收藏  
 				type : "POST",
 				url : "<%=request.getContextPath()%>/FavoriteArt.do",
 				data : {action: "insert",memNo:<%=pageContext.getAttribute("ajax_mbr_no")%>,articleNo:<%=forumArticleVO.getArticleNo()%>}, //參數傳遞 : action傳遞「加一」 mbr_no art_no 傳遞要加一的資訊
 				success : function(data) {
	 					alert("新增收藏成功");
 				}
 			}); 
})

 	$(".uncollection").click(function(){
 		$(this).hide();
 		$(".collection").show();
	
 		$.ajax({ //負責傳到article_collectionServlet 刪除某人對某文章的收藏  需要的參數: art_no mbr_no   目前mbr_no寫死 之後要從session get到目前是哪個會員對這篇文章按讚 
 				type : "POST",
 				url : "/CGA105G6/FavoriteArt.do",
 				data : {action: "delete",memNo:<%=pageContext.getAttribute("ajax_mbr_no")%>,articleNo:<%=forumArticleVO.getArticleNo()%>}, 
 				success : function(data) {
	 					alert("取消收藏成功");
 				}
 			});
})
        </script>
</center>
<%@ include file="/front-end/footer.jsp"%>
