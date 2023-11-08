    <%--
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
    <h1>Admin Interface</h1>
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
            <input type="text" placeholder="User's id" id="UserToSelect" name="id">
        </label>
        <input type="submit" value="Select User">
    </form>

    <form action="AdminServlet" method="post">
        <label for="UserToPromote">
            <input type="text" placeholder="User's username" id="UserToPromote" name="username">
        </label>
        <input type="submit" value="Promote">
    </form>
    </body>
    </html>
