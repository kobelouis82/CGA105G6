<%@ page import="com.member.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/back-end/header.jsp"%>
<%
MemVO memVO = (MemVO) session.getAttribute("memVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<style>
section {
	height: 100%;
	background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
	background-color: #FFDEE9;
	background-repeat: no-repeat;
	background-size: cover;
}
/* 	設定container */
.container {
	min-height: 500px;
}
/* 	設定表單、按鈕區塊 */
.buttonBlock {
	display: flex;
	align-items: center;
	justify-content: center;
}

.form {
	margin-top: 50px;
	display: flex;
	align-items: center;
	justify-content: center;
}

/* 	設定表單樣式 */
#f_date1 {
	margin-bottom: 20px;
}

.btnIn {
	min-width: 200px;
	border-radius: 20px !important;
}

.line {
	margin-bottom: 10px;
}

h3 {
	color: black;
	font-weight: 700 !important;
}

#table-1 {
	display: flex;
	align-items: center;
	justify-content: center;
	margin-top: 20px;
}

.select {
	width: 195px;
	height: 30px;
	border-radius: 20px;
	text-align: center;
	border: none;
}

select:focus {
	border: 2px solid pink;
}

input {
	border: none;
	border-radius: 20px;
}

input:focus {
	border: 2px solid pink !important;
}
</style>



<div class="container">
	<table id="table-1">
		<tr>
			<td><h3>會員資料查詢</h3></td>
		</tr>
	</table>


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<jsp:useBean id="memSvc" scope="page"
		class="com.member.model.MemService" />

	<div class="form">
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/memServlet1.do" name="form2">

			<div class="line">
				<b>查詢會員編號:</b> 
				<select size="1" name="mem_No" class="select">
					<option value="">請選擇
						<c:forEach var="memVO" items="${memSvc.all}">
							<option value="${memVO.memNo}">${memVO.memNo}
						</c:forEach>
				</select>
				<br>
			</div>

			<div class="line">
				<b>查詢會員帳號:</b> <input type="text" name="mem_Account" value=""><br>
			</div>

			<div class="line">
				<b>查詢會員姓名:</b> <input type="text" name="mem_Name" value=""><br>
			</div>

			<div class="line">
				<b>查詢會員狀態:</b> 
				<select name="mem_Access" class="select">
					<option value="" ${(memVO.memAccess=="")? 'selected':'' }>請選擇</option>
					<option value="0" ${(memVO.memAccess==0)? 'select':'' }>未驗證</option>
					<option value="1" ${(memVO.memAccess==1)? 'select':'' }>已驗證</option>
					<option value="2" ${(memVO.memAccess==2)? 'select':'' }>停權</option>
				</select> 
				<br>
			</div>
			<input type="hidden" name="action" value="MemSearchPro">
			&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; 
			<input type="submit" class="btn btn-dark btnIn" value="送出">
		</FORM>
	</div>
	<div>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; </div>
	<div class="buttonBlock">
		<button
			onclick="location.href='<%=request.getContextPath()%>/back-end/member/listAllMem.jsp'"
			class="btn btn-dark btnIn">查詢所有會員</button>
	</div>
</div>
<!-- 		</section> -->
<!-- 	</main> -->



<!-- </body> -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script type="text/javascript">
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>
<%@ include file="/back-end/footer.jsp"%>