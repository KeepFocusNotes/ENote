package com.epam.university.spring.enote.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class User extends AbstractNamedEntity {
    private String email;
    //temporal field
    private String password;
    private LocalDate birthDate;
    private Date registered = new Date();
    private Set<Role> roles;

    public User() {
        System.out.println(getId());
    }

    public User(User u) {
        this(u.getId(), u.getEmail(), u.getPassword(), u.getBirthDate(), u.getRegistered(),
                u.getRoles());
    }

    public User(Integer id, String email, String password, LocalDate birthDate, Date registered,
                Set<Role> roles) {
        super(id, email);
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.registered = registered;
        this.roles = roles;
    }
}