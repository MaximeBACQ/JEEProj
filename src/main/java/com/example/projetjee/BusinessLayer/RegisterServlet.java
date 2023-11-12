package com.example.projetjee.BusinessLayer;

import java.io.*;
import java.time.LocalDate;

import com.example.projetjee.DAO.UserDAO;
import com.example.projetjee.DAO.UserExistenceException;
import com.example.projetjee.Model.CompanyEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServletException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");


        //SiteUser newUser = new SiteUser(name, surname, username, email, birthDate, gender, password, false, false, new Boolean(false));
        SiteUser newUser = new SiteUser();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setBirthDate(birthDate);
        newUser.setGender(gender);
        newUser.setPassword(password);
        newUser.setIsAdmin(false);
        newUser.setIsModerator(false);
        newUser.setIsSeller(false);

        newUser.setCompany(null);

        UserDAO userDao = new UserDAO();

        try {
            userDao.createUser(newUser);
            response.sendRedirect("index.jsp");
        } catch (UserExistenceException e) {
            String errorMessage = "Erreur : cette adresse e-mail existe déjà.";
            request.setAttribute("registrationMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("registerPage.jsp");
            dispatcher.forward(request, response); // Utilisation de forward pour envoyer le message à la page
        }
    }

        public void destroy () {
        }
    }