package com.example.projetjee.DAO;

import com.example.projetjee.Model.CompanyEntity;
import com.example.projetjee.Model.SiteUser;
import jakarta.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;

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
        TypedQuery<SiteUser> query = entityManager.createQuery("SELECT u FROM SiteUser u WHERE u.email = :email", SiteUser.class);
        query.setParameter("email", email);

        List<SiteUser> resultList = query.getResultList();
        System.out.println("a");
        if (!resultList.isEmpty() & BCrypt.checkpw(password, resultList.get(0).getPassword())) {
            System.out.println("b");
            return resultList.get(0);
        } else {
            return null;
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

