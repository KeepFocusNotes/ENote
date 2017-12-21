package com.epam.university.spring.enote.services.genericservices;

import com.epam.university.spring.enote.model.genericmodels.Note;

/**
 * Interface for declaring special (not general CRUD's support operations of <code>Note</code>
 * class); should be used for interactions with Spring.
 */
public interface NoteService extends GenericService<Note> {
}
