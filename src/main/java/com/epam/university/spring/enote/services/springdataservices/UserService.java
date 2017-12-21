package com.epam.university.spring.enote.services.springdataservices;

import com.epam.university.spring.enote.model.springdatamodels.User;
import java.util.List;

public interface UserService {
  User getById(Long id);

  List<User> getAll();

  User createOrUpdate(User user);

  void delete(Long id);
}
