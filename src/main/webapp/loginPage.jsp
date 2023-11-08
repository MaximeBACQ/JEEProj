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
    <% String logFail = (String) session.getAttribute("refused");
        session.removeAttribute("refused");
    %>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="tout">
<div class="main-text">
<%
        if(logFail != null){
        %>
        <h1>You have entered credentials that don't match any user in our database, your home will be bombed soon inshallah</h1>
        <%
        }%>
    <h1> Connexion</h1>
    <form action="LoginServlet" method="post">
        <input class="main-connexion-username" type="email" name="email" placeholder="Your Email" required /><br/>
        <input class="main-connexion-password" type="password" name="password" placeholder="Your Password" required /><br/>
        <input class="main-connexion-submit" type="submit" value="Login !">
    </form>
    <p class="register"><a href="registerPage.jsp">Don't have an account ? Register here !</a></p>

</div>
</div>
</body>
</html>
