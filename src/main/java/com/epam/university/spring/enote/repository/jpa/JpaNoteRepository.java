package com.epam.university.spring.enote.repository.jpa;

import com.epam.university.spring.enote.model.Note;
import com.epam.university.spring.enote.repository.GenericDao;

import java.util.Set;

public interface JpaNoteRepository extends GenericDao<Note> {
    Set<Note> getByNotepadId(Integer notepadId);
}
