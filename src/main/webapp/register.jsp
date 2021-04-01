<%--
  Created by IntelliJ IDEA.
  User: CAOX
  Date: 2021/3/25
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="register"><!-- whthin doPost() in Servlet-->
    username<input type="text" name="username"/><br/>
    password<input type="password" name="email"><br/>
    Email<input type="text" name="email"/><br/>
    Gender:<input type="radio" name="gender" value="Male">Male <input type="radio" name="gender" value="Female">Female<br/>
    <!-- if name is same it makes array-->
    Date of Birth :<input type="text name=" name="birthDate"><br/>
    <input type="submit" value="Register"/>

</form>

</body>
</html>
