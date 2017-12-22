package com.epam.university.spring.enote.controllers;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.university.spring.enote.config.AppConfig;
import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.services.NotepadService;
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
public class TestNotepadController {
  @Mock
  private NotepadService notepadService;
  @InjectMocks
  private NotepadController notepadController = new NotepadController(notepadService);

  private MockMvc mvc;

  private Notepad notepad;
  private ObjectMapper objectMapper;

  @Before
  public void setupSpec() throws Exception {
    MockitoAnnotations.initMocks(this);
    mvc = MockMvcBuilders.standaloneSetup(notepadController).build();

    objectMapper = new ObjectMapper();

    notepad = new Notepad();
    notepad.setTitle("sometitle");
    notepad.setId(11);
  }

  @Test
  public void testCreateNotepad() throws Exception {
    String json = objectMapper.writeValueAsString(notepad);

    Mockito.when(notepadService.create(any())).thenReturn(notepad);

    mvc.perform(post("/notepads")
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void testGetNotepadById() throws Exception {
    Mockito.when(notepadService.getById(any())).thenReturn(notepad);

    mvc.perform(get("/notepads/" + notepad.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetAllNotepads() throws Exception {
    Mockito.when(notepadService.getAll()).thenReturn(Collections.EMPTY_LIST);

    mvc.perform(get("/notepads")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void testDeleteNotepadById() throws Exception {
    mvc.perform(delete("/notepads/" + notepad.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    Mockito.verify(notepadService).delete(any(Notepad.class));
  }


}
