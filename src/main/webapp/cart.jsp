<%@ page import="com.example.projetjee.Model.ProductEntity" %>
<%@ page import="com.example.projetjee.DAO.CartDAO" %>
<%@ page import="com.example.projetjee.Model.CartEntity" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panier</title>
    <link rel="stylesheet" type="text/css" href="css/cart.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.cdnfonts.com/css/trade-gothic-lt-std" rel="stylesheet">
    <script>
        function updateQuantity(productId) {
            var newQuantity = document.getElementById("quantity_" + productId).valueAsNumber;

            console.log("Nouvelle quantité : " + newQuantity);

            // Vérifier si la nouvelle quantité est un nombre valide
            if (!isNaN(newQuantity)) {
                console.log("Envoi de la requête au serveur...");
                console.log("NewQuantity après la condition sur le fait que c'est un nombre"+newQuantity)
                // Utilisez une requête AJAX pour envoyer la nouvelle quantité au serveur
                var xhr = new XMLHttpRequest();
                console.log("NewQuantity après l'ouverture de la variable xhr sur le fait que c'est un nombre"+newQuantity)
                xhr.open("POST", "ChangeQuantityServlet?productId=" + productId + "&newQuantity=" + newQuantity, true);
                xhr.send();
                console.log("NewQuantity après l'envoi de la variable xhr sur le fait que c'est un nombre"+newQuantity)


                // Vous pouvez gérer la réponse du serveur ici si nécessaire
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            console.log("Mise à jour réussie !");
                        } else {
                            console.error("Erreur lors de la mise à jour de la quantité :" + "L'url pr appeler la servlet" + "ChangeQuantityServlet?productId=" + productId + "&newQuantity=", xhr.statusText);
                        }
                    }
                };
            } else {
                console.error("La nouvelle quantité n'est pas un nombre valide.");
            }
        }
    </script>

</head>
<body>

<%@ include file="header.jsp" %>
<br><br><br><br>

<% Object obj = session.getAttribute("connectedUser");

    if (obj != null) {

        SiteUser user = (SiteUser) obj;

        int grandTotal = 0;

        CartDAO cartDAO = new CartDAO();
        ArrayList<CartEntity> cartArrayList  =
                new ArrayList<>(cartDAO.findCartsByUserId(user.getUserId()));

        if (cartArrayList.isEmpty()) {
%>
<div class="empty-cart">Votre panier est vide</div>
<%
} else {
%>

<div class="main-cart">
    <div class="left-cart">
        <div class="left-cart-title">Panier</div>

        <%
            for (CartEntity cart : cartArrayList) {
                ProductEntity product = cart.getProduct();
                int quantity = cart.getQuantity();
                double total = product.getPrice() * quantity;
                grandTotal += total;
        %>

        <div class="product-cart">
            <div class="product-cart-img"></div>
            <div class="product-cart-txt"><%= product.getLabel() %> et : <%= product.getPrice() %> &euro; et <br>
                <input type="number" id="quantity_<%=product.getProductId()%>" value="<%= quantity %>"><br>
                <button onclick="updateQuantity(<%=product.getProductId()%>)">Update</button><br>
                <%= product.getCompanyId().getName() %>
            </div>
            <div class="product-cart-price"><%= total %> &euro;</div>
        </div>
        <%
            }
        %>
    </div>
    <div class="right-cart">
        le sous total vaut <%= grandTotal %> &euro;.
    </div>

        <%
            }
        }else {
            response.sendRedirect("loginPage.jsp");
            }
        %>
    </div>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <%@ include file="html/footer.html" %>

</body>
</html>
