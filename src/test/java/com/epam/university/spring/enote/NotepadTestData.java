package com.epam.university.spring.enote;

import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.epam.university.spring.enote.model.AbstractBaseEntity.START_SEQ;

public class NotepadTestData {

    public static final int NOTEPADS_INITIALIZED = 500;
    public static final int NOTEPAD_FIRST_ID = START_SEQ;
    public static final int NOTEPAD_LAST_ID = NOTEPADS_INITIALIZED;
    public static final Notepad NOTEPAD_FIRST = new Notepad(NOTEPAD_FIRST_ID,
            "NotepadFirst", new User(43, "IzotKirilovichStrekalov@yandex.com",
            "Kirilovich36269915", null));
    public static final Notepad NOTEPAD_LAST = new Notepad(NOTEPAD_LAST_ID,
            "NotepadLast", new User(333, "YuriyAdamovichPrushinskiy@gmail.com",
            "Adamovich25901779", null));
    public static final Notepad NOTEPAD_TO_CREATE = new Notepad(null,
            "NotepadToCreate", new User(333, "YuriyAdamovichPrushinskiy@gmail.com",
            "Adamovich25901779", null));
    public static final List<Notepad> LIST_NOTEPADS_TO_CREATE = new ArrayList<>();

    public NotepadTestData() {
        LIST_NOTEPADS_TO_CREATE.add(NOTEPAD_TO_CREATE);
        LIST_NOTEPADS_TO_CREATE.add(new Notepad(null, "NotepadToCreateSecond",new User(333, "YuriyAdamovichPrushinskiy@gmail.com",
                "Adamovich25901779", null)));
    }
}