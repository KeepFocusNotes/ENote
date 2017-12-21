package com.epam.university.spring.enote.services.springdataservices.impls;

import com.epam.university.spring.enote.model.springdatamodels.Tag;
import com.epam.university.spring.enote.repository.springdatajpa.TagRepository;
import com.epam.university.spring.enote.services.springdataservices.TagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagServiceImpl implements TagService {

  private TagRepository tagRepository;

  @Autowired
  public TagServiceImpl(
      TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  @Override
  public Tag getById(Long id) {
    return tagRepository.findOne(id);
  }

  @Override
  public List<Tag> getAll() {
    return tagRepository.findAll();
  }

  @Override
  public Tag createOrUpdate(Tag tag) {
    return tagRepository.save(tag);
  }

  @Override
  public void delete(Long id) {
    tagRepository.delete(id);
  }
}
