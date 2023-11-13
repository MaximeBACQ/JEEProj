package com.example.projetjee.DAO;

import com.example.projetjee.Model.BankAccountEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.TypedQuery;

import java.util.List;
public class BankDAO extends GenericDAO<BankAccountEntity>{
    public BankDAO() {
        super(BankAccountEntity.class);
    }
    public void createBank(BankAccountEntity bankAccountEntity){
        create(bankAccountEntity);
    }
    public BankAccountEntity findBankById(int id){return findById(id);}
    public boolean isAccountValid(int bankCode, String bankDate, int cvv, int price){
        TypedQuery<BankAccountEntity> query = entityManager.createQuery("SELECT b FROM BankAccountEntity b WHERE b.bankCode = :bankCode AND b.bankDate = :bankDate AND b.cvv = :cvv AND b.bankBalance >= :price", BankAccountEntity.class);
        query.setParameter("bankCode", bankCode);
        query.setParameter("bankDate", bankDate);
        query.setParameter("cvv", cvv);
        query.setParameter("price", price);
        List<BankAccountEntity> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void accountPay(int bankCode, int price){
        TypedQuery<BankAccountEntity> query = entityManager.createQuery("UPDATE BankAccountEntity SET BankAccountEntity.bankBalance = BankAccountEntity.bankBalance - :price WHERE BankAccountEntity .bankCode = :bankCode", BankAccountEntity.class);
        query.setParameter("bankCode", bankCode);
        query.setParameter("price", price);
    }
}