package com.example.projetjee.DAO;

import com.example.projetjee.HibernateUtil;
import com.example.projetjee.Hibernate.SiteUser;
import org.hibernate.Session;


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
            e.printStackTrace();
        }
    }

    @Override
    public SiteUser getUserById(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            SiteUser user = (SiteUser) session.createQuery("SELECT SiteUser FROM SiteUser WHERE SiteUser.id = :id",SiteUser.class);
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
            List<SiteUser> users = session.createQuery("SELECT u FROM SiteUser u",SiteUser.class).list();
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
