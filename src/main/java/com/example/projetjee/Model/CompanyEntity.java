package com.example.projetjee.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "company", schema = "database_jee")
public class CompanyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "companyId")
    private int companyId;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "company")
    private List<ProductEntity> products;

    @OneToMany(mappedBy = "company")
    private List<SiteUser> workers;

    public CompanyEntity(){}
    public CompanyEntity(String name){
        this.name = name;
        this.products = null;
        this.workers = null;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public List<SiteUser> getUserId() {
        return workers;
    }

    public void setUserId(List<SiteUser> users) {
        this.workers = users;
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
