<%@page import="com.member.model.MemVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/back-end/header.jsp"%>
<%
MemVO memVO = (MemVO) request.getAttribute("memVO");
%>

<style>
section {
	height: 100%;
	background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
	background-color: #FFDEE9;
	background-repeat: no-repeat;
	background-size: cover;
}

.styled-table {
	margin-left: auto;
	margin-right: auto;
	border-collapse: collapse;
	margin: auto;
	font-size: 0.9em;
	font-family: sans-serif;
	min-width: 400px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

.styled-table thead tr {
	background-color: #212529;
	color: #ffffff;
	text-align: left;
}

.styled-table th, .styled-table td {
	padding: 12px 15px;
}

.styled-table tbody tr {
	border-bottom: 1px solid #dddddd;
}

.styled-table tbody tr:nth-of-type(even) {
	background-color: #f3f3f3;
}

.styled-table tbody tr:last-of-type {
	border-bottom: 2px solid #212529;
}

.styled-table tbody tr.active-row {
	font-weight: bold;
	color: #212529;
}

/* <!-- ===========================================樣式欄位================================================================== --> */
table#table-1 {
	display: flex;
	justify-content: center;
	margin-left: auto;
	margin-right: auto;
	width: 1000px;
	margin-top: 5px;
	margin-bottom: 5px;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h3 {
	color: black;
	font-weight: 700 !important;
	text-align: center;
}

h4 {
	color: blue;
	display: inline;
	text-align: center;
}

/*   a{ */
/*     color: white; */
/*     display: inline; */
/*   } */
</style>

<style>
table {
	margin-left: auto;
	margin-right: auto;
	width: 50%;
	margin-top: 5px;
	margin-bottom: 5px;
}
/*    table, th, td {  */
/*      border: 1px solid #212529;  */
/*    }  */
th, td {
	padding: 5px;
	text-align: left;
}

.tdLeft {
	color: black;
	font-weight: 600;
}

.tdRight {
	width: 300px;
}

.btnSub {
	width: 200px;
	border-radius: 20px !important;
}

input {
	height: 25px;
}

.inputR {
	border: none;
	border-radius: 20px;
}

.inputR:focus {
	border: 2px solid pink;
}

.select {
	width: 300px;
	border-radius: 20px;
	height: 25px;
	border: none;
}

.select:focus {
	border: 2px solid pink;
}

.radio {
	color: black;
}

.radioBlock {
	display: flex;
	align-items: center;
}
</style>




<table id="table-1">
	<tr>
		<td>
			<h3>會員資料修改</h3>
			<h4>
				<a href="<%=request.getContextPath()%>/back-end/member/select_page.jsp" class="btn btn-success">回首頁</a>
			</h4>
		</td>
	</tr>
</table>

<div id="picPreview" style="display: flex; width: 100%; height: 100%; flex-wrap: wrap; position: relative;"></div>	
<FORM METHOD="post"
	ACTION="<%=request.getContextPath()%>/memServlet1.do" name="form1"
	enctype="multipart/form-data">
	<table>
		<tr>
			<td>照片:</td>
			<td><Img
				src="${pageContext.request.contextPath}/memServlet1.do?action=getPhoto&memNo=<%=memVO.getMemNo()%>"
				width=200></td>

		</tr>
		<tr>
			<td class="tdLeft">會員帳號:</td>
			<td><input class="tdRight inputR" type="TEXT" name="memAccount" size="45" readonly style="color: gray" value="<%=memVO.getMemAccount()%>" /></td>
			<td>${errorMsgs.memAccount}</td>
		</tr>
		<tr>
			<td class="tdLeft">會員密碼:</td>
			<td><input class="tdRight inputR" type="password" name="memPassword" size="45" readonly style="color: gray" value="<%=memVO.getMemPassword()%>" /></td>
			<td>${errorMsgs.memPassword}</td>
		</tr>
		<tr>
			<td class="tdLeft">會員姓名:</td>
			<td><input class="tdRight inputR" type="TEXT" name="memName" size="45" value="<%=memVO.getMemName()%>" /></td>
			<td>${errorMsgs.memName}</td>
		</tr>
		<tr>
			<td class="tdLeft">會員地址:</td>
			<td><input class="tdRight inputR" type="TEXT" name="memAdd"
				size="45" value="<%=memVO.getMemAdd()%>" /></td>
			<td>${errorMsgs.memAdd}</td>
		</tr>
		<tr>
			<td class="tdLeft">會員電話:</td>
			<td><input class="tdRight inputR" type="TEXT" name="memTel"
				size="45" value="<%=memVO.getMemTel()%>" /></td>
			<td>${errorMsgs.memTel}</td>
		</tr>
		<tr>
			<td class="tdLeft">會員Email:</td>
			<td><input class="tdRight inputR" type="TEXT" name="memMail"
				size="45" value="<%=memVO.getMemMail()%>" /></td>
			<td>${errorMsgs.memMail}</td>
		</tr>
		<tr>
			<td class="tdLeft">所在城市:</td>
			<td><input class="form-control" type="TEXT" name="memCity"
				size="45" value="<%=memVO.getMemCity()%>" /></td>
			<td>${errorMsgs.memAdd}</td>
		</tr>
		<tr>
			<td class="tdLeft">所在區域:</td>
			<td><input class="form-control" type="TEXT" name="memDist"
				size="45" value="<%=memVO.getMemDist()%>" /></td>
			<td>${errorMsgs.memDist}</td>
		</tr>


		<tr>
			<td class="tdLeft">會員生日:</td>
			<td><input class="tdRight inputR" type="TEXT" name="memBirth"
				size="45" id="dob_date1" value="<%=memVO.getMemBirth()%>" /></td>
			<td>${errorMsgs.memBirth}</td>
		</tr>
		<tr>
			<td class="tdLeft">會員狀態: <font color=red> * </font></td>
			<td><select size="1" name="memAccess" class="select">
					<option value="0" ${(memVO.memAccess==0)? 'selected':'' }>停權</option>
					<option value="1" ${(memVO.memAccess==1)? 'selected':'' }>未驗證</option>
					<option value="2" ${(memVO.memAccess==2)? 'selected':'' }>已驗證</option>
			</select></td>
		</tr>
		<tr>
			<td><input type="file" name="upfile1" id="upfile"></td>
		</tr>
	</table>
	<br> <input type="hidden" name="articleReport"
		value="<%=memVO.getArticleReport()%>"> <input type="hidden"
		name="messageReport" value="<%=memVO.getMessageReport()%>"> <input
		type="hidden" name="action" value="update"> <input
		type="hidden" name="memNo" value="<%=memVO.getMemNo()%>"> <input
		type="submit" style="margin-left: 38%" value="送出修改"
		class="btn btn-warning btnSub">
</FORM>
<div><input class="acess" type="reset" id="upload" value="重設"></div>
</div>





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

<script>
        $.datetimepicker.setLocale('zh');
        $('#dob_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
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

	//              2.以下為某一天之後的日期無法選擇
	//              var somedate2 = new Date('2019-01-01');
	//              $('#dob_date1').datetimepicker({
	//                  beforeShowDay: function(date) {
	//                	  if (  date.getYear() >  somedate2.getYear() || 
	//         		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//         		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//                      ) {
	//                           return [false, ""]
	//                      }
	//                      return [true, ""];
	//              }});

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

	//-------------------------------------------------------------------------------------------------------------------
</script>
<script src="https://code.jquery.com/jquery-3.6.1.js"
            integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
<script>
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
//				image.classList.add("imgCss");
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
<%@ include file="/back-end/footer.jsp"%>