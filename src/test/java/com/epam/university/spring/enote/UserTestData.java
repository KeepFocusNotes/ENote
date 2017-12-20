package com.epam.university.spring.enote;

import com.epam.university.spring.enote.model.User;

import com.epam.university.spring.enote.util.DateConverter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.university.spring.enote.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static DateConverter dateConverter = new DateConverter();
    public static final int USERS_INITIALIZED = 500;
    public static final int USER_FIRST_ID = START_SEQ;
    public static final int USER_LAST_ID = USERS_INITIALIZED;
    public static final User USER_FIRST = new User(USER_FIRST_ID,
            "UserFirstMail@userdata.com", "UserFirstPassword", null);
    public static final User USER_LAST = new User(USER_LAST_ID, "UserSecondMail@userdata.com",
            "UserSecondPassword", null);
    public static final User USER_TO_CREATE = new User(null,
            "userToCreateFirst@gmail.com", "passwordUserToCreateFirst", dateConverter.convertToDatabaseColumn(LocalDate
        .of(2000, 1, 1)));
    public static final List<User> LIST_USERS_TO_CREATE = new ArrayList<>();

    public UserTestData() {
        LIST_USERS_TO_CREATE.add(USER_TO_CREATE);
        LIST_USERS_TO_CREATE.add(new User(null, "userToCreateSecond@gmail.com",
                "passwordUserToCreateSecond", dateConverter.convertToDatabaseColumn(LocalDate.of(
                2000, 1, 1))));
    }

/*
    //without dependency on equals and hashcode
    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected,
                "birthDate", "registrationDate");
    }

    //syntax sugar for the cases, when we've got just several items to pass, so don't have to
    //init array or iterable
    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registrationDate")
                .isEqualTo(expected);
    }
*/
}