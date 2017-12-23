package com.epam.university.spring.enote.controllers;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.university.spring.enote.config.AppConfig;
import com.epam.university.spring.enote.model.Note;
import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.model.Tag;
import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.services.NoteService;
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
public class TestNoteController {
  @Mock
  private NoteService noteService;

  @InjectMocks
  private NoteController noteController = new NoteController(noteService);

  private MockMvc mvc;

  private Note note;
  private Tag tag;

  private ObjectMapper objectMapper;
  private Notepad notepad;
  private User user;

  @Before
  public void setupSpec() throws Exception {
    MockitoAnnotations.initMocks(this);
    mvc = MockMvcBuilders.standaloneSetup(noteController).build();

    objectMapper = new ObjectMapper();

    note = new Note();
    note.setNotepad(null);
    note.setTitle("theFirstNote");
    note.setDescription("somedescriptionhere");
    note.setId(11);

    tag = new Tag();
    tag.setId(12);
    tag.setTitle("title");
    tag.setNotes(null);

    notepad = new Notepad();
    notepad.setTitle("sometitle");
    notepad.setId(13);

    user = new User();
    user.setId(14);
  }

  @Test
  public void testCreateNote() throws Exception {
    String json = objectMapper.writeValueAsString(note);

    Mockito.when(noteService.create(any())).thenReturn(note);

    mvc.perform(post("/notes")
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void testGetNoteById() throws Exception {
    Mockito.when(noteService.getById(any())).thenReturn(note);

    mvc.perform(get("/notes/" + note.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetAllNotes() throws Exception {
    Mockito.when(noteService.getAll()).thenReturn(Collections.EMPTY_LIST);

    mvc.perform(get("/notes")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void testDeleteNoteById() throws Exception {
    mvc.perform(delete("/notes/" + 11)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    Mockito.verify(noteService).delete(any(Note.class));
  }

  @Test
  public void testAddTagToNote() throws Exception{
    mvc.perform(post("/notes/" + note.getId() + "/tags/" + tag.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    Mockito.verify(noteService).addTagToNoteById(note.getId(), tag.getId());
  }

  @Test
  public void testGetTagsByNoteId() throws Exception{
    Mockito.when(noteService.getTags(note.getId())).thenReturn(Collections.EMPTY_SET);

    mvc.perform(get("/notes/" + note.getId() + "/tags")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetNotesByNotepadId() throws Exception{
    Mockito.when(noteService.getByNotepadId(notepad.getId())).thenReturn(Collections.EMPTY_SET);

    mvc.perform(get("/notepads/" + notepad.getId() + "/notes")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetNotesByUserId() throws Exception{
    Mockito.when(noteService.getNotesByUserId(user.getId())).thenReturn(Collections.EMPTY_SET);

    mvc.perform(get("/notepads/" + user.getId() + "/notes")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
