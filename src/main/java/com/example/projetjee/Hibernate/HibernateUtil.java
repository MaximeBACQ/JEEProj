package com.example.projetjee.Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;

public class HibernateUtil{
    private static  SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        File configFile = new File("hibernate.cfg.xml");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(configFile)
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(SiteUser.class)
                    .buildMetadata()
                    .buildSessionFactory();
            return sessionFactory;
        } catch (Exception e) {
            System.err.println("Initialisation de la SessionFactory a échoué : " + e);
            StandardServiceRegistryBuilder.destroy(registry);

            throw new ExceptionInInitializerError(e);
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