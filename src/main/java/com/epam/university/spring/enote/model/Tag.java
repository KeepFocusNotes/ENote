package com.epam.university.spring.enote.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends AbstractBaseEntity {
    public String title;
    public Integer noteId;

    public Tag(Tag tag) {
        this(tag.getId(), tag.getTitle(), tag.getNoteId());
    }

    public Tag(Integer id, String title, Integer noteId) {
        super(id);
        this.title = title;
        this.noteId = noteId;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "title='" + title + '\'' +
                ", noteId=" + noteId +
                ", id=" + id +
                '}';
    }
}
