package com.example.projetjee.BusinessLayer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String label = request.getParameter("label");
            String productId = request.getParameter("productId");
            HttpSession session = request.getSession();

            session.setAttribute("label",label);

            response.sendRedirect("productPage.jsp?products=" + label);
            System.out.println("Implémenter ajout au panier");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}