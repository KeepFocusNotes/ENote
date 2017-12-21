package com.epam.university.spring.enote.services.springdataservices.impls;

import com.epam.university.spring.enote.model.springdatamodels.TagNote;
import com.epam.university.spring.enote.repository.springdatajpa.TagNoteRepository;
import com.epam.university.spring.enote.services.springdataservices.TagNoteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagNoteServiceImpl implements TagNoteService {

  private TagNoteRepository tagNoteRepository;

  @Autowired
  public TagNoteServiceImpl(
      TagNoteRepository tagNoteRepository) {
    this.tagNoteRepository = tagNoteRepository;
  }

  @Override
  public TagNote getById(Long id) {
    return tagNoteRepository.findOne(id);
  }

  @Override
  public List<TagNote> getAll() {
    return tagNoteRepository.findAll();
  }

  @Override
  public TagNote addTagToNote(TagNote tagNote) {
    return tagNoteRepository.save(tagNote);
  }

  @Override
  public void delete(Long id) {
    tagNoteRepository.delete(id);
  }
}
