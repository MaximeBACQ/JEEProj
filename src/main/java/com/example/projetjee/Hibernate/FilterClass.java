package com.example.projetjee.Hibernate;

import com.example.projetjee.HibernateUtil;
import jakarta.servlet.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class FilterClass implements Filter {
    private SessionFactory sessionFactory;
    public void init(FilterConfig filterConfig) throws ServletException {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpRequest.getSession(false);
        try (Session session = sessionFactory.openSession()) {//uses automatic resource management, no need for catch/finally
            // Stocker la session dans les attributs de la requête pour que la servlet puisse y accéder

            httpSession.setAttribute("hibernateSession", session);
            System.out.println(session);
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
