package com.epam.university.spring.enote.services.mock;

import com.epam.university.spring.enote.model.genericmodels.Notepad;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.services.genericservices.GenericService;
import com.epam.university.spring.enote.services.genericservices.NotepadServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NotepadServiceMockTest {
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
        Mockito.when(notepadRepository.save(notepad)).thenReturn(notepad);
        List<Notepad> actual = notepadService.createFromList(expected);

        assertEquals(expected, actual);
    }
}