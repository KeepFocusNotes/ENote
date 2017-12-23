package com.epam.university.spring.enote.repository.jpa;

import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.repository.GenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.epam.university.spring.enote.model.Notepad.GET_BY_USER_ID;

@Repository
public class JpaNotepadRepositoryImpl extends JpaAbstractGenericDao<Notepad> implements
        JpaNotepadRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean delete(Notepad entity) {
        return entityManager.createNamedQuery(Notepad.DELETE)
                .setParameter("id", entity.getId())
                .executeUpdate() != 0;
    }

    @Override
    public List<Notepad> getAll() {
        return entityManager.createNamedQuery(Notepad.ALL_SORTED, Notepad.class).getResultList();
    }

    @Override
    public boolean deleteAll() {
        return entityManager.createNamedQuery(Notepad.DELETE_ALL)
                .executeUpdate() != 0;
    }

    @Override
    public Set<Notepad> getByUserId(Integer userId) {
        return new HashSet<>(entityManager.createNamedQuery(GET_BY_USER_ID, Notepad.class)
                .setParameter("id", userId)
                .getResultList());
    }
}