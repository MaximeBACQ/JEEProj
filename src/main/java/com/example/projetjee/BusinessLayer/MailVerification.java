package com.example.projetjee.BusinessLayer;

import com.example.projetjee.DAO.UserDAO;
import com.example.projetjee.DAO.UserExistenceException;
import com.example.projetjee.Model.SiteUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "MailVerification", value = "/MailVerification")
public class MailVerification extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String codeInput = request.getParameter("codeInput");
        HttpSession session = request.getSession();
        Object objectCode = session.getAttribute("codeExpected");
        String codeExpected = (String) objectCode;
        Object objectNewUser = session.getAttribute("newUser");
        SiteUser newUser = (SiteUser) objectNewUser;
        UserDAO userDao = new UserDAO();
        try{
            System.out.println(codeExpected);
            System.out.println(codeInput);
            if (codeInput.equals(codeExpected)){
                userDao.createUser(newUser);
                response.sendRedirect("index.jsp");
            }else{
                response.sendRedirect("mailVerification.jsp");
            }
        }catch (UserExistenceException e) {
            String errorMessage = "Erreur : cette adresse e-mail existe déjà.";
            request.setAttribute("registrationMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("registerPage.jsp");
            dispatcher.forward(request, response); // Utilisation de forward pour envoyer le message à la page
        }
    }
    public void destroy () {
    }
}