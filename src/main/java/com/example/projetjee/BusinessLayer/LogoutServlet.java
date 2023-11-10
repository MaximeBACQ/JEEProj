package com.example.projetjee.BusinessLayer;

import java.io.*;
import java.time.LocalDate;

import com.example.projetjee.DAO.UserDAO;
import com.example.projetjee.DAO.UserExistsException;
import com.example.projetjee.Model.SiteUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServletException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();

        if(session.getAttribute("connectedUser")!=null){
            session.removeAttribute("connectedUser");
        }

        response.sendRedirect("index.jsp");
    }

    public void destroy () {
    }
}