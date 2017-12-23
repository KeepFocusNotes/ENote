package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.util.ServiceValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotepadServiceImpl extends GenericServiceImpl<Notepad> implements NotepadService {

    @Autowired
    UserService userService;

    @Autowired
    public NotepadServiceImpl(
             /* @Qualifier("jdbcNotepadRepositoryImpl")
                    GenericDao<Notepad> jdbcNotepadRepositoryImpl) {
        super(jdbcNotepadRepositoryImpl);*/
             @Qualifier("jpaNotepadRepositoryImpl")
                     GenericDao<Notepad> jpaNotepadRepositoryImpl) {
        super(jpaNotepadRepositoryImpl);
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    @Override
    public List<Notepad> getByUserId(Integer userId) {
        Assert.isTrue(userId > 0, "User's id " + userId +
                " must be positive.");
        ServiceValidatorUtil.validateNotFoundWithId(userService.getById(userId), userId);
        List<Notepad> notepads = getAll().stream().filter(notepad ->
                notepad.getUser().getId().equals(userId)).collect(Collectors.toList());
        return notepads;
    }
}