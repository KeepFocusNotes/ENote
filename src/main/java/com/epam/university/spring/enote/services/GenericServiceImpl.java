package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.AbstractBaseEntity;
import com.epam.university.spring.enote.repository.GenericDao;
import com.epam.university.spring.enote.util.ServiceValidatorUtil;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


@NoArgsConstructor
@Getter
@Service
public class GenericServiceImpl<T extends AbstractBaseEntity> implements
        GenericService<T> {

    private GenericDao<T> genericDao;

    public GenericServiceImpl(GenericDao<T> genericDao) {
        this.genericDao = genericDao;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public T getById(Integer id) {
        return ServiceValidatorUtil.validateNotFoundWithId(genericDao.get(id), id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<T> getAll() {
        return genericDao.getAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public T create(T entity) {
        Assert.notNull(entity, "Object to create of class " + entity.getClass() +
                " must not be null");
        return genericDao.save(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(T entity) {
        Assert.notNull(entity, "Object to update of class " + entity.getClass() +
                " must not be null");
        ServiceValidatorUtil.validateNotFoundWithId(genericDao.save(entity), entity.getId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<T> createFromList(List<T> entitys) {
        Assert.notNull(entitys, "List of objects " + entitys.getClass() +
                " must not be null");
        return entitys.stream().map(entity->genericDao.save(entity)).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(T entity) {
        ServiceValidatorUtil.validateNotFoundWithId(genericDao.delete(entity),
                entity.getId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteAll() {
        genericDao.deleteAll();
    }
}
