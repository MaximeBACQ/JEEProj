package com.example.projetjee.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VotrePersistenceUnit");
    private static EntityManager entityManager;

    private JPAUtil() {
        // Empêche l'instanciation depuis l'extérieur
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static void closeEntityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}


//
//    private EntityManager entityManager;
//    private static JPAUtil JPAUtilInstance;
//    private JPAUtil(){
//        entityManager = createEM();
//    }
//    public EntityManager createEM(){
//        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence")) {
//            return entityManagerFactory.createEntityManager();
//        }catch(Exception e){
//            throw new ExceptionInInitializerError("There was an error with some informations, please try reloading the page");
//        }
//    }
//
//    public static JPAUtil getJPAUtilInstance(){
//        if(JPAUtilInstance == null){
//            JPAUtilInstance = new JPAUtil();
//        }
//        return JPAUtilInstance;
//    }
//
//    public static EntityManager getEntityManager(){
//        JPAUtil.getJPAUtilInstance();
//        return getJPAUtilInstance().entityManager;
//    }*/
//}
