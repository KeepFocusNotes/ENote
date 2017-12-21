package com.epam.university.spring.enote.model.springdatamodels;

import com.epam.university.spring.enote.util.DateConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String email;
  private String password;

  @Column
  private String username;

  @Column(name = "registration_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
  private Timestamp registrationDate = new DateConverter().convertToDatabaseColumn(LocalDate.now());
}
