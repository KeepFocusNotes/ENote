package com.epam.university.spring.enote;

import com.epam.university.spring.enote.model.Note;

import java.util.ArrayList;
import java.util.List;

import static com.epam.university.spring.enote.model.AbstractBaseEntity.START_SEQ;

public class NoteTestData {

    public static final int NOTES_INITIALIZED = 500;
    public static final int NOTE_FIRST_ID = START_SEQ;
    public static final int NOTE_LAST_ID = NOTES_INITIALIZED;
    public static final Note NOTE_FIRST = new Note(NOTE_FIRST_ID,
            "NoteFirst","NoteFirstDescription");
    public static final Note NOTE_LAST = new Note(NOTE_LAST_ID,
            "NoteLast", "NoteLastDescription");
    public static final Note NOTE_TO_CREATE = new Note(null,
            "NoteToCreateFirst","NoteToCreateFirstDescription");
    public static final List<Note> LIST_NOTES_TO_CREATE = new ArrayList<>();

    public NoteTestData() {
        LIST_NOTES_TO_CREATE.add(NOTE_TO_CREATE);
        LIST_NOTES_TO_CREATE.add(new Note(null, "NoteToCreateSecond",
                "NoteToCreateSecondDescription"));
    }
}