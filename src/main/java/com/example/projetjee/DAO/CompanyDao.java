package com.example.projetjee.DAO;

import com.example.projetjee.Model.CompanyEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CompanyDao implements GenericDAO<CompanyEntity> {
    @Override
    public CompanyEntity findById(Long id){
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            TypedQuery<CompanyEntity> query = entityManager
                    .createQuery("SELECT cp FROM CompanyEntity cp WHERE id = :id", CompanyEntity.class);

            query.setParameter("id", id);
            List<CompanyEntity> resultList = query.getResultList();

            if (!resultList.isEmpty()) {
                transaction.commit();

                return resultList.get(0);
            } else {
                throw new UserExistsException("No company matching this id found.");
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
    public List<CompanyEntity> findAll() {
        return null;
    }

    @Override
    public void create() {

    }

    @Override
    public void save(CompanyEntity companyEntity) {

    }

    @Override
    public void update(CompanyEntity companyEntity) {

    }

    @Override
    public void delete(Long id) {

    }
}
