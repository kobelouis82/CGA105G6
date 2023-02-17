<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.member.model.*" %>
<%
    MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<html>
<head>
<style>
img {
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 5px;
  width: 200px;
}
</style>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>會員註冊</title>

    <!-- import bootstrap 5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    <!-- import font-style -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300&display=swap" rel="stylesheet">

    <!-- import css stylesheet -->

    <!-- import jquery-3.6.0 -->
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>

    <!-- import icon -->
    <script src="https://kit.fontawesome.com/b5ef6b60f3.js" crossorigin="anonymous"></script>
    
    <!-- Custom fonts for this template-->
	<link
	href="<%=request.getContextPath()%>/back-end/assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
	<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

	<!-- Custom styles for this template-->
	<link
	href="<%=request.getContextPath()%>/back-end/assets/css/sb-admin-2.min.css"
	rel="stylesheet">
	</head>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/static/css/main.css"/>
</head>
<body class="bg-gradient-primary">

	<div class="container">
		<div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-2 d-none d-lg-block "></div>
                    <div class="col-lg-8">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">會員註冊</h1>
                            </div>
                    		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memServlet1.do" enctype="multipart/form-data">
                        	<div id='preview' style="width=200px"></div>
				 	 		<div class='img'></div>
                        	<label>會員帳號:</label>
                        	<strong style="color:gray">${msg}</strong>
                        	<strong style="color:red">${errorMsgs.memAccount}</strong>
                        	<input id="memAccount" name="memAccount" type="TEXT" size="38"
                               value="<%= (memVO==null)? "" : memVO.getMemAccount()%>" placeholder="至少一個字母和一個數字 , 且長度必需在6到20之間"
                               class="form-control"/>
                        	<br>
                        	<input class="btn btn-secondary" type="button" id="chkaccount" value=" 檢查是否可以使用">
                       		<br>
                        	<br>


                        <label>會員密碼:</label>

                        <strong style="color:red">${errorMsgs.memPassword}</strong>
                        <input class="form-control" type="password" name="memPassword" id="pass" size="38" placeholder="至少一個字母和一個數字 , 且長度必需在6到20之間"
                               value="<%= (memVO==null)? "" : memVO.getMemPassword()%>"/><i class="fa fa-eye"
												onclick="showhide()" id="eye"></i>
												<br>
                        <label>會員姓名:</label>

                        <strong style="color:red">${errorMsgs.memName}</strong>
                        <input class="form-control" type="TEXT" name="memName" size="38"
                               value="<%= (memVO==null)? "" : memVO.getMemName()%>"/>
                        <label>所在城市:</label>
                        <select id="city" name="memCity"/></select>
                        <strong style="color:red">${errorMsgs.memCity}</strong>
                        <br>
                        <label>所在區域:</label>
                        <select id="dist" name="memDist" /></select>
                         <strong style="color:red">${errorMsgs.memDist}</strong>
                        <br>   
                                
                        <label>會員地址:</label>

                        <strong style="color:red">${errorMsgs.memAdd}</strong>
                        <input class="form-control" type="TEXT" name="memAdd" size="38"
                               value="<%= (memVO==null)? "" : memVO.getMemAdd()%>"/>
                        <label>會員電話:</label>

                        <strong style="color:red">${errorMsgs.memTel}</strong>
                        <input class="form-control" type="TEXT" name="memTel" size="38"
                               value="<%= (memVO==null)? "" : memVO.getMemTel()%>"/>
                       
                        <label>會員信箱:</label>

                        <strong style="color:red">${errorMsgs.memMail}</strong>
                        <input class="form-control" type="TEXT" name="memMail" size="38"
                               value="<%= (memVO==null)? "" : memVO.getMemMail()%>"/>

                        <label>會員生日:</label>

                        <strong style="color:red">${errorMsgs.memBirth}</strong>
                        <input class="form-control" type="TEXT" name="memBirth" id="dob_date1" size="38"/>
						<label>大頭貼:</label>
						 <strong style="color:red">${errorMsgs.memPhoto}</strong>
						<input type="file" id='upfile1' name="upfile1"> 
						
						<input class="form-control" type="hidden" name="articleReport" value="0"/>
						<input class="form-control" type="hidden" name="messageReport" value="0" />

                        <br>
                        <div class="row justify-content-center">
                         <div class="col-lg-12" >
                        <input type="hidden" name="action" value="register">
                        <button type="submit" class="btn btn-primary btn-user btn-block">送出註冊</button>
                            </div>
                            
                         </div>                       
                    </FORM>
                    		<div class="text-center">
                    		<div class="col-lg-12" >                             
                                    <form METHOD="get"
										ACTION="<%=request.getContextPath()%>/front-end/memLogin/login.jsp">
										<button type="submit" class="btn btn-link btn-user">取消</button>
									</form>
                                  </div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>                
<script src="<%=request.getContextPath()%>/front-end/memLogin/city_dist.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script type="text/javascript">
 window.onload = function () {
		 AddressSeleclList.Initialize('city', 'dist');
   };
</script>
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
    java.sql.Date memBirth = null;
    try {
        memBirth = memVO.getMemBirth();
    } catch (Exception e) {
        memBirth = new java.sql.Date(System.currentTimeMillis());
    }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css"/>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
    .xdsoft_datetimepicker .xdsoft_datepicker {
        width: 300px; /* width:  300px; */
    }

    .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
        height: 151px; /* height:  151px; */
    }
</style>

<script>
    $.datetimepicker.setLocale('zh');
    $('#dob_date1').datetimepicker({
        theme: '',              //theme: 'dark',
        timepicker: false,       //timepicker:true,
        step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
        format: 'Y-m-d',         //format:'Y-m-d H:i:s',
        value: '<%=memBirth%>', // value:   new Date(),
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


<script>

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
while(imgs.length != 0){
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

    function showAC(json) {
        //剖析json字串,將其轉成js物件
        let chkAc = JSON.parse(json);

        if (!(chkAc.memAccount == "null")) {


            console.log(chkAc.memAccount);

            alert("此帳號已被使用");

        } else {
            alert("此帳號可以使用");

        }


    }



    $("#chkaccount").click(function accountchk() {
        //===實作(填入程式碼)
        let xhr = new XMLHttpRequest();
        let memAccount = $("#memAccount").val();
        //設定好回呼函數
        if (memAccount == null || memAccount === '') {
            alert('帳號不可為空白')
        } else {

            let url = "/CGA105G6/memServlet1.do?action=accountchk&memAccount=" + memAccount;
            xhr.open("get", url, true);
            xhr.onload = function () {
                if (xhr.status == 200) {
                    console.log(xhr.responseText);
                    showAC(xhr.responseText);
// alert(xhr.responseText);
                } else {
                    alert(xhr.status+"100");
                }// status
            };// onload

            //建立好Get連接與送出請求
            xhr.send(null);
        }
    })

</script>


</html>