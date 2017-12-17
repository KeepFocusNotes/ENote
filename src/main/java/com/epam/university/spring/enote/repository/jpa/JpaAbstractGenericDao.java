package com.epam.university.spring.enote.repository.jpa;

import com.epam.university.spring.enote.model.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Repository
public abstract class JpaAbstractGenericDao<T extends AbstractBaseEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    public Class<T> entityClass;


    public JpaAbstractGenericDao() {
        if (entityClass == null) {
            //only works if one extends BaseDao, we will take care of it with CDI
            entityClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
        }
    }

    @Transactional
    public T save(T entity) {
        if (entity.isNew()) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }

    public T get(Integer id) {
        return entityManager.find(entityClass, id);
    }

    //TODO fix the transactional
    public List<T> saveAll(List<T> entities) {
        return entities.stream().map(this::save).collect(Collectors.toList());
    }

    public abstract boolean delete(T entity);

    public abstract List<T> getAll();

    public abstract boolean deleteAll();
}