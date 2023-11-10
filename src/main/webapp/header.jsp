<%@ page import="com.example.projetjee.Model.SiteUser" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.*, java.util.*, javax.servlet.*" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Vos balises head ici -->
</head>
<body>

<div class="header-banniere">
    <a href="index.php"><img id="header-banniere-logo" src="img/TENOR.png" alt="Logo Tenor"></a>
    <h2 id="header-banniere-titre"> ZGLABIM </h2>
    <img src='img/france.png'><li> france </li>
    <li> | </li>
    <li><a href="">Contact</a></li>
    <li> | </li>
    <li>
    <%
        HttpSession userSession = request.getSession();
        SiteUser connectedUser = (SiteUser) userSession.getAttribute("connectedUser");

        if (connectedUser != null) {
            if(connectedUser.getIsAdmin()){
                %>
                <a href="adminPage.jsp">Compte</a>
                <a href="LogoutServlet">Déconnexion</a>
                <%
            }
            else if(connectedUser.getIsModerator()){
            %>
                <a href="moderatorPage.jsp">Compte</a>
                <a href="LogoutServlet">Déconnexion</a>
            <%
            }
            else {
            %>
                <a href="accountPage.jsp">Compte</a>
                <a href="LogoutServlet">Déconnexion</a>
            <%
            }
        }
        else {
            %>
            <a href="loginPage.jsp">Connexion</a>
            <%
        }
        %>

    </li>
</div>

<div class='header-menu'>
    <ul>
        <li><a href='index.php'> <i class='fa fa-house'></i>Accueil</a></li>
        <li><a href='panier.php'> <i class='fa fa-bag-shopping'></i>Panier</a></li>
        <div class='header-sous-menu-bag'>
            <ul>
                <li> 0 article(s) <br/> Total (TTC) : 0 €</li>
            </ul>
        </div>
        <li>

        </li>
    </ul>
</div>

</body>
</html>