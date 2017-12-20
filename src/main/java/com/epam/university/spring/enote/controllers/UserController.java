package com.epam.university.spring.enote.controllers;

import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public String hello(){
    return "hello, it's an enote";
  }

  @GetMapping("/users")
  public List<User> getAll(){
    return userService.getAll();
  }

  @GetMapping("/users/{id}")
  public User getById(@PathVariable Integer id){
    return userService.getById(id);
  }

  @PostMapping("/users")
  @ResponseStatus(HttpStatus.CREATED)
  public User create(@RequestBody User user) {
    return userService.create(user);
  }

  @DeleteMapping("/users/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Integer id){
    User toPassUser = new User();
    toPassUser.setId(id);
    userService.delete(toPassUser);
  }
}
