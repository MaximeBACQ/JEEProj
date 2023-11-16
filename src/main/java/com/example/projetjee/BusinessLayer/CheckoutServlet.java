package com.example.projetjee.BusinessLayer;

import com.example.projetjee.DAO.BankDAO;
import com.example.projetjee.DAO.CartDAO;
import com.example.projetjee.Model.CartEntity;
import com.example.projetjee.Model.ProductEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckoutServlet", value = "/CheckoutServlet")
public class CheckoutServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String finalMsg = "";
        HttpSession session = request.getSession();
        if(session.getAttribute("connectedUser")!=null) {
            if (request.getParameter("Payment") != null) {
                CartDAO cartDao = new CartDAO();
                BankDAO bankDAO = new BankDAO();
                SiteUser connectedPerson = (SiteUser) request.getAttribute("conectedUser");
                List<CartEntity> carts = cartDao.findCartsByUserId(connectedPerson.getUserId());
                int amountDue = 0;
                for(CartEntity cart : carts){
                    int price = cart.getProduct().getPrice();
                    int quantity = cart.getQuantity();
                    amountDue += price * quantity;
                }
                String expiryDate = request.getParameter("month") + "/" + request.getParameter("year");
                if(bankDAO.isAccountValid(Long.parseLong(request.getParameter("CardNumber")),
                        expiryDate,Integer.parseInt(request.getParameter("cvv")))){
                    //if(amountDue>) // check balance
                }
            } else {
                finalMsg = "You are trying to access the payment page from outside of your cart, please pay from your cart";
                request.setAttribute("finalMsg", finalMsg);
                response.sendRedirect("cart.jsp");
            }
        }else{
            response.sendRedirect("loginPage.jsp");
        }
    }
}
