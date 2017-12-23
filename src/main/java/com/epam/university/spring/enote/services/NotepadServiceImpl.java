package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.repository.jpa.JpaNotepadRepository;
import com.epam.university.spring.enote.util.ServiceValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class NotepadServiceImpl extends GenericServiceImpl<Notepad> implements NotepadService {

    @Autowired
    UserService userService;

    @Autowired
    JpaNotepadRepository jpaNotepadRepository;

    @Autowired
    public NotepadServiceImpl(
             /* @Qualifier("jdbcNotepadRepositoryImpl")
                    GenericDao<Notepad> jdbcNotepadRepositoryImpl) {
        super(jdbcNotepadRepositoryImpl);*/
             @Qualifier("jpaNotepadRepositoryImpl")
                     GenericDao<Notepad> jpaNotepadRepositoryImpl) {
        super(jpaNotepadRepositoryImpl);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Set<Notepad> getByUserId(Integer userId) {
        ServiceValidatorUtil.validateNotFoundWithId(userService.getById(userId), userId);
        return jpaNotepadRepository.getByUserId(userId);
    }
}