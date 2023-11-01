package com.example.projetjee.DAO;

import com.example.projetjee.Hibernate.HibernateUtil;
import com.example.projetjee.Hibernate.SiteUser;
import com.example.projetjee.Hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;


public class UserDAOImpl implements UserDAO{
    @Override
    public void addUser(SiteUser user) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            //security
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public SiteUser getUserById(int id) {
        return null;
    }

    @Override
    public List<SiteUser> getAllUsers() {
        return null;
    }

    @Override
    public void updateUser(SiteUser userInfos) {

    }

    @Override
    public void deleteUser(int id) {

    }
}
