package com.epam.university.spring.enote.repository.mock;

import static org.junit.Assert.assertEquals;

import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.services.GenericService;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class NotepadServiceImplTest {
  @Mock
  private GenericDao<Notepad> notepadRepository;
  private GenericService<Notepad> notepadService;
  private Notepad notepad;
  private Integer notepadID = 1234;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    notepadService = new NotepadServiceImpl(notepadRepository);

    notepad = new Notepad();
    notepad.setTitle("firstNotepad");
    notepad.setId(notepadID);
  }

  @Test
  public void getById() throws Exception {
    Mockito.when(notepadRepository.get(notepadID)).thenReturn(notepad);
    Notepad actual = notepadService.getById(notepadID);

    assertEquals(notepad, actual);
  }

  @Test
  public void getAll() throws Exception {
    List<Notepad> expected = Collections.singletonList(notepad);
    Mockito.when(notepadRepository.getAll()).thenReturn(expected);
    List<Notepad> actual = notepadService.getAll();

    assertEquals(expected, actual);
  }

  @Test
  public void save() throws Exception {
    Mockito.when(notepadRepository.save(notepad)).thenReturn(notepad);
    Notepad actual = notepadService.create(notepad);

    assertEquals(notepad, actual);
  }

  @Test
  public void saveAll() throws Exception {
    List<Notepad> expected = Collections.singletonList(notepad);
    Mockito.when(notepadRepository.saveAll(expected)).thenReturn(expected);
    List<Notepad> actual = notepadService.createFromList(expected);

    assertEquals(expected, actual);
  }

  @Test
  public void delete() throws Exception {
  }

  @Test
  public void deleteAll() throws Exception {
  }

}