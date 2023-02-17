<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp"%>
<center>
  <form id="myForm" action="<%=request.getContextPath()%>/CusNameServlet" method="POST">
            <input id="userName" name="userName" type="hidden" value="${memVO.memAccount}" /> 
            <button class="button" type="submit">連繫客服</button>
            </form>
            <a href="<%=request.getContextPath()%>/front-end/member/update_member_input.jsp">會員修改</a>
</center>
<%@ include file="footer.jsp"%>