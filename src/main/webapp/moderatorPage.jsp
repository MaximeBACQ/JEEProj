<%@ page import="com.example.projetjee.Model.SiteUser" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 07/11/2023
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Moderator Interface</title>
</head>
<body>
<%
    // Récupération de la variable de session
    SiteUser adminUser = (SiteUser) session.getAttribute("connectedUser");

    // Vérification si la variable de session est définie
    if( adminUser != null){
        if (adminUser.getIsAdmin() || adminUser.getIsModerator()) {
%>
<h2>Will behave normally if you work for a company</h2>
    <form action="ModeratorServlet" method="post">
        <input type="text" placeholder="label" name="label">
        <input type="number" placeholder="price" name="price">
        <input type="number" placeholder="stock" name="stock">
        <input type="text" placeholder="description" name="description">
        <input type="submit" value="Add product" name="addProduct">
    </form>

            <%
        }else{
            %>
<h2> You're not a moderator nor an admin, you do not have access to this page</h2>
<a href="index.jsp">back to index</a>
            <%
        }
    }else{
            %>
<h2>You're not logged in, please login as a moderator or admin to access this page.</h2>
<a href="index.jsp">back to index</a>
    <%}
%>
</body>
</html>
