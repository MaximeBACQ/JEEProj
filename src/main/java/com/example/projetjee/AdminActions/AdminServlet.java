package com.example.projetjee.AdminActions;

import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("DELETE FROM SiteUser u WHERE u.email = :email");
        query.setParameter("email", request.getParameter("email"));

        int rowsDeleted = query.executeUpdate();

        HttpSession session = request.getSession();

        if (rowsDeleted > 0) {
            session.setAttribute("deleted", "true");
        } else {
            session.setAttribute("deleted", "false");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

        response.sendRedirect("adminPage.jsp");
    }
    public void destroy(){

    }
}