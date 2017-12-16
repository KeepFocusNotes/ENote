package com.epam.university.spring.enote.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notes", uniqueConstraints = {@UniqueConstraint(columnNames = {"notepad_id",
        "title"}, name = "notes_unique_notepad_title")})
public class Note extends AbstractBaseEntity {

    public static final String DELETE = "Note.delete";
    public static final String DELETE_ALL = "Note.deleteAll";
    public static final String ALL_SORTED = "Note.getAllSorted";
    
    @Column(name = "title", nullable = false)
    @NotBlank
    @Size(min = 1, max = 128)
    public String title;

    @Column(name = "description")
    @Size(max = 120)
    public String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notepad_id", nullable = false)
    @NotNull
    public Notepad notepad;

    public Note(Note note) {
        this(note.getId(), note.getTitle(), note.getDescription());
    }

    public Note(Integer id, String title, String description) {
        super(id);
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
