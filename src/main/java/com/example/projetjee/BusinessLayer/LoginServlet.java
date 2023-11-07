package com.example.projetjee.BusinessLayer;

import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            TypedQuery<SiteUser> userByMailAndPass = entityManager.createNamedQuery("SiteUser.byEmailAndPass", SiteUser.class);
            userByMailAndPass.setParameter(1,email);
            userByMailAndPass.setParameter(2,password);

            List<SiteUser> resultList = userByMailAndPass.getResultList();

            HttpSession session = request.getSession();

            if(!resultList.isEmpty()) {
                SiteUser userFetched = resultList.get(0);
                if (userFetched.getIsAdmin()) {
                    session.setAttribute("adminUser", userFetched);
                    response.sendRedirect("adminPage.jsp");
                } else {
                    session.setAttribute("user", userFetched);
                    response.sendRedirect("index.jsp");
                }
            } else {
                session.setAttribute("refused","true");
                response.sendRedirect("loginPage.jsp");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void destroy() {
    }
}