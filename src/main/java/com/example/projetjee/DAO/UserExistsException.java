package com.example.projetjee.DAO;

public class UserExistsException extends Exception {
    public UserExistsException(String message) {
        super(message);
    }
}
