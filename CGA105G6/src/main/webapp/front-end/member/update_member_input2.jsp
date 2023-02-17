<%@page import="com.member.model.MemVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
MemVO memVO = (MemVO) session.getAttribute("memVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>會員資料修改</title>
<!-- import bootstrap 5.2.1 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">

<!-- import font-style -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap"
	rel="stylesheet">
<!-- import icon -->
<script src="https://kit.fontawesome.com/b5ef6b60f3.js"
	crossorigin="anonymous"></script>

<!-- import jquery-3.6.0 -->
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>

<!-- import css stylesheet -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/static/css/main.css" />
<style type="text/css">
img {
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 5px;
  width: 200px;
}
</style>

</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card border-0 shadow rounded-3 my-5">
					<div class="card-body p-4 p-sm-5">
						<h1 class="card-title text-center mb-5 fw-light fs-5">會員資料修改</h1>

						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/MemServletFront"
							name="form1" enctype="multipart/form-data">
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<label style="color: red">請修正以下錯誤:</label>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
							<div id='preview' style=""></div>
							<div class='img'></div>

							<input class="form-control" type="hidden" name="memAccount"
								size="45" value="<%=memVO.getMemAccount()%>" /> <label>會員密碼:</label>
							<input class="form-control" id="pass" type="password"
								name="memPassword" size="45" value="<%=memVO.getMemPassword()%>" /><i
								class="fa fa-eye" onclick="showhide()" id="eye"></i> <label>會員暱稱:</label>
							<input class="form-control" type="TEXT" name="memName" size="45"
								value="<%=memVO.getMemName()%>" /> <label>所在城市:</label> <select
								id="city" name="memCity" /></select> <br> <label>所在區域:</label> <select
								id="dist" name="memDist" /></select> <br> <label>會員地址:</label> <input
								class="form-control" type="TEXT" name="memAdd" size="45"
								value="<%=memVO.getMemAdd()%>" /> <label>會員電話:</label> <input
								class="form-control" type="TEXT" name="memTel" size="45"
								value="<%=memVO.getMemTel()%>" /> <label>會員Email:</label> <input
								class="form-control" type="TEXT" name="memMail" size="45"
								value="<%=memVO.getMemMail()%>" /> <label>會員生日:</label> <input
								class="form-control" type="TEXT" name="memBirth" size="45"
								id="dob_date1" /> <label>照片:</label> <input type="file"
								name="upfile1"> <br>
							<div class="row">
								<input type="hidden" name="action" value="updateMem"> <input
									type="hidden" name="memNo" value="<%=memVO.getMemNo()%>">
								<input type="hidden" name="memAccess"
									value="<%=memVO.getMemAccess()%>"> <input type="hidden"
									name="articleReport" value="<%=memVO.getArticleReport()%>">
								<input type="hidden" name="messageReport"
									value="<%=memVO.getMessageReport()%>">
								<button type="submit" class="btn btn-outline-info">送出修改</button>
							</div>
						</FORM>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script
	src="<%=request.getContextPath()%>/front-end/memLogin/city_dist.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script type="text/javascript">
 window.onload = function () {
		 AddressSeleclList.Initialize('city', 'dist');
   };
</script>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
java.sql.Date memBirth = null;
try {
	memBirth = memVO.getMemBirth();
} catch (Exception e) {
	memBirth = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script type="text/javascript">
    $.datetimepicker.setLocale('zh');
    $('#dob_date1').datetimepicker({
        theme: '',              //theme: 'dark',
        timepicker: false,       //timepicker:true,
        step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
        format: 'Y-m-d',         //format:'Y-m-d H:i:s',
        value: '<%=memBirth%>
	', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() ||
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) ||
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() ||
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) ||
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() ||
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) ||
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() ||
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) ||
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

</script>
<script type="text/javascript">
	var a = document.getElementById("eye");
	var b = document.getElementById("pass");
	function showhide() {
		if (b.type == "password") {
			b.type = 'text';
			a.className = 'fa fa-eye-slash'
		} else {
			b.type = 'password';
			a.className = 'fa fa-eye'
		}
	}
	
	
	
	let myFile = document.getElementById('upfile1');
	let preview = document.getElementById('preview');
	let imgs = document.getElementsByClassName('img');
	myFile.addEventListener('change', function(e) {
		while (imgs.length != 0) {
			imgs[0].remove();
		}
		
		let files = e.target.files;
		for (let i = 0; i < files.length; i++) {
			if (files[i].type.indexOf('image') > -1) {
				fileName = files[i].name;
				//console.log(files[i]);
				let reader = new FileReader();
				reader.addEventListener('load', function(e) {
					let result = e.target.result;
					//console.log(result);
					let img = document.createElement('img');
					img.setAttribute('class', 'img');
					img.classList.add('align');
					img.src = result;
					preview.append(img);
				});
				reader.readAsDataURL(files[i]);
			} else {
				alert('請上傳圖檔');
			}
		}
	});
</script>

</html>