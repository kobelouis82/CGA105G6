<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.requisitions_detail.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%
Requisitions_DetailVO requisitions_detailVO = (Requisitions_DetailVO) request.getAttribute("Requisitions_DetailVO"); //Requisitions_DetailServlet.java (Concroller) 存入req的requisitions_detailVO物件 (包括幫忙取出的requisitions_detailVO, 也包括輸入資料錯誤時的requisitions_detailVO物件)
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>採購單明細修改</title>

<style>
    /* 設定標題樣式 */
    h1 {
      text-align: center;
      font-size: 36px;
      font-weight: bold;
      margin-top: 20px;
    }

    /* 設定表格樣式 */
    table {
      border-collapse: collapse;
      margin: 0 auto;
      width: 60%;
    }

    /* 設定表格內容樣式 */
    table td {
      border: 1px solid black;
      padding: 10px;
      text-align: center;
    }

    /* 設定錯誤訊息樣式 */
    .error-message {
      color: red;
      margin: 10px 0;
    }

    /* 設定錯誤列表樣式 */
    .error-list {
      list-style-type: square;
      margin-left: 20px;
    }

    /* 設定回首頁連結樣式 */
    .back-to-home {
      text-align: right;
      margin-bottom: 20px;
    }
    
    body {
			font-family: Arial, sans-serif;
		}

		#table-1 {
			width: 100%;
			margin: 20px 0;
			border-collapse: collapse;
		}

		#table-1 tr td {
			text-align: center;
			padding: 10px 0;
		}

		table {
			width: 100%;
			margin: 20px 0;
			border-collapse: collapse;
		}

		table tr th {
			background-color: lightgray;
			text-align: center;
			padding: 10px;
		}

		table tr td {
			text-align: center;
			padding: 10px;
			border: 1px solid lightgray;
		}

		form {
			display: inline-block;
			margin-bottom: 0;
		}

		input[type="submit"] {
			background-color: lightgray;
			border: none;
			padding: 5px 10px;
			border-radius: 5px;
			cursor: pointer;
		}

		input[type="submit"]:hover {
			background-color: gray;
		}
		
  </style>

</head>
<body>


				<h3>採購單明細更改</h3>
				<h4>
					<a href="<%=request.getContextPath() %>/back-end/requisitions/select_page.jsp">回首頁</a>
				</h4>



	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/requisitions_detail.do" name="form1">
		<table>
			<tr>
				<td>採購單明細編號:</td>
				<td><%=requisitions_detailVO.getReqDetailNo()%></td>
			</tr>
			<tr>
				<td>採購單編號:</td>
				<td><%=requisitions_detailVO.getReqNo()%></td>
			</tr>
			<tr>
						<jsp:useBean id="shopSvc" scope="page"
				class="com.shop.model.ShopService" />
				<td>遊戲流水編號:</td>
								<td><select id ="1" size="1" name="serialNo">
						<c:forEach var="shopVO" items="${shopSvc.all}">
						<option value="${shopVO.serialNo}"
						${(requisitions_detailVO.serialNo==shopVO.serialNo)? 'selected':'' }>${shopVO.itemName}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>遊戲狀態編號:</td>
				<td><select id="2" name="status" value="${requisitions_detailVO.status}">
				 	<option value="0"
							${(requisitions_detailVO.status==0)? 'selected': ''}>現貨</option>
					<option value="1"
							${(requisitions_detailVO.status==1)? 'selected':'' }>預購</option>
				</select></td>
			</tr>
			<tr>
				<td>商品數量:</td>
				<td><%=requisitions_detailVO.getQty()%></td>
			</tr>
			<tr>
				<td>商品價格:</td>
				<td><%=requisitions_detailVO.getPrice()%></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> 
		<input
			type="hidden" name="reqDetailNo"
			value="<%=requisitions_detailVO.getReqDetailNo()%>"> 
			<input
			type="submit" value="送出修改">
		<input
			type="hidden" name="reqNo"
			value="<%=requisitions_detailVO.getReqNo()%>"> 
			
		<input type="hidden" name="qty" value="<%=requisitions_detailVO.getQty()%>">
		
		<input type="hidden" name="price" value="<%=requisitions_detailVO.getPrice()%>">
		
	</FORM>
</body>

 <%@ include file="/back-end/footer.jsp"%> 