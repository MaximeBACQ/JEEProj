package com.example.projetjee.Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;

public class HibernateUtil{
    private static  SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            File configFile = new File("hibernate.cfg.xml");
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure(configFile)
                    .build();

            return new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed.");
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}