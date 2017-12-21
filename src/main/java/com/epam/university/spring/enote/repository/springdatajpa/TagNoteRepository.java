package com.epam.university.spring.enote.repository.springdatajpa;

import com.epam.university.spring.enote.model.springdatamodels.TagNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagNoteRepository extends JpaRepository<TagNote, Long> {

}
