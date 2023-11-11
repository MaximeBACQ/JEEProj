<%@ page import="com.example.projetjee.Model.SiteUser" %>    <%--
      Created by IntelliJ IDEA.
      User: CYTech Student
      Date: 06/11/2023
      Time: 15:13
      To change this template use File | Settings | File Templates.
    --%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Admin Interface</title>
        <%
            String deleted = null;
            String selected = null;
        %>
        <c:if test="${sessionScope.deleted != null}">
            <%  deleted = (String) session.getAttribute("deleted");
                session.removeAttribute("deleted");
            %>
        </c:if>
        <c:if test="${sessionScope.selected != null}">
            <%  selected = (String) session.getAttribute("selected");
                session.removeAttribute("selected");
            %>
        </c:if>

    </head>
    <body>
    <%
        // Récupération de la variable de session
        SiteUser adminUser = (SiteUser) session.getAttribute("connectedUser");

        // Vérification si la variable de session est définie
        if( adminUser != null){
            if (adminUser.getIsAdmin()) {
    %>
    <h1>Admin Interface, users/moderators actions</h1>
        <%
            if(deleted != null)
            {
                if(deleted.equals("true")){
                %>
                <h2>The user has been deleted.</h2>
                <%
                }else{
                    %>
                    <h2> You've entered an e-mail that doesn't exist in our database, try again.</h2>
                    <%
                }
            }%>

        <h1>Enter the e-mail of the user you would like to delete</h1>
        <form action="AdminServlet" method="post">
            <label for="UserToDelete">
                <input type="text" placeholder="User's e-mail" id="UserToDelete" name="email">
            </label>
            <input type="submit" value="Delete User">
        </form>

    <h1>Enter the ID of the user you would like to select</h1>
    <%
        if(selected != null)
        {
            if(selected.equals("true")){
    %>
    <h2>The user has been selected : TODO !! SHOW USER INFORMATIONS OR REDIRECT TO A PAGE WITH THOSE INFOS</h2>
    <%
    }else{
    %>
    <h2> You've entered an id that doesn't exist in our database, try again.</h2>
    <%
            }
        }%>
    <form action="AdminServlet" method="post">
        <label for="UserToSelect">
            <input type="text" placeholder="User's id" id="UserToSelect" name="idForSelection">
        </label>
        <input type="submit" value="Select User">
    </form>

    <form action="AdminServlet" method="post">
        <label for="UserToPromote">
            <input type="text" placeholder="User's username" id="UserToPromote" name="username">
        </label>
        <input type="submit" value="Promote">
    </form>

    <form action="AdminServlet" method="post">
        <label for="AddUserToCompany">
            <input type="number" placeholder="User's id" id="AddUserToCompany" name="userForCompany">
        </label>
        <input type="number" placeholder="Company's id" id="CompanyId" name="CompanyId">
        <input type="submit" value="Add to company" name="submitCompany">
    </form>
    <% String userIdMessage = (String) request.getAttribute("finalMsg");
        if (userIdMessage != null) {
    %>
    <p><%= userIdMessage %></p>

    <form action="AdminServlet" method="post">
        <label for="UserToMakeSeller">
            <input type="text" placeholder="User's id" id="UserToMakeSeller" name="userToMakeSeller">
        </label>
        <input type="submit" value="Make Seller">
    </form>
    <% String userIdMessageSeller = (String) request.getAttribute("finalMsgSeller");
        if (userIdMessageSeller != null) {
    %>
    <p><%= userIdMessageSeller %></p>

    <h1>Products actions</h1>
    <h2> To add a product, you must head to the moderator page where sellers can sell products.</h2>

    <button
    <%
        }
    %>
<%}else{%>
    <h2>You're not an admin, you cannot access that page.</h2>
    <a href="index.jsp"> Back to index </a>
    <%}
    }else{
            %>
    <h2>You're not connected, please connect as an admin to see that page.</h2>
            <%
    }%>
    </body>
    </html>
