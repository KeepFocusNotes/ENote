package com.epam.university.spring.enote.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = Tag.DELETE, query = "DELETE FROM Tag t WHERE t.id=:id"),
        @NamedQuery(name = Tag.DELETE_ALL, query = "DELETE FROM Tag"),
        @NamedQuery(name = Tag.ALL_SORTED, query = "SELECT t FROM Tag t ORDER BY t.title"),
})
@Entity
@Table(name = "tags")
public class Tag extends AbstractBaseEntity {

    public static final String DELETE = "Tag.delete";
    public static final String DELETE_ALL = "Tag.deleteAll";
    public static final String ALL_SORTED = "Tag.getAllSorted";

    @Column(name = "title", nullable = false)
    @NotBlank
    @Size(min = 1, max = 128)
    public String title;
    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private Set<Note> notes = new HashSet<>();

    public Tag(Tag tag) {
        this(tag.getId(), tag.getTitle());
    }

    public Tag(Integer id, String title) {
        super(id);
        this.title = title;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
