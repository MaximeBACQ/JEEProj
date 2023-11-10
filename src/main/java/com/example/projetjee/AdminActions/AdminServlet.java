package com.example.projetjee.AdminActions;

import com.example.projetjee.DAO.CompanyDao;
import com.example.projetjee.DAO.UserDAO;
import com.example.projetjee.DAO.UserExistsException;
import com.example.projetjee.Model.CompanyEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDAO userDAO = new UserDAO();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        HttpSession session = request.getSession();


        if (request.getParameter("email") != null) {
            EntityTransaction transaction = null;

            try {
                transaction = entityManager.getTransaction();
                transaction.begin();

                Query query = entityManager.createQuery("DELETE FROM SiteUser u WHERE u.email = :email");
                query.setParameter("email", request.getParameter("email"));

                int rowsDeleted = query.executeUpdate();

                if (rowsDeleted > 0) {
                    session.setAttribute("deleted", "true");
                } else {
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

        if(request.getParameter("idForSelection")!=null){ // if an id was entered
            //entityManager.getTransaction().begin();
            try {
                if(userDAO.getUserById(Integer.parseInt(request.getParameter("idForSelection"))) != null){//if associated to user
                    SiteUser selectedUser = userDAO.getUserById(Integer.parseInt(request.getParameter("idForSelection")));
                    session.setAttribute("selected","true");
                    response.sendRedirect("adminPage.jsp");

                }else{
                    session.setAttribute("selected", "false");
                    response.sendRedirect("adminPage.jsp");

                }
            } catch (UserExistsException e) {
                String errorMsg = "No user found for this id";
                session.setAttribute("noUserForId",errorMsg);
                RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage.jsp");
                dispatcher.forward(request, response); // Utilisation de forward pour envoyer le message à la page
            }
        }
        if(request.getParameter("username")!=null){

        }
        if(request.getParameter("userForCompany")!=null){
            String finalMsg ="";
            try {
                    SiteUser selectedUser = userDAO.getUserById(Integer.parseInt(request.getParameter("userForCompany")));
                    session.setAttribute("selectedUser",selectedUser);
                    if(!selectedUser.getIsModerator()){
                        finalMsg = "User is not a vendor";
                        session.setAttribute("finalMsg",finalMsg);
                        response.sendRedirect("adminPage.jsp"); // Utilisation de forward pour envoyer le message à la page
                    }
                    CompanyDao cpDAO = new CompanyDao();

                    CompanyEntity userCompany = cpDAO.findById(Long.parseLong(request.getParameter("CompanyId")));
                    session.setAttribute("companySelected",userCompany);
                    selectedUser.setCompanyId(userCompany);
                    finalMsg = "Added "+selectedUser.getUsername()+"to"+userCompany.getName();
                    session.setAttribute("finalMsg",finalMsg);
//                    RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage.jsp");
//                    dispatcher.forward(request, response); // Utilisation de forward pour envoyer le message à la page
                    response.sendRedirect("adminPage.jsp"); // Utilisation de forward pour envoyer le message à la page


            } catch (UserExistsException e) {
                finalMsg = "No user found for this id";
                session.setAttribute("finalMsg",finalMsg);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage.jsp");
//                dispatcher.forward(request, response); // Utilisation de forward pour envoyer le message à la page
                response.sendRedirect("adminPage.jsp"); // Utilisation de forward pour envoyer le message à la page
            }
        }
    }
    public void destroy(){

    }
}