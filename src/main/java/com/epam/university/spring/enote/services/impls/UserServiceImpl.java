package com.epam.university.spring.enote.services.impls;

import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.services.DBService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements DBService<User> {

  private final GenericDao<User> userRepository;

  @Autowired
  public UserServiceImpl(
      GenericDao<User> userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getById(Integer id) {
    return null;
  }

  @Override
  public List<User> getAll() {
    return null;
  }

  @Override
  public User save(User element) {
    return null;
  }

  @Override
  public List<User> saveAll(List<User> elements) {
    return null;
  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }

  @Override
  public boolean deleteAll() {
    return false;
  }
}
