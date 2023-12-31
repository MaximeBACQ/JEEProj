package com.example.projetjee.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "product", schema = "database_jee")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "productId")
    private int productId;
    @Basic
    @Column(name = "label")
    private String label;
    @Basic
    @Column(name = "price")
    private int price;
    @Basic
    @Column(name = "stock")
    private int stock;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "productImage")
    private String productImage;
    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private CompanyEntity company;

    public ProductEntity(){}


    public ProductEntity(String label, int price, int stock, String description, String productImage, CompanyEntity company){
       this.label = label;
       this.price = price;
       this.stock = stock;
       this.description = description;
       this.company = company;
       this.productImage = productImage;
    }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImage() {return productImage;}

    public void setProductImage(String productImage) {this.productImage = productImage;}

    public CompanyEntity getCompanyId() {
        return company;
    }

    public void setCompanyId(CompanyEntity company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "productId=" + productId +
                ", label='" + label + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", image link" + productImage +'\'' +
                ", companyId=" + company.getCompanyId() +
                '}';
    }
}
