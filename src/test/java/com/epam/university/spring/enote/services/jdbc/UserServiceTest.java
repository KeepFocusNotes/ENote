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
                2000, 01, 01));
        User created = userService.create(newUser);
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
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void deleteAll() throws Exception {
    }

}