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
    <meta charset="UTF-8">
    <title>Moderator interface</title>
    <link rel="stylesheet" type="text/css" href="css/cart.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.cdnfonts.com/css/trade-gothic-lt-std" rel="stylesheet">
    <link rel="stylesheet" href="css/moderatorPage.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<br><br><br>

<%
    SiteUser adminUser = (SiteUser) session.getAttribute("connectedUser");

    if( adminUser != null){
        if (adminUser.getIsAdmin() || adminUser.getIsModerator()){
            if(adminUser.getCompany()!=null){
                if(adminUser.getIsSeller()){
%>

<h1>Add a product : </h1>

    <form action="ModeratorServlet" method="post">
        <input type="text" placeholder="label" name="label">
        <input type="number" placeholder="price" name="price">
        <input type="number" placeholder="stock" name="stock">
        <input type="text" placeholder="description" name="description">
        <input type="text" placeholder="Your product image's link" name="imageLink">
        <input type="submit" value="Add product" name="addProduct">
    </form>
<br><br><br><br><br><br><br><br><br><br>
            <%
                }else{
                    %>
                    <h2>You do not have the right to sell products. Please contact support.<br><br><br><br><br><br><br><br><br><br><br><br><br>
                    <%
                }
                }else {
                    %>
                        <h2> You do not work for a company, you do not have access to this page</h2><br><br><br><br><br><br><br><br><br><br><br><br><br>
                    <%
                }
        }else{
            %>
<h2> You're not a moderator nor an admin, you do not have access to this page
<a href="index.jsp">back to index</a><br><br><br><br><br><br><br><br><br><br><br><br><br>
            <%
        }
    }else{
            %>
<h2>You're not logged in, please log in as a moderator or admin to access this page.</h2>
<a href="index.jsp">back to index</a><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <%}
    if(session.getAttribute("finalMsg")!=null){
    %>
    <h2> <%=session.getAttribute("finalMsg")%></h2>
    <%
    }
%>
<br><br><br>
<%@ include file="html/footer.html" %>
</body>
</html>
