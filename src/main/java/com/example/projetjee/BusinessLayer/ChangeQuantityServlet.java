package com.example.projetjee.BusinessLayer;

import com.example.projetjee.DAO.ProductDAO;
import com.example.projetjee.Model.CartEntity;
import com.example.projetjee.Model.SiteUser;

import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import java.io.IOException;

import com.example.projetjee.DAO.CartDAO;

@WebServlet(name = "ChangeQuantityServlet", value = "/ChangeQuantityServlet")
public class ChangeQuantityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres de la requête POST
        int productId = Integer.parseInt(request.getParameter("productId"));
        int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));
        HttpSession session = request.getSession();
        SiteUser connectedPerson = (SiteUser) session.getAttribute("connectedUser");
        ProductDAO productDAO = new ProductDAO();

        System.out.println("Produit id :" + productId + "newQuantity" + newQuantity);

        // Mettre à jour la quantité en utilisant le DAO
        CartDAO cartDAO = new CartDAO(); // Assurez-vous d'ajuster le nom de votre DAO
        if(newQuantity > productDAO.findProductById(productId).getStock()) {
            //TODO : envoyer affichage dans panier : plus assez de stock, il reste actuellement n produits
            response.sendRedirect("cart.jsp");
        }else if(newQuantity<0){
            response.sendRedirect("cart.jsp");
        } else {
            CartEntity cartWithProductForUser = cartDAO.findCartByUserAndProduct(
                    connectedPerson.getUserId(), productId
            );
            System.out.println(" cart qu'on a sélectionné : ");
            System.out.println(cartWithProductForUser.toString());
            cartWithProductForUser.setQuantity(newQuantity);
            cartDAO.updateCart(cartWithProductForUser);

            // Vous pouvez envoyer une réponse JSON ou un simple message de réussite ici si nécessaire
            response.sendRedirect("cart.jsp");
        }
    }
}
