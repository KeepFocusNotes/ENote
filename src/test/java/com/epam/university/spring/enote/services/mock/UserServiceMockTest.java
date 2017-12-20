package com.epam.university.spring.enote.services.mock;

import com.epam.university.spring.enote.UserTestData;
import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.services.GenericService;
import com.epam.university.spring.enote.services.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserServiceMockTest {

    @Mock
    private GenericDao<User> userRepository;
    private GenericService<User> userService;
    private User user;
    private Integer userID = 1234;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository);
        user = new User(userID, "asd@asd.ru", "1234",
            UserTestData.dateConverter.convertToDatabaseColumn(LocalDate.of(1952, 10, 7)));
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(userRepository.get(userID)).thenReturn(user);
        User actual = userService.getById(userID);
        assertEquals(user, actual);
    }

    @Test
    public void getAll() throws Exception {
        List<User> expected = Collections.singletonList(user);
        Mockito.when(userRepository.getAll()).thenReturn(expected);
        List<User> actual = userService.getAll();
        assertEquals(expected, actual);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User actual = userService.create(user);
        assertEquals(user, actual);
    }

    @Test
    public void saveAll() throws Exception {
        List<User> expected = Collections.singletonList(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        List<User> actual = userService.createFromList(expected);
        assertEquals(expected, actual);
    }
}