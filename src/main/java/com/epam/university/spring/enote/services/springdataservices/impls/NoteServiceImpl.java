package com.epam.university.spring.enote.services.springdataservices.impls;

import com.epam.university.spring.enote.model.springdatamodels.Note;
import com.epam.university.spring.enote.repository.springdatajpa.NoteRepository;
import com.epam.university.spring.enote.services.springdataservices.NoteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

  private NoteRepository noteRepository;

  @Autowired
  public NoteServiceImpl(
      NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
  }

  @Override
  public Note getById(Long id) {
    return noteRepository.findOne(id);
  }

  @Override
  public List<Note> getAll() {
    return noteRepository.findAll();
  }

  @Override
  public Note createOrUpdate(Note note) {
    return noteRepository.save(note);
  }

  @Override
  public void delete(Long id) {
    noteRepository.delete(id);
  }
}
