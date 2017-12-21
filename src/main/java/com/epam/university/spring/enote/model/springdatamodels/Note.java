package com.epam.university.spring.enote.model.springdatamodels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Note implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  private String title;

  private String description;

  @JsonIgnore
  @OneToMany(mappedBy = "note", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<TagNote> tagNotes;
}
