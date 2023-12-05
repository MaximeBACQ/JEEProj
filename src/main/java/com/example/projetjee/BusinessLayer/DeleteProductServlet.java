package com.example.projetjee.BusinessLayer;

import com.example.projetjee.DAO.CartDAO;
import com.example.projetjee.DAO.ProductDAO;
import com.example.projetjee.Model.ProductEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "DeleteProductServlet", value = "/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteMsg = "";
        HttpSession session = request.getSession();

        if(request.getParameter("productId")!=null){
            if(session.getAttribute("connectedUser")!=null){
                ProductDAO productDAO = new ProductDAO();

                SiteUser connectedUser = (SiteUser) session.getAttribute("connectedUser");
                ProductEntity productToDeleteFromCart = productDAO.findProductById(Integer.parseInt(
                        request.getParameter("productId")
                ));

                CartDAO cartDAO = new CartDAO();
                cartDAO.deleteCart(cartDAO.findCartByUserAndProduct(connectedUser.getUserId()
                        , productToDeleteFromCart.getProductId()));

                response.sendRedirect("cart.jsp");
            }
            else{
                deleteMsg = "You tried to delete a product without being connected, please connect to see your cart";
                session.setAttribute("deleteMsg",deleteMsg);
            }
        }else{
            deleteMsg = "You tried to delete a product without using the associated trash bin button in your cart," +
                    " please use your cart to delete a product.";
            session.setAttribute("deleteMsg",deleteMsg);
        }
    }
}
