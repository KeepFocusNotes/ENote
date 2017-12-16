package com.epam.university.spring.enote.repository.jpa;

import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.repository.GenericDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Getter
@Setter
@Repository
@Transactional
public class JpaUserRepositoryImpl extends JpaAbstractGenericDao<User> implements GenericDao<User> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
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
    @Transactional
    public boolean deleteAll() {
        return entityManager.createNamedQuery(User.DELETE_ALL)
                .executeUpdate() != 0;
    }
}