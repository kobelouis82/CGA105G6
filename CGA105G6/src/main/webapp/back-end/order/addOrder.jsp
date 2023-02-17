<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.goodsFig.model.*"%>
<%@ page import="com.order.model.*"%>
<%@ include file="/back-end/header.jsp"%>
<%
OrderVO orderVO = (OrderVO) request.getAttribute("orderVO");
%>

          


                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <Form action="<%=request.getContextPath()%>/OrderServlet.do" method="post">
                    
                        <table>
                        <h3>資料新增:</h3>
                        <%-- 錯誤表列 --%>
                        <c:if test="${not empty errorMsgs}">
                            <font style="color:red">請修正以下錯誤:</font>
                            <ul>
                                <c:forEach var="message" items="${errorMsgs}">
                                    <li style="color:red">${message}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                            <tr>
                                <td>會員編號:</td>
                                <td><input type="TEXT" name="memNo" size="45" 
                                     value="<%= (orderVO==null)? "1" : orderVO.getMemNo()%>" /></td>
                            </tr>
                            <tr>
                                <td>訂單金額:</td>
                                <td><input type="TEXT" name="orderTotal" size="45"
                                     value="<%= (orderVO==null)? "1300" : orderVO.getOrderTotal()%>" /></td>
                            </tr>
                            <tr>
				<td>訂單狀態:</td>
				<td><select size="1" name="orderState">
						<option value=0>未出貨</option>
						<option value=1>已出貨</option>
						<option value=2>已完成訂單</option>
						<option value=3>訂金已收</option>
						<option value=4>訂金未收</option>
						<option value=5>訂單作廢</option>
						<option value=6>退款</option>
				</select></td>
			</tr>
                                                      
                             <tr>
				<td>運送方式:</td>
				<td><select size="1" name="orderShip">
						<option value=0>超商</option>
						<option value=1>宅配</option>
						<option value=2>郵寄</option>
				</select></td>
			</tr>
                            
                            <tr>
				<td>付款方式:</td>
				<td><select size="1" name="orderPay">
						<option value=0>信用卡付款</option>
						<option value=1>匯款</option>
						<option value=2>貨到付款</option>
				</select></td>
			</tr>
                            
                                                     
                        
                        </table>
                        <br>
                        <input type="hidden" name="action" value="insert">
                        <input type="submit" value="送出新增"></FORM>

                </div>
                <!-- /.container-fluid -->

<%@ include file="/back-end/footer.jsp"%>
    