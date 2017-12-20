package com.epam.university.spring.enote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = Note.DELETE, query = "DELETE FROM Note n WHERE n.id=:id"),
        @NamedQuery(name = Note.DELETE_ALL, query = "DELETE FROM Note"),
        @NamedQuery(name = Note.ALL_SORTED, query = "SELECT n FROM Note n ORDER BY n.title"),
})
@Entity
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

    //TODO check EAGER to print vs LAZY
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "notepad_id", nullable = false)
    @NotNull
    @JsonIgnore
    public Notepad notepad;

    public Note(Note note) {
        this(note.getId(), note.getTitle(), note.getDescription(), note.getNotepad());
    }

    public Note(Integer id, String title, String description, Notepad notepad) {
        super(id);
        this.title = title;
        this.description = description;
        this.notepad = notepad;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", notepad=" + notepad +
                ", id=" + id +
                '}';
    }
}
