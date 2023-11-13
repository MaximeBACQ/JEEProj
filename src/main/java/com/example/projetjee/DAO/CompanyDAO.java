package com.example.projetjee.DAO;

import com.example.projetjee.Model.CompanyEntity;

import java.util.List;

public class CompanyDAO extends GenericDAO<CompanyEntity> implements InterfaceDAO<CompanyEntity> {
    public CompanyDAO(){
        super(CompanyEntity.class);
    }

    public void createCompany(CompanyEntity company) {
        create(company);
    }

    public CompanyEntity findCompanyById(int id) {
        return findById(id);
    }

    public void updateCompany(CompanyEntity company) {
        update(company);
    }

    public void deleteCompany(CompanyEntity company) {
        delete(company);
    }

    public List<CompanyEntity> findAllCompanies() {
        return findAll();
    }
}
