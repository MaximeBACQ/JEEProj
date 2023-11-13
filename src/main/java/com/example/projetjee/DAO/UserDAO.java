package com.example.projetjee.DAO;

import com.example.projetjee.Model.CompanyEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDAO extends GenericDAO<SiteUser> implements InterfaceDAO<SiteUser> {

    public UserDAO(){
        super(SiteUser.class);
    }

    public void createUser(SiteUser user) throws UserExistenceException {
        create(user);
    }

    public SiteUser findUserById(int id) throws UserExistenceException {
        return findById(id);
    }

    public void updateUser(SiteUser user) {
        update(user);
    }

    public void deleteUser(SiteUser user) {
        delete(user);
    }

    public List<SiteUser> findAllUsers() {
        return findAll();
    }

    public SiteUser authenticateUser(String email, String password) {
        TypedQuery<SiteUser> query = entityManager.createQuery("SELECT u FROM SiteUser u WHERE u.email = :email AND u.password = :password", SiteUser.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        List<SiteUser> resultList = query.getResultList();

        if (!resultList.isEmpty()) {
            return resultList.get(0); // User found
        } else {
            return null; // No user found
        }
    }

    public SiteUser findUserByEmail(String email) {
        TypedQuery<SiteUser> query = entityManager.createQuery("SELECT u FROM SiteUser u WHERE u.email = :email", SiteUser.class);
        query.setParameter("email", email);
            SiteUser user;
        try {
            user = query.getSingleResult();
        } catch (jakarta.persistence.NoResultException ignored) {
            return null;
        }
        return user;

    }
}

