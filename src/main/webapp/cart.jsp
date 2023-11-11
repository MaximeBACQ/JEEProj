<%@ page import="java.util.Map" %>
<%@ page import="com.example.projetjee.Model.ProductEntity" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panier</title>
    <link rel="stylesheet" type="text/css" href="css/cart.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.cdnfonts.com/css/trade-gothic-lt-std" rel="stylesheet">
</head>
<body>

<%@ include file="header.jsp" %>
<br><br><br><br>
<div class="container-cart">
    <h2>Votre panier</h2>

    <%
        int grandTotal = 0;
        Map<ProductEntity, Integer> cart = (Map<ProductEntity, Integer>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
    %>
    <p>Votre panier est vide</p>
    <%
    } else {
    %>
    <table class="cart-table">
        <thead>
        <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Company</th>
            <th>Total</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Map.Entry<ProductEntity, Integer> entry : cart.entrySet()) {
                ProductEntity product = entry.getKey();
                int quantity = entry.getValue();
                double total = product.getPrice() * quantity;
                grandTotal += total;
        %>
        <tr>
            <td><%= product.getLabel() %></td>
            <td><%= product.getPrice() %> €</td>
            <td><%= quantity %></td>
            <td><%= product.getCompanyId().getName() %></td>
            <td><%= total %> €</td>
        </tr>
        <%
            }
        %>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">Grand Total</td>
            <td><%= grandTotal %> €</td>
        </tr>
        </tfoot>
    </table>
    <%
        }
    %>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="html/footer.html" %>

</body>
</html>
