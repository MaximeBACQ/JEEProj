<%@ page import="com.example.projetjee.Model.ProductEntity" %>
<%@ page import="jakarta.persistence.TypedQuery" %>
<%@ page import="com.example.projetjee.DAO.JPAUtil" %>
<%@ page import="java.util.*" %>
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
        List<ProductEntity> productList = new ArrayList<>();
        // Récupération de tous les paramètres de l'URL
        Map<String, String[]> parameters = request.getParameterMap();

        for(String parameter : parameters.keySet()) {
                String[] values = parameters.get(parameter);
                for(String v:values){
                    TypedQuery<ProductEntity> query = JPAUtil.getEntityManager()
                                .createQuery("SELECT p FROM ProductEntity p WHERE label LIKE :label ",ProductEntity.class);
                    query.setParameter("label", v);
                    List<ProductEntity> results = query.getResultList();

                    productList.addAll(results);

                    }
                }
         for(ProductEntity p : productList){
         %>
            <%= "label: " + p.getLabel() + " ,prix: " + p.getPrice() + " ,stock: " + p.getStock() + ", description: "
            + p.getDescription()%>
        <form action="AddToCartButtonServlet" method="post">
            <input type="hidden" name="produitId" value="<%=p.getProductId()%>" />
            <input type="hidden" name="label" value="<%=p.getLabel()%>"/>
            <input type="submit" value="Ajouter au panier" />
        </form>
        <%
         }%>

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
