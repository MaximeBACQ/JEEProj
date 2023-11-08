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


}
