package com.example.projetjee.DAO;

import com.example.projetjee.Hibernate.HibernateUtil;
import com.example.projetjee.Hibernate.SiteUser;
import com.example.projetjee.Hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.grammars.hql.HqlLexer;
import org.hibernate.query.hql.HqlInterpretationException;


import java.util.List;


public class UserDAOImpl implements UserDAO{
    @Override
    public void addUser(SiteUser user) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            //security
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println("Failed to insert user : " + e);
        }
    }

    @Override
    public SiteUser getUserById(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            SiteUser user = (SiteUser) session.createQuery("select SiteUser from SiteUser where SiteUser.id = id",SiteUser.class);
            //if we want to call a specific field use the beans field, not database's fields
            session.getTransaction().commit();
            System.out.println(user.toString());
            return user;
        }catch(Exception e){
            System.out.println("Couldn't fetch users : " + e);
            return null;
        }
    }

    @Override
    public List<SiteUser> getAllUsers() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            List<SiteUser> users = session.createQuery("select u from SiteUser u",SiteUser.class).list();
            //if we want to call a specific field use the beans field, not database's fields
            for(SiteUser s : users){
                System.out.println(s.toString());
            }
            session.getTransaction().commit();
            return users;
        }catch(Exception e){
            System.out.println("Couldn't fetch users : " + e);
            return null;
        }
    }

    @Override
    public void updateUser(SiteUser userInfos) {

    }

    @Override
    public void deleteUser(int id) {

    }
}
