package com.epam.university.spring.enote.services.springdataservices;

import com.epam.university.spring.enote.model.springdatamodels.Notepad;
import java.util.List;

public interface NotepadService {

  Notepad getById(Long id);

  List<Notepad> getAll();

  Notepad createOrUpdate(Notepad notepad);

  void delete(Long id);

}
