package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.Notepad;

import java.util.Set;

/**
 * Interface for declaring special (not general CRUD's support operations of <code>Notepad</code>
 * class); should be used for interactions with Spring.
 */
public interface NotepadService extends GenericService<Notepad> {
    Set<Notepad> getByUserId(Integer userId);
}
