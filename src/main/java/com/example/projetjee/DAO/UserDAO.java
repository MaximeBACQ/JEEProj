package com.example.projetjee.DAO;

import com.example.projetjee.Hibernate.SiteUser;

import java.util.List;

public interface UserDAO {
    void addUser(SiteUser user);
    SiteUser getUserById(int id);
    List<SiteUser> getAllUsers();
    void updateUser(SiteUser userInfos);
    void deleteUser(int id); //we'll implement some logic so that we can fetch the user to delete by its id
}
