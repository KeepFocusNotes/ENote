package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.repository.jpa.JpaUserRepository;
import com.epam.university.spring.enote.util.ServiceValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    public UserServiceImpl(
           /* @Qualifier("jdbcUserRepositoryImpl")
                    GenericDao<User> jdbcUserRepositoryImpl) {
        super(jdbcUserRepositoryImpl);*/
           @Qualifier("jpaUserRepositoryImpl")
                   GenericDao<User> jpaUserRepositoryImpl) {
        super(jpaUserRepositoryImpl);
    }

    @Cacheable("users")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<User> getAll() {
        return jpaUserRepository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User create(User entity) {
        Assert.notNull(entity, "Object to create of class " + entity.getClass() +
                " must not be null");
        return jpaUserRepository.save(entity);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(User entity) {
        Assert.notNull(entity, "Object to update of class " + entity.getClass() +
                " must not be null");
        ServiceValidatorUtil.validateNotFoundWithId(jpaUserRepository.save(entity), entity.getId());
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(User entity) {
        ServiceValidatorUtil.validateNotFoundWithId(jpaUserRepository.delete(entity),
                entity.getId());
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteAll() {
        jpaUserRepository.deleteAll();
    }

}