package com.epam.university.spring.enote.controllers;

import com.epam.university.spring.enote.model.Note;
import com.epam.university.spring.enote.model.Tag;
import com.epam.university.spring.enote.services.NoteService;
import java.util.List;
import java.util.Set;
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
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAll() {
        return noteService.getAll();
    }

    @GetMapping("/notes/{id}")
    @ResponseStatus(HttpStatus.OK)
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

    @GetMapping("/notes/{id}/tags")
    @ResponseStatus(HttpStatus.OK)
    public Set<Tag> getTagsByNoteId(@PathVariable Integer id) {
        return noteService.getTags(id);
    }

    @PostMapping("/notes/{noteId}/tags/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public void addTagToNote(@PathVariable Integer noteId, @PathVariable Integer tagId){
        noteService.addTagToNoteById(noteId, tagId);
    }

    @DeleteMapping("/notes/{noteId}/tags/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTagFromNote(@PathVariable Integer noteId, @PathVariable Integer tagId){
        noteService.deleteTagToNoteById(noteId, tagId);
    }

    @GetMapping("/notes/tags/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Note> getNotesByTagId(@PathVariable Integer tagId){
        return noteService.getAllNotesByTag(tagId);
    }

    @GetMapping("/notepads/{notepadId}/notes")
    @ResponseStatus(HttpStatus.OK)
    public Set<Note> getNotesByNotepadId(@PathVariable Integer notepadId){
        return noteService.getNotesByNotepadId(notepadId);
    }

    @GetMapping("/users/{userId}/notes")
    @ResponseStatus(HttpStatus.OK)
    public Set<Note> getNotesByUserId(@PathVariable Integer userId){
        return noteService.getNotesByUserId(userId);
    }
}
