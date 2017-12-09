package com.epam.university.spring.enote.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Note extends AbstractNamedEntity {
    public String title;
    public String description;
}
