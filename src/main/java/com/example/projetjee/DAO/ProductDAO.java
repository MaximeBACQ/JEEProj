package com.example.projetjee.DAO;

import com.example.projetjee.Model.CompanyEntity;
import com.example.projetjee.Model.ProductEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends GenericDAO<ProductEntity> implements InterfaceDAO<ProductEntity> {
    public ProductDAO(){
        super(ProductEntity.class);
    }

    public void createProduct(ProductEntity product) {
        create(product);
    }

    public ProductEntity findProductById(int id) {
        return findById(id);
    }

    public void updateProduct(ProductEntity product) {
        update(product);
    }

    public void deleteProduct(ProductEntity product) {
        delete(product);
    }

    public List<ProductEntity> findAllProducts() {
        return findAll();
    }

    public List<ProductEntity> searchProductsByLabelAndDescription(String searchLabelOrDescription) {

        String jpql = "SELECT p FROM ProductEntity p WHERE p.label LIKE :searchLabelOrDescription OR p.description LIKE :searchLabelOrDescription";

        TypedQuery<ProductEntity> query = entityManager.createQuery(jpql, ProductEntity.class);
        query.setParameter("searchLabelOrDescription", "%" + searchLabelOrDescription + "%");

        return query.getResultList();
    }

}
