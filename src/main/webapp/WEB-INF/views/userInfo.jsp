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
    User u=(User) session.getAttribute("user");
%>
<table >
    <tr>
        <td>Username:</td>
    </tr><tr>
    <td><%=u.getUsername()%></td></tr>
    <tr>
        <td>Password:</td>
    </tr>
    <tr>
        <td><%=u.getPassword()%></td>
    </tr>
    <tr>
        <td>Email:</td></tr>
    <tr>
        <td><%=u.getEmail()%></td></tr>
    <tr>
        <td>Gender:</td>
    </tr>
    <tr>
        <td><%=u.getGender()%></td></tr>
    <tr>
        <td>Birth Date:</td></tr>
    <tr><td><%=u.getBirthDate()%></td>
    </tr>
</table>
<a href="updateUser">Update</a>
<%@include file="footer.jsp"%>