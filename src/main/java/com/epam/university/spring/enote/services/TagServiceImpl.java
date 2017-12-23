package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.Tag;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.util.ServiceValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl extends GenericServiceImpl<Tag> implements TagService {

    @Autowired
    UserService userService;

    @Autowired
    NoteService noteService;

    @Autowired
    public TagServiceImpl(
           /*   @Qualifier("jdbcTagRepositoryImpl")
                    GenericDao<Tag> jdbcTagRepositoryImpl) {
        super(jdbcTagRepositoryImpl);*/
           @Qualifier("jpaTagRepositoryImpl")
                   GenericDao<Tag> jpaTagRepositoryImpl) {
        super(jpaTagRepositoryImpl);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Set<Tag> getTagsByUserId(Integer userId) {
        return new HashSet<>(noteService.getNotesByUserId(ServiceValidatorUtil
                .validateNotFoundWithId(userService.getById(userId), userId).getId()).stream()
                .flatMap(note -> note.getTags().stream()).collect(Collectors.toList()));
    }
}