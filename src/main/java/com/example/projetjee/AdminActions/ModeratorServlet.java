package com.example.projetjee.AdminActions;

import com.example.projetjee.DAO.JPAUtil;
import com.example.projetjee.DAO.ProductDAO;
import com.example.projetjee.Model.ProductEntity;
import jakarta.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ModeratorServlet", value = "/ModeratorServlet")
public class ModeratorServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductDAO productDAOImpl = new ProductDAO();

        if(request.getParameter("submit")!=null) {
            //  entityManager.getTransaction().begin();

            EntityManager entityManager = JPAUtil.getEntityManager();

            String label = request.getParameter("label");
            int price = Integer.parseInt(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String description = request.getParameter("description");

            ProductEntity newProduct = new ProductEntity();

            newProduct.setLabel(label);
            newProduct.setPrice(price);
            newProduct.setStock(stock);
            newProduct.setDescription(description);

            productDAOImpl.addProduct(newProduct);

            response.sendRedirect("index.jsp");
        }
        if(request.getParameter("id")!=null){ // if an id was entered
            //entityManager.getTransaction().begin();
            HttpSession session = request.getSession();
            if(productDAOImpl.getProductById(Integer.parseInt(request.getParameter("id"))) != null){//if associated to user
                ProductEntity product = productDAOImpl.getProductById(Integer.parseInt(request.getParameter("id")));
                session.setAttribute("selected","true");
                response.sendRedirect("adminPage.jsp");

            }else{
                session.setAttribute("selected", "false");
                response.sendRedirect("adminPage.jsp");

            }
        }
    }
    public void destroy(){

    }
}