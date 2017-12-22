package com.epam.university.spring.enote.services;

import static com.epam.university.spring.enote.TagTestData.LIST_TAGS_TO_CREATE;
import static com.epam.university.spring.enote.TagTestData.TAGS_INITIALIZED;
import static com.epam.university.spring.enote.TagTestData.TAG_FIRST;
import static com.epam.university.spring.enote.TagTestData.TAG_FIRST_ID;
import static com.epam.university.spring.enote.TagTestData.TAG_LAST;
import static com.epam.university.spring.enote.TagTestData.TAG_LAST_ID;
import static com.epam.university.spring.enote.TagTestData.TAG_TO_CREATE;
import static com.epam.university.spring.enote.UserTestData.USER_57_ID;
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
import java.util.stream.Collectors;

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
public class TagServiceTest {
    private static Set<Tag> NOTES_BY_USER_ID_TAGS;

    @Autowired
    private TagService tagService;

    @Autowired
    UserService userService;

    @Autowired
    NoteService noteService;

    @Before
    public void setUp() throws Exception {
        Set<Note> notesByUserId = noteService.getNotesByUserId(USER_57_ID);
        NOTES_BY_USER_ID_TAGS = new HashSet<>(notesByUserId.stream().flatMap(note -> {
            note.getTags().add(TAG_FIRST);
            note.getTags().add(TAG_LAST);
            noteService.update(note);
            return note.getTags().stream();
        }).collect(Collectors.toList()));
    }

    @Test
    public void getByIdFirstTag() {
        assertEquals(tagService.getById(TAG_FIRST_ID), TAG_FIRST);
        //assertMatch(tagService.getById(TAG_FIRST_ID), TAG_FIRST);
    }

    @Test
    public void getByIdLastTag() {
        assertEquals(tagService.getById(TAG_LAST_ID), TAG_LAST);
        //assertMatch(actual,TAG_FIRST);
    }

    @Test
    public void getAll() {
        List<Tag> tagsAll = tagService.getAll();
        tagsAll.sort(Comparator.comparing(AbstractBaseEntity::getId));
        int counter = 1;
        for (Tag tag : tagsAll) {
            if (counter != tag.getId() | counter > TAGS_INITIALIZED) {
                throw new NotFoundException("Entity with needed id not found.");
            }
            counter++;
        }
        assertTrue(TAGS_INITIALIZED == tagsAll.size());
    }

    @Test
    public void create() {
        TAG_TO_CREATE.setId(tagService.create(TAG_TO_CREATE).getId());
        assertEquals(tagService.getById(TAG_TO_CREATE.getId()), TAG_TO_CREATE);
    }

    @Test
    public void update() {
        Tag tagToUpdate = new Tag(TAG_FIRST);
        tagToUpdate.setTitle("UpdatedTitle");
        tagService.update(tagToUpdate);
        assertEquals(tagService.getById(tagToUpdate.getId()), tagToUpdate);
    }

    @Test
    public void createFromList() {
        List<Tag> expected = tagService.getAll();
        tagService.createFromList(LIST_TAGS_TO_CREATE);
        expected.addAll(LIST_TAGS_TO_CREATE);
        List<Tag> actual = tagService.getAll();
        assertTrue(actual.containsAll(expected)
                & expected.containsAll(actual));
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        tagService.delete(TAG_FIRST);
        tagService.getById(TAG_FIRST.getId());
    }

    @Test
    public void deleteAll() {
        tagService.deleteAll();
        assertTrue(tagService.getAll().size() == 0);
    }

    @Test
    public void getTagByUserId() {
        Set<Tag> tagsByUserId = tagService.getTagsByUserId(USER_57_ID);
        assertTrue(tagsByUserId.containsAll(NOTES_BY_USER_ID_TAGS)
                && NOTES_BY_USER_ID_TAGS.containsAll(tagsByUserId));
    }
}