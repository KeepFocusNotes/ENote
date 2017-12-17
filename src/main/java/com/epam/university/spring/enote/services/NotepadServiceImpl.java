package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.repository.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotepadServiceImpl extends GenericServiceImpl<Notepad> implements NotepadService {

    @Autowired
    public NotepadServiceImpl(
             /* @Qualifier("jdbcNotepadRepositoryImpl")
                    GenericDao<Notepad> jdbcNotepadRepositoryImpl) {
        super(jdbcNotepadRepositoryImpl);*/
          @Qualifier("jpaNotepadRepositoryImpl")
                    GenericDao<Notepad> jpaNotepadRepositoryImpl) {
        super(jpaNotepadRepositoryImpl);
    }
}