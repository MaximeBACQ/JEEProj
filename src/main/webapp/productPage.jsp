<%@ page import="com.example.projetjee.Model.ProductEntity" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produit</title>
    <link rel="stylesheet" type="text/css" href="css/cart.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.cdnfonts.com/css/trade-gothic-lt-std" rel="stylesheet">
</head>
<body>



<%@ include file="header.jsp" %>
<br><br><br><br>

<div class="container-product-details">
    <%
        String productId = request.getParameter("products");
        ProductEntity product = (ProductEntity) request.getAttribute("product");

        if (product != null) {
    %>
    <div class="product-details">
        <p>Nom du Produit : <%= product.getLabel() %></p>
        <p>Prix : <%= product.getPrice() %> €</p>
        <p>Quantité en Stock : <%= product.getStock() %></p>
        <p>Compagnie : <%= product.getCompanyId().getName() %></p>
    </div>
    <%
    } else {
    %>
    <p>Le produit n'est pas disponible.</p>
    <%
        }
    %>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="html/footer.html" %>

</body>
</html>
