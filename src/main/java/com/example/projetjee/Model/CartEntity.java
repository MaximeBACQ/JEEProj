package com.example.projetjee.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    @Column(name = "quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private SiteUser user;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private ProductEntity product;

    public CartEntity() {
    }

    public CartEntity(int quantity, SiteUser user, ProductEntity product) {
        this.quantity = quantity;
        this.user = user;
        this.product = product;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SiteUser getUser() {
        return user;
    }

    public void setUser(SiteUser user) {
        this.user = user;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "cartId=" + cartId +
                ", quantity=" + quantity +
                ", userId=" + user.getUserId() +
                ", productId=" + product.getProductId() +
                '}';
    }
}