package com.epam.university.spring.enote.services;

import static com.epam.university.spring.enote.NoteTestData.LIST_NOTES_TO_CREATE;
import static com.epam.university.spring.enote.NoteTestData.NOTES_INITIALIZED;
import static com.epam.university.spring.enote.NoteTestData.NOTE_FIRST;
import static com.epam.university.spring.enote.NoteTestData.NOTE_FIRST_ID;
import static com.epam.university.spring.enote.NoteTestData.NOTE_LAST;
import static com.epam.university.spring.enote.NoteTestData.NOTE_LAST_ID;
import static com.epam.university.spring.enote.NoteTestData.NOTE_TO_CREATE;
import static com.epam.university.spring.enote.NotepadTestData.NOTEPAD_FIRST_ID;
import static com.epam.university.spring.enote.TagTestData.TAG_FIRST_ID;
import static com.epam.university.spring.enote.TagTestData.TAG_LAST_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.epam.university.spring.enote.config.AppConfig;
import com.epam.university.spring.enote.model.AbstractBaseEntity;
import com.epam.university.spring.enote.model.Note;
import com.epam.university.spring.enote.model.Tag;
import com.epam.university.spring.enote.util.exception.NotFoundException;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
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
public class NoteServiceTest {

    private static Note NOTE_GET_TAGS;
    private static Tag TAG_FIRST_GET_TASKS;
    private static Tag TAG_LAST_GET_TASKS;
    private static Integer TAG_ID;

    @Autowired
    private NoteService noteService;
    @Autowired
    private TagService tagService;

    @Before
    public void setUp() {
        TAG_FIRST_GET_TASKS = tagService.getById(TAG_FIRST_ID);
        TAG_LAST_GET_TASKS = tagService.getById(TAG_LAST_ID);
        Set<Tag> tags = new HashSet<>();
        tags.add(TAG_FIRST_GET_TASKS);
        tags.add(TAG_LAST_GET_TASKS);
        NOTE_GET_TAGS = noteService.getById(NOTE_FIRST_ID);
        NOTE_GET_TAGS.setTags(tags);
        noteService.update(NOTE_GET_TAGS);
        TAG_ID = 333;
    }

    @Test
    public void getByIdFirstNote() {
        assertEquals(noteService.getById(NOTE_FIRST_ID), NOTE_FIRST);
        //assertMatch(noteService.getById(NOTE_FIRST_ID), NOTE_FIRST);
    }

    @Test
    public void getByIdLastNote() throws Exception {
        assertEquals(noteService.getById(NOTE_LAST_ID), NOTE_LAST);
        //assertMatch(actual,NOTE_FIRST);
    }

    @Test
    public void getAll() throws Exception {
        List<Note> notesAll = noteService.getAll();
        notesAll.sort(Comparator.comparing(AbstractBaseEntity::getId));
        int counter = 1;
        for (Note note : notesAll) {
            if (counter != note.getId() | counter > NOTES_INITIALIZED) {
                throw new NotFoundException("Entity with needed id not found.");
            }
            counter++;
        }
        assertTrue(NOTES_INITIALIZED == notesAll.size());
    }

    @Test
    public void create() throws Exception {
        NOTE_TO_CREATE.setId(noteService.create(NOTE_TO_CREATE).getId());
        assertEquals(noteService.getById(NOTE_TO_CREATE.getId()), NOTE_TO_CREATE);
    }

    @Test
    public void update() throws Exception {
        Note noteToUpdate = new Note(NOTE_FIRST);
        noteToUpdate.setTitle("UpdatedTitle");
        noteService.update(noteToUpdate);
        assertEquals(noteService.getById(noteToUpdate.getId()), noteToUpdate);
    }

    @Test
    public void createFromList() throws Exception {
        List<Note> expected = noteService.getAll();
        noteService.createFromList(LIST_NOTES_TO_CREATE);
        expected.addAll(LIST_NOTES_TO_CREATE);
        List<Note> actual = noteService.getAll();
        assertTrue(actual.containsAll(expected)
                & expected.containsAll(actual));
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        noteService.delete(NOTE_FIRST);
        noteService.getById(NOTE_FIRST.getId());
    }

    @Test
    public void deleteAll() throws Exception {
        List<Note> notesBeforeDeleting = noteService.getAll();
        noteService.deleteAll();
        assertTrue(noteService.getAll().size() == 0 && notesBeforeDeleting != null);
    }

    @Test
    public void getTags() {
        Set<Tag> tagsFromDb = noteService.getById(NOTE_FIRST_ID).getTags();
        assertTrue(tagsFromDb.contains(TAG_FIRST_GET_TASKS) && tagsFromDb.contains(TAG_LAST_GET_TASKS));
    }

    @Test
    public void addTagToNoteById() {
        noteService.addTagToNoteById(NOTE_FIRST_ID, TAG_ID);
        Note noteFromDb = noteService.getById(NOTE_FIRST_ID);
        assertTrue(noteFromDb.getTags().contains(tagService.getById(TAG_ID)));
    }

    @Test
    public void deleteTagToNoteById() {
        noteService.addTagToNoteById(NOTE_FIRST_ID, TAG_ID);
        noteService.deleteTagToNoteById(NOTE_FIRST_ID, TAG_ID);
        Note noteTagDeleted = noteService.getById(NOTE_FIRST_ID);
        assertTrue(!noteTagDeleted.getTags().contains(tagService.getById(TAG_ID)));
    }

    @Test
    public void getAllNotesByTag() {
        noteService.addTagToNoteById(NOTE_FIRST_ID, TAG_ID);
        noteService.addTagToNoteById(NOTE_LAST_ID, TAG_ID);
        Set<Note> allNotesByTag = noteService.getAllNotesByTag(TAG_ID);
        assertTrue(allNotesByTag.contains(noteService.getById(NOTE_FIRST_ID))
                && allNotesByTag.contains(noteService.getById(NOTE_LAST_ID)));
    }

    @Test
    public void getByNotepadId() {
        Set<Note> allNotesByNotepadId = noteService.getByNotepadId(NOTEPAD_FIRST_ID);
        System.out.println(allNotesByNotepadId);
        assertTrue(allNotesByNotepadId.stream().allMatch(note -> note.getNotepad()
                .getId().equals(NOTEPAD_FIRST_ID)) && allNotesByNotepadId.size() > 0);
    }

    @Test
    public void getByUserId() {
        Set<Note> allNotesByUser = noteService.getNotesByUserId(TAG_ID);
        assertTrue(allNotesByUser.stream().allMatch(note -> note.getNotepad().getUser().getId()
                .equals(TAG_ID)) && allNotesByUser.size() > 0);
    }
}