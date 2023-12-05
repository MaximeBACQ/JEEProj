<%@ page import="com.example.projetjee.Model.SiteUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Page</title>
    <link rel="stylesheet" type="text/css" href="css/cart.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.cdnfonts.com/css/trade-gothic-lt-std" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"/>
<br><br><br>

<div>
    <%
        SiteUser user = (SiteUser) session.getAttribute("connectedUser");

        if(user != null) {
    %>
    <h2>Informations sur le compte</h2>
    <table border="1">
        <tr>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Nom d'utilisateur</th>
            <th>Email</th>
            <th>Date de naissance</th>
            <th>Genre</th>
        </tr>
        <tr>
            <td><%= user.getName() %></td>
            <td><%= user.getSurname() %></td>
            <td><%= user.getUsername() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getBirthDate() %></td>
            <td><%= user.getGender() %></td>
        </tr>
    </table>
    <%
        }
    %>
</div>
<br><br><br>
<%@ include file="html/footer.html" %>
</body>
</html>
