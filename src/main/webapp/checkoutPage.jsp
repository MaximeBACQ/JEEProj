<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 16/11/2023
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
</head>
<body>
<% if(request.getParameter("Checkout")!=null){%>
    <form action="CheckoutServlet" method="post">
        <input type="text" placeholder="Card Owner"><br/>
        <input type="number" name="CardNumber" placeholder="Card Number"><br/>
        expiration mm
        <select name="" id="month">
            <option value="month" selected disabled>month</option>
            <option value="01">01</option>
            <option value="02">02</option>
            <option value="03">03</option>
            <option value="04">04</option>
            <option value="05">05</option>
            <option value="06">06</option>
            <option value="07">07</option>
            <option value="08">08</option>
            <option value="09">09</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
        </select>
        expiration yy
        <select name="" id="year">
            <option value="year" selected disabled>year</option>
            <option value="2023">2023</option>
            <option value="2024">2024</option>
            <option value="2025">2025</option>
            <option value="2026">2026</option>
            <option value="2027">2027</option>
            <option value="2028">2028</option>
            <option value="2029">2029</option>
            <option value="2030">2030</option>
        </select><br/>
        <input type="number" placeholder="CVV" name ="cvv"><br/>
        <input type="submit" value="Validate Informations" name="Payment">
    </form>
<%}else{
    %>
    <h2>You are not coming from a cart, please log in and use your cart button to proceed to checkout.</h2>
<%}%>
</body>
</html>
