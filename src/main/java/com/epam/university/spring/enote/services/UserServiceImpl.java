package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.repository.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {

    @Autowired
    public UserServiceImpl(
            @Qualifier("jdbcUserRepositoryImpl")
                    GenericDao<User> jdbcUserRepositoryImpl) {
        super(jdbcUserRepositoryImpl);
            /*@Qualifier("jpaUserRepositoryImpl")
                    GenericDao<User> jpaUserRepositoryImpl) {
        super(jpaUserRepositoryImpl);*/
    }
}