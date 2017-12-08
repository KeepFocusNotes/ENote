package com.epam.university.spring.enote.model;

import lombok.Getter;
import lombok.Setter;

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