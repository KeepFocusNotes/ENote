package com.epam.university.spring.enote.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * An abstract class designed for a person to be able to identify an entity (easier to perceive
 * than a digital id). In the project the inheritance from this class is using only for human-like
 * entities.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Named_entity">In the expression "Named Entity" the
 * word "Named" aims to restrict the possible set of entities to only those for which one or many
 * rigid designators stands for the referent.A designator is rigid when it designates the same thing
 * in every possible world.
 */

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    protected String email;

    public AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id, String mail) {
        super(id);
        this.email = mail;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, email);
    }
}