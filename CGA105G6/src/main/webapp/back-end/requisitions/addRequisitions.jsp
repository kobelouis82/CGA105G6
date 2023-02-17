<%@ page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.requisitions.model.*"%>
<%@ page import="com.supplier.model.*"%>
<%@ page import="com.requisitions_detail.model.*"%>
<%@ page import="com.admin.model.*"%>
<%@ include file="/back-end/header.jsp"%>

<%
RequisitionsVO requisitionsVO = (RequisitionsVO) request.getAttribute("requisitionsVO");
Requisitions_DetailVO requisitions_detailVO = (Requisitions_DetailVO) request.getAttribute("Requisitions_DetailVO");
%>


<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增採購單</title>

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
  

<body>
<div class="back-to-home">
    <h4>
      <a href="<%=request.getContextPath() %>/back-end/requisitions/select_page.jsp">回首頁</a>
    </h4>
</div>   
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<h1>採購單新增:</h1>
	<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/requisitions.do" name="form1">
		<table>
			<jsp:useBean id="adminSvc" scope="page"
				class="com.admin.model.AdminService" />
			<tr>
				<td>採購管理員:</td>
				<td><select size="1" name="adminNo">
						<c:forEach var="adminVO" items="${adminSvc.all}">
							<option value="${adminVO.adminNo}"
								${(requisitionsVO.adminNo==adminVO.adminNo)? 'selected':'' }>${adminVO.adminName}
						</c:forEach>
				</select></td>
			</tr>
			<jsp:useBean id="supplierSvc" scope="page"
				class="com.supplier.model.SupplierService" />
			<tr>
				<td>採購廠商名稱:</td>
				<td><select size="1" name="supplyNo">
						<c:forEach var="supplierVO" items="${supplierSvc.all}">
							<option value="${supplierVO.supplyNo}"
								${(requisitionsVO.supplyNo==supplierVO.supplyNo)? 'selected':'' }>${supplierVO.supplyName}
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>採購單狀態:</td>
				<td><select name="reqStatus"
					value="${requisitionsVO.reqStatus}">
						<option value="0" ${(requisitionsVO.reqStatus==0)? 'selected': ''}>未送出</option>
						<option value="1" ${(requisitionsVO.reqStatus==1)? 'selected': ''}>待審核</option>
						<option value="2" ${(requisitionsVO.reqStatus==2)? 'selected': ''}>已核准</option>
						<option value="3" ${(requisitionsVO.reqStatus==3)? 'selected': ''}>已進貨</option>
						<option value="4" ${(requisitionsVO.reqStatus==4)? 'selected': ''}>已付款</option>
						<option value="5" ${(requisitionsVO.reqStatus==5)? 'selected': ''}>結案</option>
						<option value="6" ${(requisitionsVO.reqStatus==6)? 'selected': ''}>作廢</option>
				</select></td>
			</tr>
			
			<tr>
				<td>採購單付款方式:</td>
				<td><select name="reqPay" value="${requisitionsVO.reqPay}">
						<option value="0" ${(requisitionsVO.reqPay==0)? 'selected': ''}>現金</option>
						<option value="1" ${(requisitionsVO.reqPay==1)? 'selected':'' }>匯款</option>
						<option value="2" ${(requisitionsVO.reqPay==2)? 'selected':'' }>信用卡</option>
				</select></td>
			</tr>
			
			<tr>
				<td>總價格:</td>
				<td><input type="TEXT" name="totalPrice" size="10"
					value="<%=(requisitionsVO == null) ? "總價" : requisitionsVO.getTotalPrice()%>" /></td>
			</tr>
		</table>

		<h2>明細新增:</h2>
		<table id="reqDetailTable">
			<tr>
				<th>遊戲流水編號</th>
				<th>遊戲狀態</th>
				<th>數量</th>
				<th>單價</th>
			</tr>
			
			<jsp:useBean id="shopSvc" scope="page"
				class="com.shop.model.ShopService" />
			<tr>
				
				<td><select id ="1" size="1" name="serialNo">
						<c:forEach var="shopVO" items="${shopSvc.all}">
							<option value="${shopVO.serialNo}"
								${(requisitions_detailVO.serialNo==shopVO.serialNo)? 'selected':'' }>${shopVO.itemName}
						</c:forEach>
				</select></td>
			
				
				<td><select id="2" name="status" value="${requisitions_detailVO.status}">
				 	<option value="0"
							${(requisitions_detailVO.status==0)? 'selected': ''}>現貨</option>
					<option value="1"
							${(requisitions_detailVO.status==1)? 'selected':'' }>預購</option>
				</select></td>
			
				<td><input type="text" name="qty"
					value="<%=(requisitions_detailVO == null) ? " " : requisitions_detailVO.getQty()%>" placeholder="請輸入數量" /></td>
				<td><input type="text" name="price"
					value="<%=(requisitions_detailVO == null) ? " " : requisitions_detailVO.getPrice()%>" placeholder="請輸入價格" /></td>
			</tr>

			<tr>
				<td colspan="4">
			<tr>
				<td><input type="button" value="新增明細" onclick="addRow()"></td>
				<td><input type="button" value="刪除明細" onclick="removeRow()"></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insertReq">
		<input type="submit" onclick="show()" value="送出新增">

	</FORM>

	<script>
		function show() {
			alert("添加成功");			
		}
	</script>

	<script>
function addRow() {
	var table = document.getElementById("reqDetailTable");
	var row = table.insertRow(-1);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	
	  var serialNoSelect = "<select id='1' size='1' name='serialNo'>";
	  serialNoSelect += "<c:forEach var='shopVO' items='${shopSvc.all}'>";
	  serialNoSelect += "<option value='${shopVO.serialNo}'>${shopVO.itemName}</option>";
	  serialNoSelect += "</c:forEach>";
	  serialNoSelect += "</select>";

	  var statusSelect = "<select id='2' name='status'>";
	  statusSelect += "<option value='0'>現貨</option>";
	  statusSelect += "<option value='1'>預購</option>";
	  statusSelect += "</select>";

	  cell1.innerHTML = serialNoSelect;
	  cell2.innerHTML = statusSelect;
	  cell3.innerHTML = "<input type='text' name='qty'>";
	  cell4.innerHTML = "<input type='text' name='price'>";
}

function removeRow() {
	var table = document.getElementById("reqDetailTable");
	if (table.rows.length > 2) {
		table.deleteRow(-1);
	}
}

function calculateTotalPrice() {
	var qtyElements = document.getElementsByName("qty");
	var priceElements = document.getElementsByName("price");
	var totalPrice = 0;
	for (var i = 0; i < qtyElements.length; i++) {
		var qty = parseInt(qtyElements[i].value);
		var price = parseInt(priceElements[i].value);
		totalPrice += qty * price;
	}
	document.getElementsByName("totalPrice")[0].value = totalPrice;
}

document.addEventListener("input", function(event) {
	calculateTotalPrice();
});
</script>
</body>
 <%@ include file="/back-end/footer.jsp"%>