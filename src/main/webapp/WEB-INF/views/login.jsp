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
<%
    if(!(request.getAttribute("message")==null)){
        out.println(request.getAttribute("message"));}
%>
<%
    Cookie[] allCookies=request.getCookies();
    String username="",password="",rememberMeVal="";
    if(allCookies!=null) {
        for(Cookie c:allCookies) {
            if(c.getName().equals("cUsername"))
                username=c.getValue();
            if(c.getName().equals("cPassword"))
                password=c.getValue();
            if(c.getName().equals("cRememberMe"))
                rememberMeVal=c.getValue();
        }
    }
%>
<form method="post" action="login">
    UserName:<input type="text" name="username" value="<%=username%>"><br/>
    Password:<input type="password" name="password" value="<%=password%>"><br/>
    <input type="checkbox" name="rememberMe" value="1" <%=rememberMeVal.equals("")?"checked":""%>>Remember Me<br/>
    <input type="submit" value="submit">
</form>
<%@include file="footer.jsp"%>
</html>
