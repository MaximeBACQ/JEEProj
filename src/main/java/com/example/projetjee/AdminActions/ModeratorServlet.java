package com.example.projetjee.AdminActions;

import com.example.projetjee.DAO.JPAUtil;
import com.example.projetjee.DAO.ProductDAO;
import com.example.projetjee.Model.CompanyEntity;
import com.example.projetjee.Model.ProductEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ModeratorServlet", value = "/ModeratorServlet")
public class ModeratorServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductDAO productDAO = new ProductDAO();
        HttpSession session = request.getSession();
        SiteUser connectedPerson = (SiteUser) session.getAttribute("connectedUser");
        boolean isAdmOrModConnected = connectedPerson.getIsModerator() || connectedPerson.getIsAdmin();
        if(isAdmOrModConnected){
            if (request.getParameter("addProduct") != null) {

                String label = request.getParameter("label");
                int price = Integer.parseInt(request.getParameter("price"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                String description = request.getParameter("description");
                final CompanyEntity connectedPersonCompany = connectedPerson.getCompany();

                ProductEntity newProduct = new ProductEntity();
                newProduct.setLabel(label);
                newProduct.setPrice(price);
                newProduct.setStock(stock);
                newProduct.setDescription(label);
                newProduct.setCompanyId(connectedPersonCompany);

                productDAO.createProduct(newProduct);

                response.sendRedirect("index.jsp");
            }
        }
    }
    public void destroy(){

    }
}