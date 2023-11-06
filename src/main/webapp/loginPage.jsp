<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 01/11/2023
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="LoginServlet" method="post">
        <input type="email" name="email" placeholder="Your Email" required /><br/>
        <input type="password" name="password" placeholder="Your Password" required /><br/>
        <input type="submit" value="Login !">
    </form>
    <h2><a href="registerPage.jsp">Don't have an account ? Register here !</a></h2>
</body>
</html>
