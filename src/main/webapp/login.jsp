<%--
  Created by IntelliJ IDEA.
  User: CAOX
  Date: 2021/4/8
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<%@include file="header.jsp"%>

<h1>Login</h1>
<%
    if(!(request.getAttribute("message")==null)){
        out.println(request.getAttribute("message"));}
%>
<%@include file="header.jsp"%>
<form method="post" action="login">
    UserName:<input type="text" name="username"><br/>
    Password:<input type="text" name="password"><br/>
    <input type="submit" value="login">
</form>
<%@include file="footer.jsp"%>
</html>
