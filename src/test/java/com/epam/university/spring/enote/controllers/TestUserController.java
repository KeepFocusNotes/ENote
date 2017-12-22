package com.epam.university.spring.enote.controllers;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.university.spring.enote.config.AppConfig;
import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUserController {
  @Mock
  private UserService userService;
  @InjectMocks
  private UserController userController = new UserController(userService);

  private MockMvc mvc;

  private User user;
  private ObjectMapper objectMapper;

  @Before
  public void setupSpec() throws Exception {
    MockitoAnnotations.initMocks(this);
    mvc = MockMvcBuilders.standaloneSetup(userController).build();

    objectMapper = new ObjectMapper();

    user = new User();
    user.setEmail("asd@asd.ru");
    user.setPassword("asd13asd");
    user.setId(11);

  }

  @Test
  public void testCreateUser() throws Exception {
    String json = objectMapper.writeValueAsString(user);

    Mockito.when(userService.create(any())).thenReturn(user);

    mvc.perform(post("/users")
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void testGetUserById() throws Exception {
    Mockito.when(userService.getById(any())).thenReturn(user);

    mvc.perform(get("/users/" + user.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetAllUsers() throws Exception {
    Mockito.when(userService.getAll()).thenReturn(Collections.EMPTY_LIST);

    mvc.perform(get("/users")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void testDeleteUserById() throws Exception {
    mvc.perform(delete("/users/" + user.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    Mockito.verify(userService).delete(any(User.class));
  }
}
