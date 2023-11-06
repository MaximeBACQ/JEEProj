<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 01/11/2023
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form action="RegisterServlet" method="post">
        <input type="text" name="name" placeholder="Your Name" required /><br/>
        <input type="text" name="surname" placeholder="Your Surname" required /><br/>
        <input type="text" name="username" placeholder="Your Username, that will be displayed to others" required /><br/>
        <input type="email" name="email" placeholder="Your Email" required /><br/>
        <input type="date" name="birthDate" required /><br/>
        <input type="checkbox" name="gender" id="male"/>
        <label for="male">M</label><br>
        <input type="checkbox" name="gender" id="female"/>
        <label for="female">F</label><br>
        <input type="password" name="password" placeholder="Your Password" required /><br/>
        <input type="submit" value="Register !"/>
    </form>
</body>
</html>
