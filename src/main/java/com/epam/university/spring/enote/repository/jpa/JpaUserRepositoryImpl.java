package com.epam.university.spring.enote.repository.jpa;

import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.repository.GenericDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserRepositoryImpl extends JpaAbstractGenericDao<User> implements GenericDao<User> {

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
    public boolean deleteAll() {
        return entityManager.createNamedQuery(User.DELETE_ALL)
                .executeUpdate() != 0;
    }
}