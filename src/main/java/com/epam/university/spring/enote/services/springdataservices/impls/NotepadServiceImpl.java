package com.epam.university.spring.enote.services.springdataservices.impls;

import com.epam.university.spring.enote.model.springdatamodels.Notepad;
import com.epam.university.spring.enote.repository.springdatajpa.NotepadRepository;
import com.epam.university.spring.enote.services.springdataservices.NotepadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotepadServiceImpl implements NotepadService {

  private NotepadRepository notepadRepository;

  @Autowired
  public NotepadServiceImpl(
      NotepadRepository notepadRepository) {
    this.notepadRepository = notepadRepository;
  }

  @Override
  public Notepad getById(Long id) {
    return notepadRepository.findOne(id);
  }

  @Override
  public List<Notepad> getAll() {
    return notepadRepository.findAll();
  }

  @Override
  public Notepad createOrUpdate(Notepad notepad) {
    return notepadRepository.save(notepad);
  }

  @Override
  public void delete(Long id) {
    notepadRepository.delete(id);
  }
}
