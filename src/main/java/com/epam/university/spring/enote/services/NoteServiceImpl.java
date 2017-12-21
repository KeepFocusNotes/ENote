package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.Note;
import com.epam.university.spring.enote.model.Tag;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.util.ServiceValidatorUtil;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteServiceImpl extends GenericServiceImpl<Note> implements NoteService {

    @Autowired
    private TagService tagService;

    @Autowired
    private NotepadService notepadService;

    @Autowired
    private UserService userService;

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
        return ServiceValidatorUtil.validateNotFoundWithId(getById(noteId), noteId)
                .getTags();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addTagToNoteById(Integer noteId, Integer tagId) {
        ServiceValidatorUtil.validateNotFoundWithId(getById(noteId), noteId).getTags()
                .add(ServiceValidatorUtil.validateNotFoundWithId(tagService.getById(tagId), tagId));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteTagToNoteById(Integer noteId, Integer tagId) {
        ServiceValidatorUtil.validateNotFoundWithId(getById(noteId), noteId).getTags()
                .remove(ServiceValidatorUtil.validateNotFoundWithId(tagService.getById(tagId), tagId));

    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Set<Note> getAllNotesByTag(Integer tagId) {
        return ServiceValidatorUtil.validateNotFoundWithId(tagService.getById(tagId), tagId)
                .getNotes();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Set<Note> getNotesByNotepadId(Integer notepadId) {
        ServiceValidatorUtil.validateNotFoundWithId(notepadService.getById(notepadId), notepadId);
        return new HashSet<>(getAll().stream().filter(note -> note.getNotepad().getId().equals
                (notepadId)).collect(Collectors.toList()));
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Set<Note> getNotesByUserId(Integer userId) {
        ServiceValidatorUtil.validateNotFoundWithId(userService.getById(userId), userId);
        return new HashSet<>(notepadService.getByUserId(userId).stream().flatMap(notepad ->
                getNotesByNotepadId(notepad.getId()).stream()).collect(Collectors.toList()));
    }
}