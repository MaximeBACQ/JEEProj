package com.example.projetjee.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bankAccount", schema = "database_jee")
public class BankAccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "bankId")
    private int bankId;

    @Column(name="bankCode")
    private long bankCode;

    @Column(name="bankDate")
    private String bankDate;

    @Column(name="cvv")
    private int cvv;

    @Column(name="bankBalance")
    private int bankBalance;

    //@OneToMany(mappedBy = "bankId")
    //@OneToOne(mappedBy = "SiteUser", cascade = CascadeType.ALL)
    //private SiteUser user;


    public BankAccountEntity(){};
    public BankAccountEntity(long bankCode, String bankDate, int cvv, int bankBalance){
        this.bankCode = bankCode;
        this.bankDate = bankDate;
        this.cvv = cvv;
        this.bankBalance = bankBalance;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public long getBankCode() {
        return bankCode;
    }

    public void setBankCode(long bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankDate() {
        return bankDate;
    }

    public void setBankDate(String bankDate) {
        this.bankDate = bankDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(int bankBalance) {
        this.bankBalance = bankBalance;
    }
    @Override
    public String toString() {
        return "bankAccount{"+
                "bankId=" + bankId +
                "bankCode=" + bankCode +
                "bankDate=" + bankDate +
                "cvv" + bankBalance +
                "bankBalance" + bankBalance +
                "}";
    }
}
