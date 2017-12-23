package com.epam.university.spring.enote.services;

import static com.epam.university.spring.enote.NotepadTestData.LIST_NOTEPADS_TO_CREATE;
import static com.epam.university.spring.enote.NotepadTestData.NOTEPADS_INITIALIZED;
import static com.epam.university.spring.enote.NotepadTestData.NOTEPAD_FIRST;
import static com.epam.university.spring.enote.NotepadTestData.NOTEPAD_FIRST_ID;
import static com.epam.university.spring.enote.NotepadTestData.NOTEPAD_LAST;
import static com.epam.university.spring.enote.NotepadTestData.NOTEPAD_LAST_ID;
import static com.epam.university.spring.enote.NotepadTestData.NOTEPAD_TO_CREATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.epam.university.spring.enote.config.AppConfig;
import com.epam.university.spring.enote.model.AbstractBaseEntity;
import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.util.exception.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public class NotepadServiceTest {

    @Autowired
    private NotepadService notepadService;

    @Test
    public void getByIdFirstNotepad() throws Exception {
        assertEquals(notepadService.getById(NOTEPAD_FIRST_ID), NOTEPAD_FIRST);
        //assertMatch(notepadService.getById(NOTEPAD_FIRST_ID), NOTEPAD_FIRST);
    }

    @Test
    public void getByIdLastNotepad() throws Exception {
        assertEquals(notepadService.getById(NOTEPAD_LAST_ID), NOTEPAD_LAST);
        //assertMatch(actual,NOTEPAD_FIRST);
    }

    @Test
    public void getAll() throws Exception {
        List<Notepad> notepadsAll = notepadService.getAll();
        notepadsAll.sort(Comparator.comparing(AbstractBaseEntity::getId));
        int counter = 1;
        for (Notepad notepad : notepadsAll) {
            if (counter != notepad.getId() | counter > NOTEPADS_INITIALIZED) {
                throw new NotFoundException("Entity with needed id not found.");
            }
            counter++;
        }
        assertTrue(NOTEPADS_INITIALIZED == notepadsAll.size());
    }

    @Test
    public void create() throws Exception {
        NOTEPAD_TO_CREATE.setId(notepadService.create(NOTEPAD_TO_CREATE).getId());
        assertEquals(notepadService.getById(NOTEPAD_TO_CREATE.getId()), NOTEPAD_TO_CREATE);
    }

    @Test
    public void update() throws Exception {
        Notepad notepadToUpdate = new Notepad(NOTEPAD_FIRST);
        notepadToUpdate.setTitle("UpdatedTitle");
        notepadService.update(notepadToUpdate);
        assertEquals(notepadService.getById(notepadToUpdate.getId()), notepadToUpdate);
    }

    @Test
    public void createFromList() throws Exception {
        List<Notepad> expected = notepadService.getAll();
        notepadService.createFromList(LIST_NOTEPADS_TO_CREATE);
        expected.addAll(LIST_NOTEPADS_TO_CREATE);
        List<Notepad> actual = notepadService.getAll();
        assertTrue(actual.containsAll(expected)
                & expected.containsAll(actual));
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        notepadService.delete(NOTEPAD_FIRST);
        notepadService.getById(NOTEPAD_FIRST.getId());
    }

    @Test
    public void deleteAll() throws Exception {
        notepadService.deleteAll();
        assertTrue(notepadService.getAll().size() == 0);
    }

    @Test
    public void getNotepadsByUserId() {
        Set<Notepad> notepads = notepadService.getByUserId(NOTEPAD_LAST.getUser().getId());
        assertTrue(notepads.stream().allMatch(notepad -> notepad.getUser().getId().equals(
                NOTEPAD_LAST.getUser().getId())));
    }
}