<%@ page import="com.example.projetjee.DAO.ProductDAO" %>
<%@ page import="com.example.projetjee.Model.ProductEntity" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 27/11/2023
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<html>
<%
    int productId = 0;
// Vérification si le paramètre existe
    if (request.getParameter("productId") != null && !request.getParameter("productId").isEmpty()) {
        // Conversion du productId en entier si nécessaire
        productId = Integer.parseInt(request.getParameter("productId"));
    }
%>

<head>
    <title>Product Page</title>
    <link rel="stylesheet" type="text/css" href="css/cart.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.cdnfonts.com/css/trade-gothic-lt-std" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<%
    if(request.getParameter("productId") != null && !request.getParameter("productId").isEmpty()){
        ProductDAO productDAO = new ProductDAO();
        if(productDAO.findProductById(productId)==null){
            %><h1> You tried looking for a product that does not exist</h1><%
        }else{%>

<br><br><br>
<div class="en-vedette">
    <div class="vedette-produit">
        <div class="vedette-texte">
            <div class="vedette-titre">
                <h1><% ProductEntity productToPrint = productDAO.findProductById(productId);%></h1>
                <strong><%= productToPrint.getLabel() %></strong> <br><br>
            </div>
            <%= productToPrint.getDescription() %><br><br>
            Stock : <%if(productToPrint.getStock()==0){%>
            Out of stock
            <%
            }else{
            %>
            <%=productToPrint.getStock()%>
            <%}%><br><br>
            <br><br>
            &agrave; partir de <br>
            <div class="vedette-prix"><strong><%= productToPrint.getPrice() %> &euro;</strong></div><br><br><br><br>

            <form action="CartServlet" method="post">
                <input type="hidden" name="productId" value="<%=productToPrint.getProductId()%>" />
                <input type="submit" value="Ajouter au panier" />
            </form>
        </div>

        <a href="detailledProductPage.jsp?productId=<%=productToPrint.getProductId()%>"><img src="<%=productToPrint.getProductImage()%>" alt="Product Image Missing"></a>
    </div>
</div>
<br><br><br>
        <%
        }
    }
%>




<%@ include file="html/footer.html" %>
</body>
</html>