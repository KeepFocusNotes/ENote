package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.Tag;
import com.epam.university.spring.enote.repository.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends GenericServiceImpl<Tag> implements TagService {

   // private final JdbcTagRepositoryImpl jdbcTagRepositoryImpl;

    @Autowired
    public TagServiceImpl(
            @Qualifier("jdbcTagRepositoryImpl")
                    GenericDao<Tag> jdbcTagRepositoryImpl) {
        super(jdbcTagRepositoryImpl);
     //   this.jdbcTagRepositoryImpl = (JdbcTagRepositoryImpl) jdbcTagRepositoryImpl;
    }
}