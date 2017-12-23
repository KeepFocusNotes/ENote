package com.epam.university.spring.enote.repository.jpa;

import com.epam.university.spring.enote.model.Note;
import com.epam.university.spring.enote.repository.GenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class JpaNoteRepositoryImpl extends JpaAbstractGenericDao<Note> implements GenericDao<Note>
        , JpaNoteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean delete(Note entity) {
        return entityManager.createNamedQuery(Note.DELETE)
                .setParameter("id", entity.getId())
                .executeUpdate() != 0;
    }

    @Override
    public List<Note> getAll() {
        return entityManager.createNamedQuery(Note.ALL_SORTED, Note.class).getResultList();
    }

    @Override
    public boolean deleteAll() {
        return entityManager.createNamedQuery(Note.DELETE_ALL)
                .executeUpdate() != 0;
    }

    public Set<Note> getByNotepadId(Integer notepadId) {
        return new HashSet<>(entityManager.createNamedQuery(Note.GET_BY_NOTEPAD_ID)
                .setParameter("id",notepadId)
                .getResultList());
    }
}