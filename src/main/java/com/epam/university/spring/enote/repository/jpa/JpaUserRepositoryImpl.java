package com.epam.university.spring.enote.repository.jpa;

import com.epam.university.spring.enote.model.AbstractBaseEntity;
import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.repository.GenericDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Repository
@Transactional
public class JpaUserRepositoryImpl extends JpaAbstractGenericDao<User> implements GenericDao<User> {
    //TODO is setted method in the implementing interface is needed for autowiring of the DAO in
    //services?

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean delete(User entity) {
        return entityManager.createNamedQuery(User.DELETE)
                .setParameter("id", entity.getId())
                .executeUpdate() != 0;
    }

    @Override
    public List<User> getAll() {
        return entityManager.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }

    @Override
    public List<User> saveAll(List<User> entities) {
        return entities.stream().map(this::save).collect(Collectors.toList());
    }

    @Override
    public boolean deleteAll() {
        return entityManager.createNamedQuery(User.DELETE_ALL)
                .executeUpdate() != 0;
    }
}