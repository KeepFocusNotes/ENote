package com.epam.university.spring.enote.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notepad extends AbstractBaseEntity {
    public String title;
    public Integer userId;

    public Notepad(Notepad notepad) {
        this(notepad.getId(), notepad.getTitle(), notepad.getUserId());
    }

    public Notepad(Integer id, String title, Integer userId) {
        super(id);
        this.title = title;
        this.userId = userId;
    }
}
