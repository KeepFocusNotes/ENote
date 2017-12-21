package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.Note;
import com.epam.university.spring.enote.model.Tag;

import java.util.Set;

/**
 * Interface for declaring special (not general CRUD's support operations of <code>Note</code>
 * class); should be used for interactions with Spring.
 */
public interface NoteService extends GenericService<Note> {

    Set<Tag> getTags(Integer noteId);

    void addTagToNoteById(Integer noteId, Integer tagId);

    void deleteTagToNoteById(Integer noteId, Integer tagId);

    Set<Note> getAllNotesByTag(Integer tagId);
}
