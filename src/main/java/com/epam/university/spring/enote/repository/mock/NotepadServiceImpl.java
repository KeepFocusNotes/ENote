package com.epam.university.spring.enote.repository.mock;

import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.services.NotepadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotepadServiceImpl extends JdbcGenericServiceImpl<Notepad> implements NotepadService {

   // private final JdbcNotepadRepositoryImpl jdbcNotepadRepositoryImpl;

    @Autowired
    public NotepadServiceImpl(
            @Qualifier("jdbcNotepadRepositoryImpl")
                    GenericDao<Notepad> jdbcNotepadRepositoryImpl) {
        super(jdbcNotepadRepositoryImpl);
      //  this.jdbcNotepadRepositoryImpl = (JdbcNotepadRepositoryImpl) jdbcNotepadRepositoryImpl;
    }
}