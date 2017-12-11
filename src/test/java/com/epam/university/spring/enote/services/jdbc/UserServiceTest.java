package com.epam.university.spring.enote.services.jdbc;

import com.epam.university.spring.enote.model.AbstractBaseEntity;
import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.services.UserService;
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

import static com.epam.university.spring.enote.UserTestData.LIST_USERS_TO_CREATE;
import static com.epam.university.spring.enote.UserTestData.USERS_INITIALIZED;
import static com.epam.university.spring.enote.UserTestData.USER_FIRST;
import static com.epam.university.spring.enote.UserTestData.USER_FIRST_ID;
import static com.epam.university.spring.enote.UserTestData.USER_LAST;
import static com.epam.university.spring.enote.UserTestData.USER_LAST_ID;
import static com.epam.university.spring.enote.UserTestData.USER_TO_CREATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getByIdFirstUser() throws Exception {
        System.out.println("1");
        assertEquals(userService.getById(USER_FIRST_ID), USER_FIRST);
        //assertMatch(userService.getById(USER_FIRST_ID), USER_FIRST);
    }

    @Test
    public void getByIdLastUser() throws Exception {
        System.out.println("2");
        assertEquals(userService.getById(USER_LAST_ID), USER_LAST);
        //assertMatch(actual,USER_FIRST);
    }

    @Test
    public void getAll() throws Exception {
        System.out.println("3");
        List<User> usersAll = userService.getAll();
        System.out.println(usersAll.size());
        usersAll.sort(Comparator.comparing(AbstractBaseEntity::getId));
        int counter = 1;
        for (User user : usersAll) {
            if (counter != user.getId() | counter > USERS_INITIALIZED) {
                System.out.println(user);
                throw new NotFoundException("Entity with needed id not found.");
            }
            counter++;
        }
        assertTrue(USERS_INITIALIZED == usersAll.size());
    }

    @Test
    public void create() throws Exception {
        System.out.println("4");
        USER_TO_CREATE.setId(userService.create(USER_TO_CREATE).getId());
        assertEquals(userService.getById(USER_TO_CREATE.getId()), USER_TO_CREATE);
    }

    @Test
    public void update() throws Exception {
        System.out.println("5");
        User userToUpdate = new User(USER_FIRST);
        userToUpdate.setPassword("UpdatedPassword");
        userService.update(userToUpdate);
        assertEquals(userService.getById(userToUpdate.getId()), userToUpdate);
    }

    @Test
    public void createFromList() throws Exception {
        System.out.println("6");
        List<User> expected = userService.getAll();
        userService.createFromList(LIST_USERS_TO_CREATE);
        expected.addAll(LIST_USERS_TO_CREATE);
        List<User> actual = userService.getAll();
        assertTrue(actual.containsAll(expected)
                & expected.containsAll(actual));
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        System.out.println("7");
        userService.delete(USER_FIRST);
        userService.getById(USER_FIRST.getId());
    }

    @Test
    public void deleteAll() throws Exception {
        System.out.println("8");
        userService.deleteAll();
        assertTrue(userService.getAll().size() == 0);
    }
}