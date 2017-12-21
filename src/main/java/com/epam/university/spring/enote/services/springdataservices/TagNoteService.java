package com.epam.university.spring.enote.services.springdataservices;

import com.epam.university.spring.enote.model.springdatamodels.TagNote;
import java.util.List;

public interface TagNoteService {
  TagNote getById(Long id);

  List<TagNote> getAll();

  TagNote addTagToNote(TagNote tagNote);

  void delete(Long id);
}
