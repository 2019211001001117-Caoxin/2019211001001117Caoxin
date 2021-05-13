<%--
  Created by IntelliJ IDEA.
  User: CAOX
  Date: 2021/5/9
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.Caoxin.model.User" %>
<%@include file="header.jsp"%>
<%
    User u= (User) session.getAttribute("user");
%>

<h1>User Info</h1>

<table border="1">
    <tr>
        <th>Username</th>
        <th>Password</th>
        <th>Email</th>
        <th>Gender</th>
        <th>Birthdate</th>
    </tr>
    <tr>
        <td><%=u.getUsername()%></td>
        <td><%=u.getPassword()%></td>
        <td><%=u.getEmail()%></td>
        <td><%=u.getGender()%></td>
        <td><%=u.getBirthDate()%></td>
    </tr>

</table>

<a href="updateUser">Update User</a>

<%@include file="footer.jsp"%>