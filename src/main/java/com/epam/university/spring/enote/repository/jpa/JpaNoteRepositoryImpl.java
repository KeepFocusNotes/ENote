package com.epam.university.spring.enote.repository.jpa;

import com.epam.university.spring.enote.model.Note;
import com.epam.university.spring.enote.repository.GenericDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class JpaNoteRepositoryImpl extends JpaAbstractGenericDao<Note> implements GenericDao<Note> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public boolean delete(Note entity) {
        return entityManager.createNamedQuery(Note.DELETE)
                .setParameter("id", entity.getId())
                .executeUpdate() != 0;
    }

    @Transactional
    @Override
    public List<Note> getAll() {
        return entityManager.createNamedQuery(Note.ALL_SORTED, Note.class).getResultList();
    }

    @Override
    @Transactional
    public boolean deleteAll() {
        return entityManager.createNamedQuery(Note.DELETE_ALL)
                .executeUpdate() != 0;
    }
}