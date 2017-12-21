package com.epam.university.spring.enote.services.springdataservices.impls;

import com.epam.university.spring.enote.model.springdatamodels.User;
import com.epam.university.spring.enote.repository.springdatajpa.UserRepository;
import com.epam.university.spring.enote.services.springdataservices.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getById(Long id) {
    return userRepository.findOne(id);
  }

  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @Override
  public User createOrUpdate(User user) {
    return userRepository.save(user);
  }

  @Override
  public void delete(Long id) {
    userRepository.delete(id);
  }
}
