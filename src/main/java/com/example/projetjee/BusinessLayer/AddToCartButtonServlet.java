package com.example.projetjee.BusinessLayer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddToCartButtonServlet", value = "/AddToCartButtonServlet")
public class AddToCartButtonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String label = request.getParameter("label");
            String productId = request.getParameter("productId");

            //TODO Ajout dans le panier
            response.sendRedirect("productPage.jsp?products=" + label);
            System.out.println("Implémenter ajout au panier");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}