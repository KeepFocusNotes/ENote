package com.epam.university.spring.enote.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class User extends AbstractNamedEntity {
    private String email;
    //temporal field
    private String password;
    private LocalDate birthDate;
    private LocalDate registrationDate = LocalDate.now();

    public User() {
    }

    //copying constructor
    public User(User u) {
        this(u.getId(), u.getEmail(), u.getPassword(), u.getBirthDate());
    }

    public User(Integer id, String email, String password, LocalDate birthDate) {
        super(id, email);
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", registrationDate=" + registrationDate +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!email.equals(user.email)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}