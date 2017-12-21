package com.epam.university.spring.enote.controllers;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.university.spring.enote.config.AppConfig;
import com.epam.university.spring.enote.model.Tag;
import com.epam.university.spring.enote.services.TagService;
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
public class TestTagController {
  @Mock
  private TagService tagService;
  @InjectMocks
  private TagController tagController = new TagController(tagService);

  private MockMvc mvc;

  private Tag tag;
  private ObjectMapper objectMapper;

  @Before
  public void setupSpec() throws Exception {
    MockitoAnnotations.initMocks(this);
    mvc = MockMvcBuilders.standaloneSetup(tagController).build();

    objectMapper = new ObjectMapper();

    tag = new Tag();
    tag.setTitle("sometitle");
    tag.setId(11);

  }

  @Test
  public void testCreateTag() throws Exception {
    String json = objectMapper.writeValueAsString(tag);

    Mockito.when(tagService.create(any())).thenReturn(tag);

    mvc.perform(post("/tags")
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void testGetTagById() throws Exception {
    Mockito.when(tagService.getById(any())).thenReturn(tag);

    mvc.perform(get("/tags/" + tag.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetAllUsers() throws Exception {
    Mockito.when(tagService.getAll()).thenReturn(Collections.EMPTY_LIST);

    mvc.perform(get("/tags")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void testDeleteUserById() throws Exception {
    mvc.perform(delete("/tags/" + tag.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    Mockito.verify(tagService).delete(any(Tag.class));
  }
}
