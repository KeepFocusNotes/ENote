package com.epam.university.spring.enote.services.springdataservices;

import com.epam.university.spring.enote.model.springdatamodels.Tag;
import java.util.List;

public interface TagService {
    Tag getById(Long id);

    List<Tag> getAll();

    Tag createOrUpdate(Tag tag);

    void delete(Long id);
}
