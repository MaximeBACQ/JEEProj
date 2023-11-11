package com.example.projetjee.AdminActions;

import com.example.projetjee.DAO.CompanyDAO;
import com.example.projetjee.DAO.UserDAO;
import com.example.projetjee.DAO.UserExistenceException;
import com.example.projetjee.Model.CompanyEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.query.Query;

import java.io.IOException;

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
                    if (userToDelete != null) {
                        userDAO.deleteUser(userToDelete);
                    } else {
                        finalMsg = "No user was found matching this email";
                    }

                }

                if (request.getParameter("submitCompany") != null) {

                    try {
                        int userId = Integer.parseInt(request.getParameter("userForCompany"));
                        SiteUser selectedUser = userDAO.findUserById(userId);
                        session.setAttribute("selectedUser", selectedUser);

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
                    } catch (UserExistenceException e) {
                        finalMsg = "No user found for this id";
                        session.setAttribute("finalMsg", finalMsg);
                    }
                }
            } finally {
                response.sendRedirect("adminPage.jsp");
            }

            if (request.getParameter("userToMakeSeller") != null) {

                try {
                    int userId = Integer.parseInt(request.getParameter("userForCompany"));
                    SiteUser selectedUser = userDAO.findUserById(userId);
                    session.setAttribute("selectedUser", selectedUser);

                    if (selectedUser != null && (!selectedUser.getIsModerator()||!selectedUser.getIsAdmin())){
                        finalMsg = "User is not a moderator";
                        session.setAttribute("finalMsgSeller", finalMsg);
                    }else if(selectedUser.getIsSeller()){
                        finalMsg="User is already a seller";
                        session.setAttribute("finalMsgSeller",finalMsg);
                    }else{
                        selectedUser.setIsSeller(true);
                        userDAO.updateUser(selectedUser);
                    }
                } catch (UserExistenceException e) {
                    finalMsg = "No user found for this id";
                    session.setAttribute("finalMsgSeller",finalMsg)
                }
            }
        }




    public void destroy(){

    }
}