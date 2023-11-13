package com.example.projetjee.DAO;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.EntityManager;

import java.util.List;
public class GenericDAO<Entity> implements InterfaceDAO<Entity> {

    protected EntityManager entityManager = JPAUtil.getEntityManager();

    protected final Class<Entity> entityClass;

    protected final EntityTransaction transaction = entityManager.getTransaction();

    public GenericDAO(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(Entity entity) {

        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }

    public void update(Entity entity) {
        transaction.begin();
        entityManager.merge(entity);
        transaction.commit();
    }

    public void delete(Entity entity) {
        transaction.begin();
        entityManager.remove(entity);
        transaction.commit();
    }

    public Entity findById(int id) {
        return entityManager.find(entityClass, id);
    }

    public List<Entity> findAll() {
        return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
    }
}