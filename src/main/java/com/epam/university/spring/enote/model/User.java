package com.epam.university.spring.enote.model;

import com.epam.university.spring.enote.util.DateConverter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.DELETE_ALL, query = "DELETE FROM User"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.email"),
})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}, name =
        "users_unique_email")})
public class User extends AbstractNamedEntity {

    public static final String DELETE = "User.delete";
    public static final String DELETE_ALL = "User.deleteAll";
    public static final String ALL_SORTED = "User.getAllSorted";

    //temporal field
    @Column(name = "password", nullable = false)
    @Size(min = 5, max = 128)
    private String password;

    @Column(name = "birth_date")
    private Timestamp birthDate;

    @Column(name = "registration_date", columnDefinition = "timestamp default now()")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Timestamp registrationDate = new DateConverter().convertToDatabaseColumn(LocalDate.now());

    public User() {
    }

    //copying constructor
    public User(User u) {
        this(u.getId(), u.getEmail(), u.getPassword(), u.getBirthDate());
    }

    public User(Integer id, String email, String password, Timestamp birthDate) {
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
}