package com.example.projetjee.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="users")
@NamedQuery(name="SiteUser.byEmailAndPass", query="SELECT u FROM SiteUser u WHERE u.email = ?1 and u.password = ?2")
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="username")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="birthDate")
    private LocalDate birthDate;
    @Column(name="gender")
    private String gender;
    @Column(name="password")
    private String password;
    @Column(name="isAdmin")
    private boolean isAdmin;
    @Column(name="isModerator")
    private boolean isModerator;
    @Column(name="isSeller")
    private boolean isSeller;
    @ManyToOne
    @JoinColumn(name = "companyId")
    private CompanyEntity company;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bankId")
    private BankAccountEntity bank;


    public SiteUser(){          //hibernate needs an empty constructor

    }

    public SiteUser(String name, String surname, String username, String email, LocalDate birthDate, String gender,
                    String password, boolean isAdmin, boolean isModerator,boolean isSeller){          //our needed constructor
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email=email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isModerator = isModerator;
        this.isSeller=isSeller;
        this.company = null;
        this.bank = null;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean getIsModerator() {return isModerator;}

    public void setIsModerator(boolean moderator) {isModerator = moderator;}

    public boolean getIsSeller(){return isSeller;}
    public void setIsSeller(boolean seller){isSeller = seller;}

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public BankAccountEntity getBank() {
        return bank;
    }

    public void setBank(BankAccountEntity bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        String out =
                "SiteUser{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", isModerator=" + isModerator +
                ", isSeller=" + isSeller;

        if(company != null){
            out +=  ", companyId=" + company.getCompanyId();
        }

        if(bank == null){
            return out + '}';
        }else{
            return out + ", bankId=" + bank.getBankId() + '}';
        }

    }
}
