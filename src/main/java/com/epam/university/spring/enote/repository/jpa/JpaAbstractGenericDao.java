package com.epam.university.spring.enote.repository.jpa;

import com.epam.university.spring.enote.model.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Repository
public abstract class JpaAbstractGenericDao<T extends AbstractBaseEntity> {
    //TODO is setted method in the implementing interface is needed for autowiring of the DAO in
    //services?

    @PersistenceContext
    private EntityManager entityManager;

    public Class<T> entityClass;

    @Transactional
    public T save(T entity) {
        if (entity.isNew()) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }

    public T get(Integer id){
        return entityManager.find(entityClass,id);
    }

    public abstract boolean delete(T entity);

    public abstract List<T> getAll();

    public abstract List<T> saveAll(List<T> entity);

    public abstract boolean deleteAll();
}