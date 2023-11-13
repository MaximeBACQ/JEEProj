package com.example.projetjee.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public interface InterfaceDAO<Entity>{

    void create(Entity entity);

    void update(Entity entity);

    void delete(Entity entity);

    Entity findById(int id);

    List<Entity> findAll();

}
