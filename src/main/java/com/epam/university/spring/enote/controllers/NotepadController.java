package com.epam.university.spring.enote.controllers;

import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.services.NotepadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotepadController {
  private NotepadService notepadService;

  @Autowired
  public NotepadController(NotepadService notepadService) {
    this.notepadService = notepadService;
  }

  @GetMapping("/notepads")
  public List<Notepad> getAll(){
    return notepadService.getAll();
  }

  @GetMapping("/notepads/{id}")
  public Notepad getById(@PathVariable Integer id){
    return notepadService.getById(id);
  }

  @PostMapping("/notepads")
  @ResponseStatus(HttpStatus.CREATED)
  public Notepad create(@RequestBody Notepad notepad) {
    return notepadService.create(notepad);
  }

  @DeleteMapping("/notepads/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Integer id){
    Notepad toPassNotepad = new Notepad();
    toPassNotepad.setId(id);
    notepadService.delete(toPassNotepad);
  }

}
