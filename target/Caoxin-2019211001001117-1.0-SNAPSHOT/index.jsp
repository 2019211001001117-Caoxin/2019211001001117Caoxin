<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%@include file="header.jsp"%>

<h1>Welcome to my home page!</h1> <br/>
<form method="get" action="SearchServlet">
    <input type="text" name="txt" size="30"/>
    <select name="search">
        <option value="google">Google</option>
        <option value="bing">Bing</option>
        <option value="baidu">Baidu</option>
    </select>
    <input type="submit" value="Search"/>
</form>
<br/>

<a href="hello-servlet">Hellow Servlet-week1</a><br/>
<a href="hello-servlet">Stud Info Servlet-week2</a><br/>
<a href="register.jsp">Register-getParameter -week3</a> <br/>
<a href="config">Config parameter -week4</a> <br/>
<a href="register.jsp">Register JDBC -week4</a><br/>
<a href="index.jsp">include -week5</a> <br/>
<a href="login.jsp">Login page -week5</a> <br/>


<%@include file="footer.jsp"%>
</body>
</html>