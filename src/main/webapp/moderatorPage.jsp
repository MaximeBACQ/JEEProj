<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 07/11/2023
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Moderator Page</title>
</head>
<body>
    <form action="ModeratorServlet" method="post">
        <input type="text" name="label" placeholder="Name of your product">
        <input type="number" name="price" placeholder="It's price">
        <input type="number" name="stock" placeholder="How many ?">
        <input type="text" name="description" placeholder="Describe your product">
        <input type="submit" value="Add a product !" name="submit">
    </form>
</body>
</html>
