<%@ page import="com.Caoxin.model.User" %><%--
  Created by IntelliJ IDEA.
  User: CAOX
  Date: 2021/4/14
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1>User Info</h1>
<%
    User user=(User) request.getAttribute("user");
%>
<table >
    <tr>
        <td>Username:</td>
    </tr><tr>
    <td><%=user.getUsername()%></td></tr>
    <tr>
        <td>Password:</td>
    </tr>
    <tr>
        <td><%=user.getPassword()%></td>
    </tr>
    <tr>
        <td>Email:</td></tr>
    <tr>
        <td><%=user.getEmail()%></td></tr>
    <tr>
        <td>Gender:</td>
    </tr>
    <tr>
        <td><%=user.getGender()%></td></tr>
    <tr>
        <td>Birth Date:</td></tr>
    <tr><td><%=user.getBirthDate()%></td>
    </tr>
</table>
<%@include file="footer.jsp"%>