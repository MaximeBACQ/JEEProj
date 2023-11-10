package com.example.projetjee.AdminActions;

import com.example.projetjee.DAO.UserDAO;
import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDAO userDAOImpl = new UserDAO();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        if (request.getParameter("email") != null) {
            EntityTransaction transaction = null;

            try {
                transaction = entityManager.getTransaction();
                transaction.begin();

                Query query = entityManager.createQuery("DELETE FROM SiteUser u WHERE u.email = :email");
                query.setParameter("email", request.getParameter("email"));

                int rowsDeleted = query.executeUpdate();

                if (rowsDeleted > 0) {
                    HttpSession session = request.getSession();
                    session.setAttribute("deleted", "true");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("deleted", "false");
                }

                transaction.commit();
                response.sendRedirect("adminPage.jsp");
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                entityManager.close();
            }
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
        if(request.getParameter("username")!=null){ // if an id was entered
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