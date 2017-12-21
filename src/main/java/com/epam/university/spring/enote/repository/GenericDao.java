package com.epam.university.spring.enote.repository;

import com.epam.university.spring.enote.model.genericmodels.AbstractBaseEntity;

import java.util.List;

public interface GenericDao<T extends AbstractBaseEntity> {
    //final or not; null if not found
    T get(Integer id);

    List<T> getAll();

    T save(T entity);

    List<T> saveAll(List<T> entity);

    boolean delete(T entity);

    boolean deleteAll();
}
