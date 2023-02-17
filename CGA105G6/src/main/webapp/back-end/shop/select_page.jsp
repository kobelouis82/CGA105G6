<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/back-end/header.jsp"%>
<!DOCTYPE html>

                    <li><a href='listAllShop.jsp'>查詢</a> 所有商品  <br><br></li>
                    <h3>資料查詢:</h3>
                
                    <%-- 錯誤表列 --%>
                    <c:if test="${not empty errorMsgs}">
                        <font style="color: red">請修正以下錯誤:</font>
                        <ul>
                            <c:forEach var="message" items="${errorMsgs}">
                                <li style="color: red">${message}</li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    
                    <ul>
                    
                        <li>
                            <FORM METHOD="post" ACTION="shop.do">
                                <b>輸入流水編號 :</b> <input type="text" name="serialNo">
                                <input type="hidden" name="action" value="getOne_For_BackEnd_Display">
                                <input type="submit" value="送出">
                            </FORM>
                        </li>
                		
                        <jsp:useBean id="shopSvc" scope="page"	class="com.shop.model.ShopService" />
                
                        <li>
                            <FORM METHOD="post" ACTION="shop.do">
                                <b>選擇流水編號:</b> <select size="1" name="serialNo">
                                    <c:forEach var="shopVO" items="${shopSvc.all}">
                                        <option value="${shopVO.serialNo}">${shopVO.serialNo}
                                    </c:forEach>
                                </select> <input type="hidden" name="action" value="getOne_For_BackEnd_Display">
                                <input type="submit" value="送出">
                            </FORM>
 <%@ include file="/back-end/footer.jsp"%>