package com.epam.university.spring.enote;

import com.epam.university.spring.enote.model.genericmodels.Note;
import com.epam.university.spring.enote.model.genericmodels.Tag;

import java.util.ArrayList;
import java.util.List;

import static com.epam.university.spring.enote.model.genericmodels.AbstractBaseEntity.START_SEQ;

public class TagTestData {

    public static final int TAGS_INITIALIZED = 500;
    public static final int TAG_FIRST_ID = START_SEQ;
    public static final int TAG_LAST_ID = TAGS_INITIALIZED;
    public static final Tag TAG_FIRST = new Tag(TAG_FIRST_ID,
            "TagFirst", new Note(43, "noteTitle43", "noteDescription43"
            , null));
    public static final Tag TAG_LAST = new Tag(TAG_LAST_ID, "TagLast", new Note(440
            , "noteTitle333", "noteDescription333", null));
    public static final Tag TAG_TO_CREATE = new Tag(null, "TagToCreateFirst"
            , new Note(1, "NoteFirst", "NoteFirstDescription", null));
    public static final List<Tag> LIST_TAGS_TO_CREATE = new ArrayList<>();

    public TagTestData() {
        LIST_TAGS_TO_CREATE.add(TAG_TO_CREATE);
        LIST_TAGS_TO_CREATE.add(new Tag(null, "TagToCreateSecond",
                new Note(2, "noteTitle2", "noteDescription2",null)));
    }
}