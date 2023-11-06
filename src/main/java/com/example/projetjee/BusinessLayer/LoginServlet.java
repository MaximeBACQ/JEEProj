package com.example.projetjee.BusinessLayer;

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

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<SiteUser> userByMailAndPass = entityManager.createNamedQuery("SiteUser.byEmailAndPass", SiteUser.class);
        userByMailAndPass.setParameter(1,email);
        userByMailAndPass.setParameter(2,password);

        List<SiteUser> resultList = userByMailAndPass.getResultList();
        SiteUser userFetched = resultList.get(0);

        PrintWriter out = response.getWriter();

        if(userFetched != null) {
            HttpSession session = request.getSession();
            out.println(userFetched);
            if(userFetched.getIsAdmin()) {
                session.setAttribute("adminUser", userFetched);
                RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage.jsp");
                dispatcher.forward(request, response);
            }else{
                session.setAttribute("user", userFetched);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        }else{
            out.println("Your credentials did not match anything in our database");
        }

        /*RequestDispatcher dispatcher = request.getRequestDispatcher("votre_page.jsp");
        dispatcher.forward(request, response);*/
    }
    public void destroy(){
    }
}