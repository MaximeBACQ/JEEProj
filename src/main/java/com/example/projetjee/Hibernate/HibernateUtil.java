package com.example.projetjee.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil{
/*    private static SessionFactory sessionFactory;

    private HibernateUtil(){
        sessionFactory = buildSessionFactory();
    }

    private static SessionFactory buildSessionFactory() {
        try {
            // Création de la SessionFactory à partir du fichier de configuration hibernate.cfg.xml
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Gérer les éventuelles exceptions
            System.err.println("Initialisation de la SessionFactory a échoué : " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory==null){
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }*/

    public static SessionFactory getCurrentSessionFromJPA(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(org.hibernate.Session.class);
        SessionFactory sf = session.getSessionFactory();
        return sf;
    }
}