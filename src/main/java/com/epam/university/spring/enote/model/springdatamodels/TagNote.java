package com.epam.university.spring.enote.model.springdatamodels;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class TagNote implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "tag_id", insertable = false, updatable = false)
  private Tag tag;

  @ManyToOne
  @JoinColumn(name = "note_id", insertable = false, updatable = false)
  private Note note;
}
