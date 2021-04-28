<%--
  Created by IntelliJ IDEA.
  User: CAOX
  Date: 2021/4/28
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<h1>User Update</h1>
<%
    User u= (User) session.getAttribute("user");
%>
<form method="post" action="updateUser"><!-- whthin doPost() in Servlet-->
    <input type="hidden" name="id" value="<%=u.getId()%>">
    username<input type="text" name="username" value="<%=u.getUsername()%>"/><br/>
    password<input type="password" name="password" value="<%=u.getPassword()%>"/><br/>
    Email<input type="text" name="email" value="<%=u.getEmail()%>"/><br/>
    Gender:<input type="radio" name="gender" value="Male" <%="Male".equals(u.getGender())?"checked" :""%>>Male
    <input type="radio" name="gender" value="Female" <%="Female".equals(u.getGender())?"checked" :""%>>Female<br/>
    <!-- if name is same it makes array-->
    Date of Birth :<input type="text" name="birthdate" value="<%=u.getBirthDate()%>"><br/>
    <input type="submit" value="Save Changes"/>

</form>
<%@ include file="footer.jsp"%>