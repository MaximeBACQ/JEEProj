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
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            UserDAO userDAO = new UserDAO();
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            HttpSession session = request.getSession();

            try {
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
                    } catch (Exception e) {
                        if (transaction != null) {
                            transaction.rollback();
                        }
                        e.printStackTrace();
                    } finally {
                        entityManager.close();
                    }
                }


                    if (request.getParameter("userForCompany") != null) {
                        String finalMsg = "";

                        try {
                            int userId = Integer.parseInt(request.getParameter("userForCompany"));
                            SiteUser selectedUser = userDAO.getUserById(userId);

                            if (selectedUser != null && !selectedUser.getIsModerator()) {
                                finalMsg = "User is not a vendor";
                                session.setAttribute("finalMsg", finalMsg);
                            } else {
                                CompanyDao cpDAO = new CompanyDao();
                                long companyId = Long.parseLong(request.getParameter("CompanyId"));
                                CompanyEntity userCompany = cpDAO.findById(companyId);

                                if (userCompany != null) {
                                    EntityTransaction transaction = entityManager.getTransaction();
                                    try {
                                        transaction.begin();

                                        Query query = entityManager.createQuery("UPDATE SiteUser SET companyId = :company WHERE id = :userId");
                                        query.setParameter("company", userCompany);
                                        query.setParameter("userId", userId);

                                        int rowsUpdated = query.executeUpdate();

                                        transaction.commit();

                                        if (rowsUpdated > 0) {
                                            finalMsg = "Added " + selectedUser.getUsername() + " to " + userCompany.getName();
                                            session.setAttribute("finalMsg", finalMsg);
                                        } else {
                                            finalMsg = "Failed to update user's company";
                                            session.setAttribute("finalMsg", finalMsg);
                                        }
                                    } catch (Exception e) {
                                        if (transaction != null && transaction.isActive()) {
                                            transaction.rollback();
                                        }
                                        e.printStackTrace();
                                    }
                                } else {
                                    finalMsg = "Company not found for id: " + companyId;
                                    session.setAttribute("finalMsg", finalMsg);
                                }
                            }
                        } catch (UserExistsException e) {
                            finalMsg = "No user found for this id";
                            session.setAttribute("finalMsg", finalMsg);
                        }
                    }
            } finally {
                response.sendRedirect("adminPage.jsp");
            }
        }


    public void destroy(){

    }
}