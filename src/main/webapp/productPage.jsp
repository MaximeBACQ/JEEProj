<%@ page import="com.example.projetjee.Model.ProductEntity" %>
<%@ page import="jakarta.persistence.TypedQuery" %>
<%@ page import="com.example.projetjee.DAO.JPAUtil" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.projetjee.DAO.ProductDAO" %>
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
        ProductDAO productDAO = new ProductDAO();
        String labels = request.getParameter("products");
        ArrayList<ProductEntity> productList = new ArrayList<>(productDAO.searchProductsByLabelAndDescription(labels));

         for(ProductEntity p : productList){
         %>
    <div>
        <strong><%=p.getLabel()%></strong>
        <p>Prix : <%=p.getPrice()%></p>
        <p>Stock : <%=p.getStock()%></p>
            <%=p.getDescription()%><br>
        <p> <img src="<%=p.getProductImage()%>" alt="Product Image Missing"></p>
        <form action="CartServlet" method="post">
<%--            <%p.getProductImage();%>--%>
            <input type="hidden" name="productId" value="<%=p.getProductId()%>" />
            <input type="submit" value="Ajouter au panier" />
        </form>
    </div><br><br>
        <%
         }
%>

<%--            <%=productList.toString() %>--%>

<%--    TypedQuery<ProductEntity> query = JPAUtil.getEntityManager().createQuery("SELECT u FROM SiteUser u WHERE label LIKE %%")--%>
<%--<%--/*        if (product != null) {&ndash;%&gt;
&lt;%&ndash;    %>&ndash;%&gt;
&lt;%&ndash;    <div class="product-details">&ndash;%&gt;
&lt;%&ndash;        <p>Nom du Produit : <%= product.getLabel() %></p>&ndash;%&gt;
&lt;%&ndash;        <p>Prix : <%= product.getPrice() %> €</p>&ndash;%&gt;
&lt;%&ndash;        <p>Quantité en Stock : <%= product.getStock() %></p>&ndash;%&gt;
&lt;%&ndash;        <p>Compagnie : <%= product.getCompanyId().getName() %></p>&ndash;%&gt;
&lt;%&ndash;    </div>&ndash;%&gt;
&lt;%&ndash;    &lt;%&ndash;dash;%&gt;
&lt;%&ndash;    } else {&ndash;%&gt;
&lt;%&ndash;    %>&ndash;%&gt;
&lt;%&ndash;    <p>Le produit n'est pas disponible.</p>&ndash;%&gt;
&lt;%&ndash;    &lt;%&ndash;dash;%&gt;
&lt;%&ndash;        }&ndash;%&gt;
&lt;%&ndash;    %>&ndash;%&gt;
&lt;%&ndash;</div>&ndash;%&gt;
&lt;%&ndash;<br><br><br><br><br><br><br><br><br><br><br><br><br><br>*/--%>
<%@ include file="html/footer.html" %>

</body>
</html>
