package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.AbstractBaseEntity;
import com.epam.university.spring.enote.model.Tag;
import com.epam.university.spring.enote.services.TagService;
import com.epam.university.spring.enote.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

import static com.epam.university.spring.enote.TagTestData.LIST_TAGS_TO_CREATE;
import static com.epam.university.spring.enote.TagTestData.TAGS_INITIALIZED;
import static com.epam.university.spring.enote.TagTestData.TAG_FIRST;
import static com.epam.university.spring.enote.TagTestData.TAG_FIRST_ID;
import static com.epam.university.spring.enote.TagTestData.TAG_LAST;
import static com.epam.university.spring.enote.TagTestData.TAG_LAST_ID;
import static com.epam.university.spring.enote.TagTestData.TAG_TO_CREATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class TagServiceTest {

    @Autowired
    private TagService tagService;

    @Test
    public void getByIdFirstTag() throws Exception {
                assertEquals(tagService.getById(TAG_FIRST_ID), TAG_FIRST);
        //assertMatch(tagService.getById(TAG_FIRST_ID), TAG_FIRST);
    }

    @Test
    public void getByIdLastTag() throws Exception {
                assertEquals(tagService.getById(TAG_LAST_ID), TAG_LAST);
        //assertMatch(actual,TAG_FIRST);
    }

    @Test
    public void getAll() throws Exception {
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
    public void create() throws Exception {
        TAG_TO_CREATE.setId(tagService.create(TAG_TO_CREATE).getId());
        assertEquals(tagService.getById(TAG_TO_CREATE.getId()), TAG_TO_CREATE);
    }

    @Test
    public void update() throws Exception {
        Tag tagToUpdate = new Tag(TAG_FIRST);
        tagToUpdate.setTitle("UpdatedTitle");
        tagService.update(tagToUpdate);
        assertEquals(tagService.getById(tagToUpdate.getId()), tagToUpdate);
    }

    @Test
    public void createFromList() throws Exception {
        List<Tag> expected = tagService.getAll();
        tagService.createFromList(LIST_TAGS_TO_CREATE);
        expected.addAll(LIST_TAGS_TO_CREATE);
        List<Tag> actual = tagService.getAll();
        assertTrue(actual.containsAll(expected)
                & expected.containsAll(actual));
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        tagService.delete(TAG_FIRST);
        tagService.getById(TAG_FIRST.getId());
    }

    @Test
    public void deleteAll() throws Exception {
        tagService.deleteAll();
        assertTrue(tagService.getAll().size() == 0);
    }
}