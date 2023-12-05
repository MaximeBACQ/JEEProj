package com.example.projetjee.BusinessLayer;

import java.io.*;
import java.time.LocalDate;
import java.util.Random;

import com.example.projetjee.DAO.UserDAO;
import com.example.projetjee.DAO.UserExistenceException;
import com.example.projetjee.MailSender;
import com.example.projetjee.Model.CompanyEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServletException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        String passwordHashed = BCrypt.hashpw(password, BCrypt.gensalt());


        //SiteUser newUser = new SiteUser(name, surname, username, email, birthDate, gender, password, false, false, new Boolean(false));
        SiteUser newUser = new SiteUser();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setBirthDate(birthDate);
        newUser.setGender(gender);
        newUser.setPassword(passwordHashed);
        newUser.setIsAdmin(false);
        newUser.setIsModerator(false);
        newUser.setIsSeller(false);

        newUser.setCompany(null);

        UserDAO userDao = new UserDAO();

        Random random = new Random();
        String codeExpected = String.valueOf(100000+random.nextInt(899999));

        MailSender.sendEmail(newUser.getEmail(), "Code de vérification",
                "Voici votre code pour créer votre compte" +
                        "\n" +
                        "\n" +
                        "Voici votre code de vérification pour créer votre compte ZGLABIM :\n"+codeExpected);
        HttpSession session = request.getSession();
        session.setAttribute("codeExpected", codeExpected);
        System.out.println(codeExpected);
        session.setAttribute("newUser", newUser);
        response.sendRedirect("mailVerification.jsp");
    }

        public void destroy () {
        }
    }