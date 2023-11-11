package com.example.projetjee.AdminActions;

import com.example.projetjee.DAO.CompanyDAO;
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
            HttpSession session = request.getSession();
            String finalMsg = "";

            try {
                if (request.getParameter("email") != null) {
                    String email = request.getParameter("email");
                    SiteUser userToDelete = userDAO.findUserByEmail(email);
                    if (userToDelete != null){
                        userDAO.deleteUser(userToDelete);
                    }
                    else{
                        finalMsg = "No user was found matching this email";
                    }

                }

                if (request.getParameter("userForCompany") != null){

                    try {

                        int userId = Integer.parseInt(request.getParameter("userForCompany"));
                        SiteUser selectedUser = userDAO.findUserById(userId);

                        if (selectedUser != null && !selectedUser.getIsModerator()) {
                            finalMsg = "User is not a vendor";
                            session.setAttribute("finalMsg", finalMsg);
                        } else {
                            CompanyDAO cpDAO = new CompanyDAO();
                            int companyId = Integer.parseInt(request.getParameter("CompanyId"));
                            CompanyEntity userCompany = cpDAO.findById(companyId);

                            if (userCompany != null) {

                                //try {
                                    selectedUser.setCompany(userCompany);
                                    userDAO.updateUser(selectedUser);

                                       /* if (rowsUpdated > 0) {
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
                                    session.setAttribute("finalMsg", finalMsg); */
                                }
                            }
                    } catch (UserExistsException e) {
                        finalMsg = "No user found for this id";
                        session.setAttribute("finalMsg", finalMsg);
                    }
                }
            } finally{
                response.sendRedirect("adminPage.jsp");
            }
        }



    public void destroy(){

    }
}