package com.epam.university.spring.enote.repository.mock;

import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends JdbcGenericServiceImpl<User> implements UserService {

   // private final JdbcUserRepositoryImpl jdbcUserRepositoryImpl;

    @Autowired
    public UserServiceImpl(
            @Qualifier("jdbcUserRepositoryImpl")
                    GenericDao<User> jdbcUserRepositoryImpl) {
        super(jdbcUserRepositoryImpl);
    //    this.jdbcUserRepositoryImpl = (JdbcUserRepositoryImpl) jdbcUserRepositoryImpl;
    }
}