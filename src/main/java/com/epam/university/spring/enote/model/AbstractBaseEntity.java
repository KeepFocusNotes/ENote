package com.epam.university.spring.enote.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class AbstractBaseEntity implements Serializable{
    public static final int START_SEQ = 100000;
    protected Integer id;

    public AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractBaseEntity that = (AbstractBaseEntity) o;

        //excluded the case when both id equal to zero
        return id != null && id.equals(that.id);
    }

    //TODO is returning just if is enough here or we need to use hashcode
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}