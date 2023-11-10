    <%@ page import="com.example.projetjee.Model.SiteUser" %>
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Welcome to Zglabim</title>
    </head>
    <body>

        <%
            HttpSession userSession = request.getSession();
            SiteUser connectedUser = (SiteUser) userSession.getAttribute("connectedUser");

            if (connectedUser != null) {
        %>
        <p>Bienvenue, <%= connectedUser.getUsername() %>! (<a href="LogoutServlet">Déconnexion</a>)</p>
        <% } else { %>
        <p>Vous n'êtes pas connecté. (<a href="loginPage.jsp">Connexion</a>)</p>
        <% } %>


    <%@ include file="html/header.html" %>
    <%@ include file="html/accueil.html" %>
    <%@ include file="html/footer.html" %>





    <br/>
    <a href="loginPage.jsp">Login Page</a>
    </body>
    </html>


