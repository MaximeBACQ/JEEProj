package com.example.projetjee.BusinessLayer;

import java.io.*;
import java.time.LocalDate;

import com.example.projetjee.DAO.UserDAOImpl;
import com.example.projetjee.Hibernate.SiteUser;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // Récupération des paramètres du formulaire
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");

        boolean basicUser = false;

        // Manipulation des données reçues
        SiteUser newUser = new SiteUser(name,surname,username,email,birthDate,gender,password,basicUser,basicUser);

        System.out.println(newUser.toString());
        UserDAOImpl userDao = new UserDAOImpl();
        userDao.addUser(newUser);

        // Exemple de réponse renvoyée au client
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Informations reçues :</h2>");
        response.getWriter().println("<p>Nom : " + name + "</p>");
        response.getWriter().println("<p>Prénom : " + surname + "</p>");
        response.getWriter().println("</body></html>");

    }

    public void destroy() {
    }
}