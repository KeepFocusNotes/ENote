package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.Note;
import com.epam.university.spring.enote.model.Tag;
import com.epam.university.spring.enote.repository.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl extends GenericServiceImpl<Note> implements NoteService {

    @Autowired
    private TagService tagService;

    @Autowired
    public NoteServiceImpl(
            /*  @Qualifier("jdbcNoteRepositoryImpl")
                    GenericDao<Note> jdbcNoteRepositoryImpl) {
        super(jdbcNoteRepositoryImpl);*/
            @Qualifier("jpaNoteRepositoryImpl")
                    GenericDao<Note> jpaNoteRepositoryImpl) {
        super(jpaNoteRepositoryImpl);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Set<Tag> getTags(Integer noteId) {
        Note byId = getById(noteId);
        if (byId != null) {
            return byId.getTags();
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addTagToNoteById(Integer noteId, Integer tagId) {
        Note note = getById(noteId);
        Tag tag = tagService.getById(tagId);
        if (note != null && tag != null) {
            note.getTags().add(tag);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteTagToNoteById(Integer noteId, Integer tagId) {
        Note note = getById(noteId);
        Tag tag = tagService.getById(tagId);
        if (note != null && tag != null) {
            note.getTags().remove(tag);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Set<Note> getAllNotesByTag(Integer tagId) {
        Tag tag = tagService.getById(tagId);
        if (tag != null) {
            return tag.getNotes();
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Set<Note> getNoteByNotepadId(Integer notepadId) {
        return new HashSet<>(getAll().stream().filter(note -> note.getNotepad().getId().equals
                (notepadId)).collect(Collectors.toList()));
    }
}