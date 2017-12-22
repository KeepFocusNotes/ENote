package com.epam.university.spring.enote.repository.jdbc;

import com.epam.university.spring.enote.model.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/*Jpa profile is on
@Getter
@Setter
@Repository
public abstract class JdbcAbstractGenericDao<T extends AbstractBaseEntity> {
    //TODO - optional -  test with/without diff lombok's annotations
    public Class<T> entityClass;

    public abstract T get(Integer id);

    public abstract List<T> getAll();

    public abstract T save(T entity);

    public abstract List<T> saveAll(List<T> entity);

    public abstract boolean delete(T entity);

    public abstract boolean deleteAll();
}
*/