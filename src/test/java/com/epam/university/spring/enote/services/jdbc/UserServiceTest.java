package com.epam.university.spring.enote.services.jdbc;

import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.university.spring.enote.UserTestData.USER_FIRST;
import static com.epam.university.spring.enote.UserTestData.USER_FIRST_ID;
import static com.epam.university.spring.enote.UserTestData.assertMatch;
import static org.junit.Assert.assertTrue;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getById() throws Exception {
        User actual = userService.getById(USER_FIRST_ID);
        assertMatch(USER_FIRST, actual);
    }

    @Test
    public void getAll() throws Exception {
        List<User> usersAll = userService.getAll();
        assertTrue(16403 == usersAll.size());
    }

    @Test
    public void create() throws Exception {
        List<User> usersAllBeforeCreation = userService.getAll();
        User newUser = new User(null, "new@gmail.com", "newPass", LocalDate.of(
                2000, 1, 1));
        User created = userService.create(newUser);
        //or just return the user and check equal
        newUser.setId(created.getId());
        usersAllBeforeCreation.add(newUser);
        ArrayList usersAllBeforeCreationPlusNew = new ArrayList<>(usersAllBeforeCreation);
        assertMatch(userService.getAll(), usersAllBeforeCreationPlusNew);
    }

    @Test
    public void update() throws Exception {
        User userToUpdate = new User(USER_FIRST);
        userToUpdate.setPassword("UpdatedPassword");
        userService.update(userToUpdate);
        assertMatch(userService.getById(USER_FIRST_ID), userToUpdate);
    }

    @Test
    public void createFromList() throws Exception {
        List<User> usersAllBeforeCreation = userService.getAll();
        List<User> usersToCreate = new ArrayList<>();
        usersToCreate.add(new User(null, "newFirst@gmail.com", "newPass", LocalDate.of(
                2000, 1, 1)));
        usersToCreate.add(new User(null, "newSecond@gmail.com", "newPass", LocalDate.of(
                2000, 1, 2)));
        List<User> createdFromList = userService.createFromList(usersToCreate);
        usersAllBeforeCreation.addAll(createdFromList);
        ArrayList usersAllBeforeCreationPlusNew = new ArrayList<>(usersAllBeforeCreation);
        assertMatch(userService.getAll(), usersAllBeforeCreationPlusNew);
    }

    @Test
    public void delete() throws Exception {
        List<User> usersAllBeforeDeleting = userService.getAll();
        List<User> usersAllBeforeDeletingEcxeptFirst = usersAllBeforeDeleting.subList(1,
                usersAllBeforeDeleting.size());
        userService.delete(USER_FIRST);
        assertMatch(userService.getAll(), usersAllBeforeDeletingEcxeptFirst);
    }

    @Test
    public void deleteAll() throws Exception {
        userService.deleteAll();
        assertTrue(userService.getAll().size() == 0);
    }
}