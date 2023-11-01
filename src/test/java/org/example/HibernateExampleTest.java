package org.example;

import com.example.projetjee.Hibernate.HibernateUtil;
import com.example.projetjee.Hibernate.SiteUser;
import jdk.jfr.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class HibernateExampleTest {

    private static SessionFactory sessionFactory;

    @BeforeEach


    protected void setUp() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build();
        try {
            sessionFactory =
                    new MetadataSources(registry)
                            .addAnnotatedClass(SiteUser.class)
                            .buildMetadata()
                            .buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            System.err.println("Initialisation de la SessionFactory a échoué : " + e);
            StandardServiceRegistryBuilder.destroy(registry);

            throw new ExceptionInInitializerError(e);
        }
    }



    @Test
    void save_my_first_object_in_db(){
        SiteUser user = new SiteUser("Walter","WHITE","W.W","sussybaka@gmail.com",LocalDate.now(),"mechanic","waltpass",false,false);
        try(Session session = sessionFactory.openSession()){

            //security
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Test
    void hql_fetch_users(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            List<SiteUser> users = session.createQuery("select u from SiteUser u",SiteUser.class).list();
            //if we want to call a specific field use the beans field, not database's fields
            for(SiteUser s : users){
                System.out.println(s.toString());
            }
            session.getTransaction().commit();

        }
    }
}
