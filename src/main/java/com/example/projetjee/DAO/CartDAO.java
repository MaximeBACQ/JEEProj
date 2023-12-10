package com.example.projetjee.DAO;

import com.example.projetjee.Model.CartEntity;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CartDAO extends GenericDAO<CartEntity> implements InterfaceDAO<CartEntity> {
    public CartDAO(){
        super(CartEntity.class);
    }

    public void createCart(CartEntity cart) {
        create(cart);
    }

    public CartEntity findCartById(int id) {
        return findById(id);
    }

    public void updateCart(CartEntity cart) {
        update(cart);
    }

    public void deleteCart(CartEntity cart) {
        delete(cart);
    }

    public List<CartEntity> findAllCarts() {
        return findAll();
    }

    public List<CartEntity> findCartsByUserId(int userId) {
        String jpql = "SELECT c FROM CartEntity c WHERE c.user.id = :userId";
        TypedQuery<CartEntity> query = entityManager.createQuery(jpql, CartEntity.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public CartEntity findCartByUserAndProduct(int userId, int productId){
        String jpql = "SELECT c FROM CartEntity c WHERE c.user.id = :userId and c.product.id = :productId";
        TypedQuery<CartEntity> query = entityManager.createQuery(jpql, CartEntity.class);
        query.setParameter("userId", userId);
        query.setParameter("productId", productId);

        List<CartEntity> resultList = query.getResultList();

        if (!resultList.isEmpty()) {
            return resultList.get(0); // Si la liste n'est pas vide, retournez le premier élément
        } else {
            return null; // Aucun résultat trouvé, retournez null
        }
    }
}
