package com.epam.university.spring.enote.services;

import java.util.List;

public interface DBService<T> {

  T getById(Integer id);

  List<T> getAll();

  T save(T element);

  List<T> saveAll(List<T> elements);

  boolean delete(Integer id);

  boolean deleteAll();
}
