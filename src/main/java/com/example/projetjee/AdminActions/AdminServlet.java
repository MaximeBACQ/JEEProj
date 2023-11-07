package com.example.projetjee.AdminActions;

import com.example.projetjee.DAO.UserDAOImpl;
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
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        if(request.getParameter("email")!=null) {
            //  entityManager.getTransaction().begin();

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
        if(request.getParameter("id")!=null){ // if an id was entered
            //entityManager.getTransaction().begin();
            HttpSession session = request.getSession();
            if(userDAOImpl.getUserById(Integer.parseInt(request.getParameter("id"))) != null){//if associated to user
                SiteUser selectedUser = userDAOImpl.getUserById(Integer.parseInt(request.getParameter("id")));
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