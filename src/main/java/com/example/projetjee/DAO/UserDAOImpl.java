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
    private JPAUtil jpaUtil;
    //public static UserDAOImpl userDaoImpl = new UserDAOImpl();
    @Override
    public void addUser(SiteUser user) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try{
            //EntityTransaction transaction = entityManager.getTransaction();
            entityManager.getTransaction().begin();
            entityManager.merge(user);
        }
        catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    @Override
    public SiteUser getUserById(int id) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            TypedQuery<SiteUser> query = entityManager.createQuery("SELECT u FROM SiteUser u", SiteUser.class);
            List<SiteUser> resultList = query.getResultList();

            if (!resultList.isEmpty()) {
                transaction.commit();

                return resultList.get(0);
            } else {
                return null;
            }
        }catch(Exception e){
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                    entityManager.getTransaction().rollback();
                }
            e.printStackTrace();
        }finally {
        entityManager.close();
        }
        return null;
}
    @Override
    public List<SiteUser> getAllUsers() {
        // not functional
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            TypedQuery<SiteUser> query = entityManager.createQuery("SELECT u FROM SiteUser u", SiteUser.class);
            List<SiteUser> resultList = query.getResultList();

            if (!resultList.isEmpty()) {
                transaction.commit();

                return resultList;
            } else {
                return null;
            }

//            for (SiteUser user : resultList) {
//                System.out.println(user.getName());
//            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        /*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<SiteUser> listUsers = null;
        try {
@@ -87,7 +142,8 @@ public List<SiteUser> getAllUsers() {
        } finally {
            entityManager.close();
        }
        return listUsers;*/
        return null;
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

    @Override
    public void promoteUser(String username){

    }


}

