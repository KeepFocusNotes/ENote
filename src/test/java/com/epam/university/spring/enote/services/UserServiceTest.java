package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.config.AppConfig;
import com.epam.university.spring.enote.model.AbstractBaseEntity;
import com.epam.university.spring.enote.model.User;
//import com.epam.university.spring.enote.util.JpaUtil;
import com.epam.university.spring.enote.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

import org.springframework.test.context.web.WebAppConfiguration;

import static com.epam.university.spring.enote.UserTestData.LIST_USERS_TO_CREATE;
import static com.epam.university.spring.enote.UserTestData.USERS_INITIALIZED;
import static com.epam.university.spring.enote.UserTestData.USER_FIRST;
import static com.epam.university.spring.enote.UserTestData.USER_FIRST_ID;
import static com.epam.university.spring.enote.UserTestData.USER_LAST;
import static com.epam.university.spring.enote.UserTestData.USER_LAST_ID;
import static com.epam.university.spring.enote.UserTestData.USER_TO_CREATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    /*@Autowired
    protected JpaUtil jpaUtil;

    @Before
    public void setUp() {
        jpaUtil.clear2ndLevelHibernateCache();
    }*/

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void getByIdFirstUser() throws Exception {
        assertEquals(userService.getById(USER_FIRST_ID), USER_FIRST);
        //assertMatch(userService.getById(USER_FIRST_ID), USER_FIRST);
    }

    @Test
    public void getByIdLastUser() throws Exception {
        assertEquals(userService.getById(USER_LAST_ID), USER_LAST);
        //assertMatch(actual,USER_FIRST);
    }

    @Test
    public void getAll() throws Exception {
        List<User> usersAll = userService.getAll();
        usersAll.sort(Comparator.comparing(AbstractBaseEntity::getId));
        int counter = 1;
        for (User user : usersAll) {
            if (counter != user.getId() | counter > USERS_INITIALIZED) {
                throw new NotFoundException("Entity with needed id not found.");
            }
            counter++;
        }
        assertTrue(USERS_INITIALIZED == usersAll.size());
    }

    @Test
    public void create() throws Exception {
        USER_TO_CREATE.setId(userService.create(USER_TO_CREATE).getId());
        assertEquals(userService.getById(USER_TO_CREATE.getId()), USER_TO_CREATE);
    }

    @Test
    public void update() throws Exception {
        User userToUpdate = new User(USER_FIRST);
        userToUpdate.setPassword("UpdatedPassword");
        userService.update(userToUpdate);
        assertEquals(userService.getById(userToUpdate.getId()), userToUpdate);
    }

    @Test
    public void createFromList() throws Exception {
        List<User> expected = userService.getAll();
        userService.createFromList(LIST_USERS_TO_CREATE);
        expected.addAll(LIST_USERS_TO_CREATE);
        List<User> actual = userService.getAll();
        assertTrue(actual.containsAll(expected)
                & expected.containsAll(actual));
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        userService.delete(USER_FIRST);
        userService.getById(USER_FIRST.getId());
    }

    @Test
    public void deleteAll() throws Exception {
        userService.deleteAll();
        assertTrue(userService.getAll().size() == 0);
    }
}