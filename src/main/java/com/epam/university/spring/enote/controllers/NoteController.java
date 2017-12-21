package com.epam.university.spring.enote.controllers;

import com.epam.university.spring.enote.model.Note;
import com.epam.university.spring.enote.services.NoteService;
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
public class NoteController {
    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public List<Note> getAll() {
        return noteService.getAll();
    }

    @GetMapping("/notes/{id}")
    public Note getById(@PathVariable Integer id) {
        return noteService.getById(id);
    }

    @PostMapping("/notes")
    @ResponseStatus(HttpStatus.CREATED)
    public Note create(@RequestBody Note note) {
        return noteService.create(note);
    }

    @DeleteMapping("/notes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        Note toPassNote = new Note();
        toPassNote.setId(id);
        noteService.delete(toPassNote);
    }
}
