package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.Note;
import com.epam.university.spring.enote.repository.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl extends GenericServiceImpl<Note> implements NoteService {

    //private final JdbcNoteRepositoryImpl jdbcNoteRepositoryImpl;

    @Autowired
    public NoteServiceImpl(
            @Qualifier("jdbcNoteRepositoryImpl")
                    GenericDao<Note> jdbcNoteRepositoryImpl) {
        super(jdbcNoteRepositoryImpl);
      //  this.jdbcNoteRepositoryImpl = (JdbcNoteRepositoryImpl) jdbcNoteRepositoryImpl;
    }
}