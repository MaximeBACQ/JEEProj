<%@ page import="org.hibernate.Session" %>
<%@ page import="com.example.projetjee.HibernateUtil" %>
<%@ page import="org.hibernate.Hibernate" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 01/11/2023
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<%Session session1 = com.example.projetjee.HibernateUtil.getSessionFactory().openSession();%>
<button onclick="envoyerVersServlet()">Envoyer des données</button>--%>

<%--<script>
    function envoyerVersServlet() {
        var data ="<%= session1 %>";
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "VotreServletURL", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send(data);

        //servlet response
        xhr.onload = function() {
            if (xhr.status === 200) {
                console.log(xhr.responseText);
            }
        };
    }
</script>--%>
    <form action="RegisterServlet" method="post">
        <input type="text" name="name" placeholder="Your Name" required /><br/>
        <input type="text" name="surname" placeholder="Your Surname" required /><br/>
        <input type="text" name="username" placeholder="Your Username, that will be displayed to others" required /><br/>
        <input type="email" name="email" placeholder="Your Email" required /><br/>
        <input type="date" name="birthDate" required /><br/>
        <input type="checkbox" name="gender" id="male" value="male"/>
        <label for="male">M</label><br>
        <input type="checkbox" name="gender" id="female" value="female"/>
        <label for="female">F</label><br>
        <input type="password" name="password" placeholder="Your Password" required /><br/>
        <input type="submit" value="Register !"/>
    </form>
</body>
</html>
