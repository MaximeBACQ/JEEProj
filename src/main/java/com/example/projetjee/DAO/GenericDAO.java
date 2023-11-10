package com.example.projetjee.DAO;

import com.example.projetjee.Model.SiteUser;

import java.util.List;

public interface GenericDAO<Entity> {
    Entity findById(Long id);
    List<Entity> findAll();
    void create();

    void save(Entity entity);

    void update(Entity entity);
    void delete(Long id);
}