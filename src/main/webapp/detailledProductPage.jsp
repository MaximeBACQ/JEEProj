<%@ page import="com.example.projetjee.DAO.ProductDAO" %>
<%@ page import="com.example.projetjee.Model.ProductEntity" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 27/11/2023
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body>
<%
    if(request.getParameter("productId") != null && !request.getParameter("productId").isEmpty()){
        ProductDAO productDAO = new ProductDAO();
        if(productDAO.findProductById(productId)==null){
            %><h1> You tried looking for a product that does not exist</h1><%
        }else{%>
            <h1><% ProductEntity productToPrint = productDAO.findProductById(productId);%></h1>
            <p><span class="info-label">Name :</span> <%= productToPrint.getLabel() %></p>
            <p><span class="info-label">Price :</span> <%= productToPrint.getPrice() %></p>
            <p><span class="info-label">Stock :</span> <%= productToPrint.getStock() %></p>
            <p><span class="info-label">Description :</span> <%= productToPrint.getDescription() %></p>
            <p><span class="info-label"></span><img src="<%=productToPrint.getProductImage()%>" alt="Product Image"></p>
            <form action="CartServlet" method="post">
                <input type="hidden" name="productId" value="<%=productToPrint.getProductId()%>" />
                <input type="submit" value="Ajouter au panier" />
            </form>
        <%
        }
    }
%>
</body>
</html>