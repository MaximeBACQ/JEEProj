package com.example.projetjee.BusinessLayer;

import com.example.projetjee.DAO.CartDAO;
import com.example.projetjee.DAO.ProductDAO;
import com.example.projetjee.DAO.UserDAO;
import com.example.projetjee.Model.CartEntity;
import com.example.projetjee.Model.ProductEntity;
import com.example.projetjee.Model.SiteUser;
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
        try {
            HttpSession session = request.getSession();

            if (session.getAttribute("connectedUser") == null) {
                response.sendRedirect("loginPage.jsp");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            HttpSession session = request.getSession();
            Object obj = session.getAttribute("connectedUser");

            if (obj == null) {
                response.sendRedirect("loginPage.jsp");
            }

            SiteUser user = (SiteUser) obj;
            ProductDAO productDAO = new ProductDAO();

            ProductEntity product =  productDAO.findProductById(Integer.parseInt(request.getParameter("productId")));

            CartEntity cart = new CartEntity(1, user, product);

            CartDAO cartDAO = new CartDAO();
            cartDAO.createCart(cart);

            response.sendRedirect("cart.jsp");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}