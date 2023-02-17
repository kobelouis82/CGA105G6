<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="com.member.model.*"%>



<%
// ForumArticleVO ForumArticleVO = (ForumArticleVO) request.getAttribute("ForumArticleVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
// Integer articleNo = ForumArticleVO.getArticleNo();
// MemVO memVO = (MemVO) session.getAttribute("memVO"); 
// MemVO memVO = memberSvc.getOneMem(1);
// Integer memNo=memVO.getMemNo();
%>

<%@ include file="/front-end/header.jsp"%>

<style>

 #dropdownMenuLink {
	 display: none;
 }
.title {
	/* width: 75%; */
	height: 40px;
	background-color: black;
	position: relative;
	left: 50%;
	transform: translate(-50%);
	display: flex;
	align-items: center;
	padding: 10px;
	color: white;
	font-size: 20px;
	font-weight: 800;
	margin-bottom: 10px;
}

.container1 {
	margin: 0%;
	width: 75%;
	padding: 10px;
	position: relative;
	left: 50%;
	transform: translate(-50%);
}

.form {
	padding: 10px;
}

.asort, .acontent, .atitle {
	margin-bottom: 10px;
}

.ititle {
	width: 100%;
}

.icontent {
	width: 100%;
	height: 500px;
}

#tcontent {
	font-weight: 800;
	color: #33b5e5;
}

.btn-warning {
	color: white;
	font-weight: 600;
	margin-bottom: 10px;
}

#logo {
	width: 100px;
	height: 40px;
}

.displayBox{
    background-color:white;
    margin-right:20px;
    box-shadow:-3px -3px 9px gray;
    margin-bottom: 20px;
      }

</style>
</head>`
<div class="shop-area">
	<div class="container1">
		<div class="row">
			<div class="col-md-12">			
			<a class="btn btn-secondary" href="<%=request.getContextPath()%>/front-end/article/listAllArt.jsp" role="button">文章查詢</a>
		</div>
	</div>
</div>
			<div class="container1">
				<div class="row">
					<div class="col-md-12">	
						<div class="displayBox">
							<div class="title">發表新文章</div>
								<div class="form">
									<form method="post" action="<%=request.getContextPath() %>/ForumArticleServlet" name="form1" enctype="multipart/form-data">
										<div class="asort">
											<jsp:useBean id="forumSvc" scope="page" class="com.forum.model.ForumService" />
												<select name="forumNo">
													<c:forEach var="forumVO" items="${forumSvc.all}">
														<option value="${forumVO.forumNo}">${forumVO.forumName}</option>
													</c:forEach>
												</select>
										</div>
									<div class="atitle">
										<input type="text" class="ititle" name="title" value="${param.title}" placeholder="請輸入文章標題" required>
										</div>
									<div class="acontent">
										<span id="tcontent">文章內容</span>
										<textarea name="content" class="editor" id="demo" >${param.content}</textarea>
									</div>
								<span id="tcontent">上傳圖片</span>
							<input type="file" name="upfile1" multiple id="upfile"> 
							<br> 
							<input class="acess" type="reset" id="upload" value="重設">
								<div id="picPreview" style="display: flex; width: 100%; height: 100%; flex-wrap: wrap; position: relative;"></div>	
									<input type="hidden" name="memNo" value="${memVO.memNo}">
									<input type="hidden" name="articleState" value=0>
									<input type="hidden" name="action" value="insert">
								<div style="width:100%; display:flex;">
                					<div style="width:89%;"></div>
                						<button class="btn btn-warning" value="送出新增" type="submit">確認發文</button>
                					</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
	    </div>		
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.6.7/dist/sweetalert2.all.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.1.js"
            integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<script src="<%=request.getContextPath() %>/ckeditor5/build/ckeditor.js"></script>
<script>
	ClassicEditor
    .create(document.querySelector('.editor'), {

        toolbar: {
            items: [
                'heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList',
                '|', 'alignment', 'outdent', 'indent', '|', 'fontColor',
                '|', 'blockQuote','undo', 'redo', 'insertTable', 'mediaEmbed'
            ]
        }
    })
    .then(editor => {
        console.log(editor);
    });

	$('.btn-warning').click(function(e){
		e.preventDefault();
		var form = $(this).parents('form');
		Swal.fire({
			  title: '確認要發表文章嗎？',
			  showCancelButton: true,
			  cancelButtonText: "取消",
			  confirmButtonText: '確定',
			  confirmButtonColor: 'green',
			}).then((result) => {
			  if (result.isConfirmed) {
			    Swal.fire('發表成功，自動轉跳回首頁', '', 'success'),
			    setTimeout(function(){
			    	form.submit();
				},1000);
			  }
			})
	})
 
var filereader_support = typeof FileReader != 'undefined';
		if (!filereader_support) {
			alert("No FileReader support");
		}
		acceptedTypes = {
			'image/png' : true,
			'image/jpeg' : true,
			'image/gif' : true
		};
		let upfile = document.getElementById("upfile");
		upfile.addEventListener("change", function(event) {
			let files = event.target.files || event.dataTransfer.files;
			for (let i = 0; i < files.length; i++) {
				previewfile(files[i])
			}
		}, false);
		function previewfile(file) {
			if (filereader_support === true
					&& acceptedTypes[file.type] === true) {
				let reader = new FileReader();
				reader.onload = function(event) {
					let image = new Image();
					image.src = event.target.result;
					image.width = 200;
// 					image.classList.add("imgCss");
					picPreview.appendChild(image);
				};
				reader.readAsDataURL(file);
			} else {
				picPreview.innerHTML += "<p>" + "filename: <strong>"
						+ file.name + "</strong><br>" + "ContentTyp: <strong>"
						+ file.type + "</strong><br>" + "size: <strong>"
						+ file.size + "</strong> bytes</p>";
			}
		}
		$("#upload").click(function() {
			$("#picPreview").empty() // 清空當下預覽
			previewfile(this.files) // this即為<input>元素
		})
    </script>
<%@ include file="/front-end/footer.jsp"%>