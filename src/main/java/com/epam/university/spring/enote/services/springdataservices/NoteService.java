package com.epam.university.spring.enote.services.springdataservices;

import com.epam.university.spring.enote.model.springdatamodels.Note;
import java.util.List;

public interface NoteService {
  Note getById(Long id);

  List<Note> getAll();

  Note createOrUpdate(Note note);

  void delete(Long id);

}
