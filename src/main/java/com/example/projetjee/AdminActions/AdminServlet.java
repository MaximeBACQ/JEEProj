package com.example.projetjee.AdminActions;

import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
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

        TypedQuery<SiteUser> query = entityManager.createQuery("DELETE FROM SiteUser u WHERE u.username > :username", SiteUser.class);
        query.setParameter("username", request.getParameter("username"));

        List<SiteUser> resultList = query.getResultList();

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        if(!resultList.isEmpty()){
            SiteUser userFetched = resultList.get(0);
            out.println(userFetched);

            session.setAttribute("refused", "false");
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage.jsp");
            dispatcher.forward(request, response);

        }else{
            session.setAttribute("refused","true");
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage.jsp");
            dispatcher.forward(request, response);
        }


        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public void destroy(){

    }
}