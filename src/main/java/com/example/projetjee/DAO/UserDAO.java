package com.example.projetjee.DAO;

import com.example.projetjee.Model.SiteUser;

import java.util.List;
public interface UserDAO {
    void addUser(SiteUser user);
    SiteUser getUserById(int id);
    List<SiteUser> getAllUsers(); //not functional
    void updateUser(SiteUser userInfos);
    void deleteUser(int id); //we'll implement some logic so that we can fetch the user to delete by its id
    void deleteUser(String username); //not functional
    void promoteUser(String username);
}
