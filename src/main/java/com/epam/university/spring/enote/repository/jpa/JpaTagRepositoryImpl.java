package com.epam.university.spring.enote.repository.jpa;

import com.epam.university.spring.enote.model.Tag;
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
public class JpaTagRepositoryImpl extends JpaAbstractGenericDao<Tag> implements GenericDao<Tag> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
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
    @Transactional
    public boolean deleteAll() {
        return entityManager.createNamedQuery(Tag.DELETE_ALL)
                .executeUpdate() != 0;
    }
}