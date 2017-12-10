package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.AbstractBaseEntity;

import java.util.List;

public interface GenericService<T extends AbstractBaseEntity> {

    T getById(Integer id);

    List<T> getAll();

    T create(T entity);

    List<T> createFromList(List<T> entities);

    void update(T entity);

    void delete(T entity);

    void deleteAll();
}
