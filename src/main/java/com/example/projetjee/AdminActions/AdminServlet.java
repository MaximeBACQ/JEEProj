package com.example.projetjee.AdminActions;

import com.example.projetjee.DAO.*;
import com.example.projetjee.Model.CompanyEntity;
import com.example.projetjee.Model.ProductEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            UserDAO userDAO = new UserDAO();
            ProductDAO productDAO = new ProductDAO();
            HttpSession session = request.getSession();
            String finalMsg = "";

            if (request.getParameter("email") != null) {
                String email = request.getParameter("email");
                if(userDAO.findUserByEmail(email)!=null) {
                    SiteUser userToDelete = userDAO.findUserByEmail(email);
                    finalMsg = "User" + userToDelete.getUsername() + "was deleted";
                    session.setAttribute("finalMsgDelete", finalMsg);
                    userDAO.deleteUser(userToDelete);
                    response.sendRedirect("adminPage.jsp");
                }else{
                    finalMsg = "No user was found matching this email.";
                    session.setAttribute("finalMsgDelete", finalMsg);
                    response.sendRedirect("adminPage.jsp");
                }
            }
            if (request.getParameter("idForSelection") != null) {
                String idForSelection = request.getParameter("idForSelection");
                try {
                    SiteUser selectedUser = userDAO.findUserById(Integer.parseInt(idForSelection));
                    if (selectedUser != null) {
                        finalMsg = "The user " +selectedUser.getUsername()+ " is selected.";
                        session.setAttribute("finalMsgSelectUser", finalMsg);
                    } else {
                        finalMsg = "The id you've entered doesn't exist in our database";
                        session.setAttribute("finalMsgSelectUser", finalMsg);
                    }
                } catch (NumberFormatException | UserExistenceException e) {
                    finalMsg = "Invalid ID format or no user found for this ID";
                    session.setAttribute("finalMsgSelectUser", finalMsg);
                }
                response.sendRedirect("adminPage.jsp");
            }
            if (request.getParameter("idToPromote") != null) {
                String idToPromote = request.getParameter("idToPromote");
                try {
                    SiteUser userToPromote = userDAO.findUserById(Integer.parseInt(idToPromote));
                    if (userToPromote != null) {
                        if (!userToPromote.getIsModerator()) {
                            userToPromote.setIsModerator(true);
                            userDAO.updateUser(userToPromote);
                            finalMsg = "User " + userToPromote.getUsername() + " is now a moderator.";
                        } else {
                            finalMsg = "User " + userToPromote.getUsername() + " is already a moderator.";
                        }
                    } else {
                        finalMsg = "No user found for this ID";
                    }
                } catch (NumberFormatException | UserExistenceException e) {
                    finalMsg = "Invalid ID format or no user found for this ID";
                }
                session.setAttribute("finalMsgModerator", finalMsg);
                response.sendRedirect("adminPage.jsp");
            }
            if (request.getParameter("submitCompany") != null) {
                try {
                    int userId = Integer.parseInt(request.getParameter("userForCompany"));
                    SiteUser selectedUser = userDAO.findUserById(userId);

                    if (selectedUser != null) {
                        int companyId = Integer.parseInt(request.getParameter("CompanyId"));
                        CompanyDAO cpDAO = new CompanyDAO();
                        CompanyEntity userCompany = cpDAO.findById(companyId);

                        if (userCompany != null) {
                            selectedUser.setCompany(userCompany);
                            userDAO.updateUser(selectedUser);
                            finalMsg = "Added " + userCompany.getName() + " to " + selectedUser.getUsername();
                        } else {
                            finalMsg = "Company not found for id: " + companyId;
                        }
                    } else {
                        finalMsg = "No user found for this id";
                    }
                } catch (NumberFormatException | UserExistenceException e) {
                    finalMsg = "Invalid input or no user/company found for given IDs";
                }
                session.setAttribute("finalMsgCompany", finalMsg);
                response.sendRedirect("adminPage.jsp");
            }

            if (request.getParameter("userToMakeSeller") != null) {
                String idToMakeSeller = request.getParameter("userToMakeSeller");
                try {
                    SiteUser userToMakeSeller = userDAO.findUserById(Integer.parseInt(idToMakeSeller));
                    if (userToMakeSeller != null) {
                        if (!userToMakeSeller.getIsSeller()) {
                            userToMakeSeller.setIsSeller(true);
                            userDAO.updateUser(userToMakeSeller);
                            finalMsg = "User " + userToMakeSeller.getUsername() + " is now a seller.";
                        } else {
                            finalMsg = "User " + userToMakeSeller.getUsername() + " is already a seller.";
                        }
                    } else {
                        finalMsg = "No user found for this ID.";
                    }
                } catch (NumberFormatException | UserExistenceException e) {
                    finalMsg = "Invalid ID format or no user found for this ID.";
                }
                session.setAttribute("finalMsgSeller", finalMsg);
                response.sendRedirect("adminPage.jsp");
            }

            if (request.getParameter("userToRemoveSeller") != null) {
                String idToRemoveSeller = request.getParameter("userToRemoveSeller");
                try {
                    SiteUser userToRemoveSeller = userDAO.findUserById(Integer.parseInt(idToRemoveSeller));
                    if (userToRemoveSeller != null) {
                        if (userToRemoveSeller.getIsSeller()) {
                            userToRemoveSeller.setIsSeller(false);
                            userDAO.updateUser(userToRemoveSeller);
                            finalMsg = "User " + userToRemoveSeller.getUsername() + " is no longer a seller.";
                        } else {
                            finalMsg = "User " + userToRemoveSeller.getUsername() + " is not a seller.";
                        }
                    } else {
                        finalMsg = "No user found for this ID.";
                    }
                } catch (NumberFormatException | UserExistenceException e) {
                    finalMsg = "Invalid ID format or no user found for this ID.";
                }
                session.setAttribute("finalMsgRemoveSeller", finalMsg);
                response.sendRedirect("adminPage.jsp");
            }

            if (request.getParameter("pToSearch") != null) {
                String labelToSearch = request.getParameter("pToSearch").trim();
                try {
                    TypedQuery<ProductEntity> query = JPAUtil.getEntityManager()
                            .createQuery("SELECT p FROM ProductEntity p WHERE p.label LIKE :label", ProductEntity.class);
                    query.setParameter("label", "%" + labelToSearch + "%");
                    List<ProductEntity> products = query.getResultList();

                    if (!products.isEmpty()) {
                        finalMsg = "Products found with the label: " + labelToSearch;
                        session.setAttribute("ProductList", products);
                    } else {
                        finalMsg = "No products found with the label: " + labelToSearch;
                    }
                } catch (Exception e) {
                    finalMsg = "Error searching for products: " + e.getMessage();
                }
                session.setAttribute("finalMsgSearchProducts", finalMsg);
                response.sendRedirect("adminPage.jsp");
            }
            if (request.getParameter("pToDelete") != null) {
                try {
                    ProductEntity selectedProduct = productDAO.findProductById(Integer.parseInt(request.getParameter("pToDelete")));
                    if (selectedProduct != null) {
                        productDAO.deleteProduct(selectedProduct);
                        finalMsg = "Product has been deleted";
                    } else {
                        finalMsg = "No product found for this id";
                    }
                } catch (NumberFormatException e) {
                    finalMsg = "Invalid product ID format";
                }
                session.setAttribute("finalMsgDeleteP", finalMsg);
                response.sendRedirect("adminPage.jsp");
            }
        }




    public void destroy(){

    }
}