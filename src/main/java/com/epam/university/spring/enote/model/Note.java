package com.epam.university.spring.enote.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Note extends AbstractBaseEntity {
    public String title;
    public String description;
    public Integer notepadId;

    public Note(Note note) {
        this(note.getId(), note.getTitle(), note.getDescription(), note.getNotepadId());
    }

    public Note(Integer id, String title, String description, Integer notepadId) {
        super(id);
        this.title = title;
        this.description = description;
        this.notepadId = notepadId;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", notepadId=" + notepadId +
                ", id=" + id +
                '}';
    }
}
