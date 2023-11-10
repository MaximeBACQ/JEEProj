package com.example.projetjee.DAO;

import com.example.projetjee.Model.ProductEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductDAO implements GenericDAO<ProductEntity>{
    public void addProduct(ProductEntity product){
        EntityManager entityManager = JPAUtil.getEntityManager();

        try{
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(product);
            transaction.commit();
        }
        catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally {
            entityManager.close();
            entityManager.getTransaction().commit();
        }
    }

    public ProductEntity getProductById(int id){
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            TypedQuery<ProductEntity> query = entityManager.createQuery("SELECT u FROM ProductEntity u", ProductEntity.class);
            List<ProductEntity> resultList = query.getResultList();

            if (!resultList.isEmpty()) {
                transaction.commit();

                return resultList.get(0);
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
        return null;
    }

    @Override
    public ProductEntity findById(Long id) {
        return null;
    }

    @Override
    public List<ProductEntity> findAll() {
        return null;
    }

    @Override
    public void create() {

    }

    @Override
    public void save(ProductEntity productEntity) {

    }

    @Override
    public void update(ProductEntity productEntity) {

    }

    @Override
    public void delete(Long id) {

    }
}
