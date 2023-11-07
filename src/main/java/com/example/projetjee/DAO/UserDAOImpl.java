package com.example.projetjee.DAO;

import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.*;
import org.hibernate.Session;
import com.example.projetjee.Model.SiteUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.util.List;


public class UserDAOImpl implements UserDAO{
    public static UserDAOImpl userDaoImpl = new UserDAOImpl();
    @Override
    public void addUser(SiteUser user) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            entityManager.persist(user);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
    }
        finally {
            entityManager.close();
        }
    }

    @Override
    public SiteUser getUserById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        SiteUser user = null;
        try {
            user = entityManager.find(SiteUser.class, id);
            if (user != null) {
                user = entityManager.find(SiteUser.class, id);
            }
        } catch (Exception e) {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                    e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public List<SiteUser> getAllUsers() {
        // not functional
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<SiteUser> listUsers = null;
        try {
            TypedQuery<SiteUser> query = entityManager.createQuery("SELECT u FROM users u", SiteUser.class);
            listUsers = query.getResultList();
        } finally {
            entityManager.close();
        }
        return listUsers;
    }

    @Override
    public void updateUser(SiteUser userInfos) {

    }

    @Override
    public void deleteUser(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            SiteUser user = entityManager.find(SiteUser.class, id);

            if (user != null) {
                entityManager.remove(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteUser(String username) {
        //Not functional
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            SiteUser user = entityManager.find(SiteUser.class, username);

            if (user != null) {
                entityManager.remove(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


}

