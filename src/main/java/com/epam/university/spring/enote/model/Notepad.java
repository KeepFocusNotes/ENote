package com.epam.university.spring.enote.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@ToString
@Table(name = "notepads", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id",
        "title"}, name = "notepads_unique_user_title")})
public class Notepad extends AbstractBaseEntity {

    public static final String DELETE = "Notepad.delete";
    public static final String DELETE_ALL = "Notepad.deleteAll";
    public static final String ALL_SORTED = "Notepad.getAllSorted";

    @Column(name = "title", nullable = false)
    @NotBlank
    @Size(min = 1, max = 128)
    public String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    public User user;

    public Notepad(Notepad notepad) {
        this(notepad.getId(), notepad.getTitle());
    }

    public Notepad(Integer id, String title) {
        super(id);
        this.title = title;
    }
}
