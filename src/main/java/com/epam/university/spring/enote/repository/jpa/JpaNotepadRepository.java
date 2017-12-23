package com.epam.university.spring.enote.repository.jpa;

import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.repository.GenericDao;

import java.util.Set;

public interface JpaNotepadRepository extends GenericDao<Notepad> {
    Set<Notepad> getByUserId(Integer userId);
}
