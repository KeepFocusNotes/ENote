package com.epam.university.spring.enote.model;

import java.time.LocalDate;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends AbstractNamedEntity {
    private String email;
    //temporal field
    private String password;
    private LocalDate birthDate;
    private LocalDate registrationDate = LocalDate.now();

    public User() {
        System.out.println(getId());
    }

    //copying constructor
    public User(User u) {
        this(u.getId(), u.getEmail(), u.getPassword(), u.getBirthDate(), u.getRegistrationDate());
    }

    public User(Integer id, String email, String password, LocalDate birthDate,
                LocalDate registrationDate) {
        super(id, email);
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
    }
}