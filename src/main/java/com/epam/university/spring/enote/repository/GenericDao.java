package com.epam.university.spring.enote.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Serializable> {
    //final or not; null if not found
    T get(Integer id);

    List<T> getAll();

    T save(T entity);

    List<T> saveAll(List<T> entity);

    boolean delete(T entity);

    boolean deleteAll();
}
