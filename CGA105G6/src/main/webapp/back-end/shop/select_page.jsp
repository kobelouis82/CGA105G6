<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/back-end/header.jsp"%>
<!DOCTYPE html>

                    <li><a href='listAllShop.jsp'>�d��</a> �Ҧ��ӫ~  <br><br></li>
                    <h3>��Ƭd��:</h3>
                
                    <%-- ���~��C --%>
                    <c:if test="${not empty errorMsgs}">
                        <font style="color: red">�Эץ��H�U���~:</font>
                        <ul>
                            <c:forEach var="message" items="${errorMsgs}">
                                <li style="color: red">${message}</li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    
                    <ul>
                    
                        <li>
                            <FORM METHOD="post" ACTION="shop.do">
                                <b>��J�y���s�� :</b> <input type="text" name="serialNo">
                                <input type="hidden" name="action" value="getOne_For_BackEnd_Display">
                                <input type="submit" value="�e�X">
                            </FORM>
                        </li>
                		
                        <jsp:useBean id="shopSvc" scope="page"	class="com.shop.model.ShopService" />
                
                        <li>
                            <FORM METHOD="post" ACTION="shop.do">
                                <b>��ܬy���s��:</b> <select size="1" name="serialNo">
                                    <c:forEach var="shopVO" items="${shopSvc.all}">
                                        <option value="${shopVO.serialNo}">${shopVO.serialNo}
                                    </c:forEach>
                                </select> <input type="hidden" name="action" value="getOne_For_BackEnd_Display">
                                <input type="submit" value="�e�X">
                            </FORM>
 <%@ include file="/back-end/footer.jsp"%>