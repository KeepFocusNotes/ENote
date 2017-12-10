package com.epam.university.spring.enote.services.jdbc;

import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.services.UserService;
import com.epam.university.spring.enote.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.university.spring.enote.UserTestData.USER_FIRST;
import static com.epam.university.spring.enote.UserTestData.USER_FIRST_ID;
import static com.epam.university.spring.enote.UserTestData.USER_SECOND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
//temporary disabled
//@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {
    private List<User> usersToInitDb;
    List<User> usersToCreate;

    @Autowired
    private UserService userService;

    public void populateDb() {
        userService.deleteAll();
        User userFirstCopy = new User(USER_FIRST);
        userFirstCopy.setId(null);
        User userSecondCopy = new User(USER_SECOND);
        userSecondCopy.setId(null);
        userService.create(userFirstCopy);
        userService.create(userSecondCopy);
        usersToInitDb = new ArrayList<>();
        usersToInitDb.add(userFirstCopy);
        usersToInitDb.add(userSecondCopy);
        usersToCreate = new ArrayList<>();
        usersToCreate.add(new User(null, "newFirst@gmail.com", "newPass", LocalDate.of(
                2000, 1, 1)));
        usersToCreate.add(new User(null, "newSecond@gmail.com", "newPass", LocalDate.of(
                2000, 1, 2)));
    }

    @Test
    public void getById() throws Exception {
        User userToGet = new User(null, "userToGet@gmail.com",
                "thePass", LocalDate.of(2000, 1, 1));
        Integer id = userService.create(userToGet).getId();
        User actual = userService.getById(id);
        assertEquals(userToGet, actual);
        //assertMatch(actual,USER_FIRST);
    }

    @Test
    public void getAll() throws Exception {
        populateDb();
        List<User> usersAll = userService.getAll();
        assertTrue(2 == usersAll.size());
    }

    @Test
    public void create() throws Exception {
        populateDb();
        assertEquals(userService.getById(USER_FIRST_ID), USER_FIRST);
    }

    @Test
    public void update() throws Exception {
        User userToUpdate = new User(null, "userToUpdate@gmail.com",
                "newPass", LocalDate.of(2000, 1, 1));
        Integer id = userService.create(userToUpdate).getId();
        userToUpdate.setPassword("UpdatedPassword");
        userService.update(userToUpdate);
        assertEquals(userService.getById(id), userToUpdate);
    }

    @Test
    public void createFromList() throws Exception {

        populateDb();
        List<User> usersAllBeforeCreation = userService.getAll();
        List<User> createdFromList = userService.createFromList(usersToCreate);
        usersAllBeforeCreation.addAll(createdFromList);
        ArrayList usersAllBeforeCreationPlusNew = new ArrayList<>(usersAllBeforeCreation);
        List<User> usersAllAfterCreation = userService.getAll();
        assertTrue(usersAllAfterCreation.containsAll(usersAllBeforeCreationPlusNew)
                & usersAllBeforeCreationPlusNew.containsAll(usersAllAfterCreation));
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        populateDb();
        userService.delete(USER_FIRST);
        userService.getById(USER_FIRST.getId());
    }

    @Test
    public void deleteAll() throws Exception {
        populateDb();
        userService.deleteAll();
        assertTrue(userService.getAll().size() == 0);
    }
}