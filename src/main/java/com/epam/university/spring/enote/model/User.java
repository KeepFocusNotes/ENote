package com.epam.university.spring.enote.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
//TODO uniqueConstraints = {@UniqueConstraint ??
@Table(name = "users")
public class User extends AbstractNamedEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    //temporal field
    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 128)
    private String password;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "registration_date", columnDefinition = "timestamp default now()")
    @NotNull
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

    //TODO optimize
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