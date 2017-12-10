package com.epam.university.spring.enote;

import com.epam.university.spring.enote.model.User;

import java.time.LocalDate;

import static com.epam.university.spring.enote.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final int USER_FIRST_ID = START_SEQ;
    public static final int USER_SECOND_ID = START_SEQ + 1;
    public static final User USER_FIRST = new User(USER_FIRST_ID, "UserFirstMail@userdata.com",
            "UserFirstPasswordUserData​", LocalDate.of(1980, 1,
            1));
    public static final User USER_SECOND = new User(USER_SECOND_ID, "UserSecondMail@userdata.com",
            "UserSecondPasswordUserData", LocalDate.of(1980, 1,
            2));

   /*
    //without dependency on equals and hashcode
    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registrationDate");
    }

    //syntax sugar for the cases, when we've got just several items to pass, so don't have to
    init array or iterable
    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registrationDate")
                .isEqualTo(expected);
    }
        */
}