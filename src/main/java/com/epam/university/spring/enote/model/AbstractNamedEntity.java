package com.epam.university.spring.enote.model;

import lombok.Getter;
import lombok.Setter;


/**
 * An abstract class designed for a person to be able to identify an entity (easier to perceive
 * than a digital id).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Named_entity">In the expression "Named Entity" the
 * word "Named" aims to restrict the possible set of entities to only those for which one or many
 * rigid designators stands for the referent.A designator is rigid when it designates the same thing
 * in every possible world.
 */

@Getter
@Setter
public abstract class AbstractNamedEntity extends AbstractBaseEntity {
    protected String mail;

    public AbstractNamedEntity() {
        System.out.println(getId());
    }

    protected AbstractNamedEntity(Integer id, String mail) {
        super(id);
        this.mail = mail;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, mail);
    }
}