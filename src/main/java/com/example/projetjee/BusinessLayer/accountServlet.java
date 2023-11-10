package com.example.projetjee.BusinessLayer;

import com.example.projetjee.DAO.UserDAO;
import com.example.projetjee.Model.SiteUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AccountServlet", value = "/AccountServlet")
public class accountServlet extends HttpServlet {




    public void destroy() {
    }
}
