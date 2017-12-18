package com.epam.university.spring.enote.controllers;

import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    return "hello, it's a enote";
  }

  @GetMapping("/users")
  List<User> getAllUsers(){
    return userService.getAll();
  }
}
