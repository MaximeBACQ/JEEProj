package com.example.projetjee.BusinessLayer;

import com.example.projetjee.DAO.UserDAO;
import com.example.projetjee.Model.SiteUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();

        //System.out.println(authenticatedUser.toString());

        try {
            SiteUser authenticatedUser = userDAO.authenticateUser(email, password);
            session.setAttribute("connectedUser", authenticatedUser);
            response.sendRedirect("index.jsp");
        } catch (com.example.projetjee.exceptions.AuthenticationException e) {
            session.setAttribute("refused", "true");
            response.sendRedirect("loginPage.jsp");
        }


    }

    public void destroy() {
    }
}