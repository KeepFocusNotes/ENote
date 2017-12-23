package com.epam.university.spring.enote.repository.jpa;

import com.epam.university.spring.enote.model.Tag;
import com.epam.university.spring.enote.repository.GenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaTagRepositoryImpl extends JpaAbstractGenericDao<Tag> implements GenericDao<Tag> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean delete(Tag entity) {
        return entityManager.createNamedQuery(Tag.DELETE)
                .setParameter("id", entity.getId())
                .executeUpdate() != 0;
    }

    @Override
    public List<Tag> getAll() {
        return entityManager.createNamedQuery(Tag.ALL_SORTED, Tag.class).getResultList();
    }

    @Override
    public boolean deleteAll() {
        return entityManager.createNamedQuery(Tag.DELETE_ALL)
                .executeUpdate() != 0;
    }
}