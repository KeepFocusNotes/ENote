package com.epam.university.spring.enote.services.jdbc;

import com.epam.university.spring.enote.model.Tag;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.repository.jdbc.JdbcTagRepositoryImpl;
import com.epam.university.spring.enote.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends JdbcGenericServiceImpl<Tag> implements TagService {

   // private final JdbcTagRepositoryImpl jdbcTagRepositoryImpl;

    @Autowired
    public TagServiceImpl(
            @Qualifier("jdbcTagRepositoryImpl")
                    GenericDao<Tag> jdbcTagRepositoryImpl) {
        super(jdbcTagRepositoryImpl);
     //   this.jdbcTagRepositoryImpl = (JdbcTagRepositoryImpl) jdbcTagRepositoryImpl;
    }
}