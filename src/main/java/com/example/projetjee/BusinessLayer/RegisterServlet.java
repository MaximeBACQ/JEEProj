package com.example.projetjee.BusinessLayer;

import java.io.*;
import java.time.LocalDate;

import com.example.projetjee.DAO.UserDAOImpl;
import com.example.projetjee.DAO.UserExistsException;
import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");

        boolean basicUser = false;

        SiteUser newUser = new SiteUser(name, surname, username, email, birthDate, gender, password, basicUser, basicUser);

        UserDAOImpl userDao = new UserDAOImpl();

        try {
            userDao.addUser(newUser);
            response.sendRedirect("index.jsp");
        } catch (UserExistsException e) {
            String errorMessage = "Erreur : cette adresse e-mail existe déjà.";
            request.setAttribute("registrationMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("registerPage.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (jakarta.servlet.ServletException g) {
                g.printStackTrace();
            }

        }
    }

        public void destroy () {
        }
    }