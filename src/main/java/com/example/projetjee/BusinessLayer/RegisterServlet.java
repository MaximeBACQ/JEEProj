package com.example.projetjee.BusinessLayer;

import java.io.*;
import java.time.LocalDate;

import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;




@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");

        boolean basicUser = false;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<SiteUser> query = entityManager.createQuery("SELECT u FROM SiteUser u WHERE u.email = :email", SiteUser.class);
        query.setParameter("email", email);

        try {
            SiteUser existingUser = query.getSingleResult();
            response.sendRedirect("registerPage.jsp?registrationMessage=An account with this email already exists.");
            return; // Arrêter ici pour éviter l'enregistrement supplémentaire
        } catch (NoResultException e) {
            // Continue si l'e-mail n'existe pas
        }

        SiteUser newUser = new SiteUser(name, surname, username, email, birthDate, gender, password, basicUser, basicUser);

        entityManager.getTransaction().begin();
        entityManager.persist(newUser);
        entityManager.getTransaction().commit();
        response.sendRedirect("index.jsp?registrationMessage=Account created successfully.");

        entityManager.close();
        entityManagerFactory.close();
    }



    public void destroy() {
    }
}