package com.example.projetjee.DAO;

import com.example.projetjee.Model.BankAccountEntity;
import com.example.projetjee.Model.CartEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.TypedQuery;

import java.util.List;
public class BankDAO extends GenericDAO<BankAccountEntity> implements InterfaceDAO<BankAccountEntity> {
    public BankDAO() {
        super(BankAccountEntity.class);
    }
    public void createBank(BankAccountEntity bankAccountEntity){
        create(bankAccountEntity);
    }

    public void updateBank(BankAccountEntity bankAccountEntity){
        update(bankAccountEntity);
    }

    public void deleteBank(BankAccountEntity bankAccountEntity){
        delete(bankAccountEntity);
    }

    public BankAccountEntity findBankById(int id){return findById(id);}
    public BankAccountEntity isAccountValid(long bankCode, String bankDate, int cvv){
        TypedQuery<BankAccountEntity> query = entityManager.createQuery("SELECT b FROM BankAccountEntity b WHERE" +
                " b.bankCode = :bankCode AND b.bankDate = :bankDate AND b.cvv = :cvv"
                , BankAccountEntity.class);
        query.setParameter("bankCode", bankCode);
        query.setParameter("bankDate", bankDate);
        query.setParameter("cvv", cvv);
        //List<BankAccountEntity> resultList = query.getResultList();
        //return !resultList.isEmpty(); // true if not empty
        return query.getSingleResult();
    }

    public void accountPay(long bankCode, int price){
        TypedQuery<BankAccountEntity> query = entityManager.createQuery("UPDATE BankAccountEntity SET BankAccountEntity.bankBalance = BankAccountEntity.bankBalance - :price WHERE BankAccountEntity .bankCode = :bankCode", BankAccountEntity.class);
        query.setParameter("bankCode", bankCode);
        query.setParameter("price", price);
    }
}
