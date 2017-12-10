package com.epam.university.spring.enote.repository.jdbc;

import com.epam.university.spring.enote.model.Note;
import com.epam.university.spring.enote.repository.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class JdbcNoteRepositoryImpl extends JdbcAbstractGenericDao<Note> implements
        GenericDao<Note> {

    private static final BeanPropertyRowMapper<Note> ROW_MAPPER = BeanPropertyRowMapper.newInstance
            (Note.class);
    //TODO why not create utils with getter?!
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    //for all possible deps?
    @Autowired
    public JdbcNoteRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate
            namedParameterJdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("notes")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Note get(Integer id) {
        List<Note> NoteList = jdbcTemplate.query("SELECT * FROM notes WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(NoteList);
    }

    @Override
    public List<Note> getAll() {
        return jdbcTemplate.query("SELECT * FROM notes ORDER BY email", ROW_MAPPER);
    }

    @Override
    public Note save(Note Note) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(Note);

        if (Note.isNew()) {
            Number newKey = simpleJdbcInsert.executeAndReturnKey(parameterSource);
            Note.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE notes SET title=:title,desctiption=:description WHERE id=:id",
                parameterSource) == 0) {
            return null;
        }
        return Note;
    }

    @Override
    public List<Note> saveAll(List<Note> notes) {
        return notes.stream().map(this::save).collect(Collectors.toList());
    }

    //TODO cascade removing
    //create abstract for each repo
    @Override
    public boolean delete(Note Note) {
        return jdbcTemplate.update("DELETE FROM notes WHERE id=?", Note.getId()) != 0;
    }

    //TODO cascade removing
    @Override
    public boolean deleteAll() {
        return jdbcTemplate.update("DELETE FROM notes") != 0;
    }
}