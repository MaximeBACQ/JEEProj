package com.example.projetjee.BusinessLayer;

import com.example.projetjee.DAO.ProductDAO;
import com.example.projetjee.Model.ProductEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/productPage")
public class productServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("products");

        if (productId != null && !productId.isEmpty()) {

            int productIdInt = Integer.parseInt(productId);

            ProductDAO productDAO = new ProductDAO();
            ProductEntity product = productDAO.findProductById(productIdInt);

            if (product != null) {
                request.setAttribute("product", product);
                request.getRequestDispatcher("/productPage.jsp").forward(request, response);
            } else {
                response.getWriter().println("Le produit n'est pas disponible.");
            }
        } else {
            response.getWriter().println("Aucun produit spécifié.");
        }
    }
}
