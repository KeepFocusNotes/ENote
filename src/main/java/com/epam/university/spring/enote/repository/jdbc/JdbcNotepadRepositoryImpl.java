package com.epam.university.spring.enote.repository.jdbc;

import com.epam.university.spring.enote.model.Notepad;
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
public class JdbcNotepadRepositoryImpl extends JdbcAbstractGenericDao<Notepad> implements
        GenericDao<Notepad> {

    private static final BeanPropertyRowMapper<Notepad> ROW_MAPPER = BeanPropertyRowMapper
            .newInstance(Notepad.class);
    //TODO why not create utils with getter?!
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    //for all possible deps?
    @Autowired
    public JdbcNotepadRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate
            namedParameterJdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("notepads")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Notepad get(Integer id) {
        List<Notepad> notepadList = jdbcTemplate.query("SELECT * FROM notepads WHERE id=?", ROW_MAPPER,
                id);
        return DataAccessUtils.singleResult(notepadList);
    }

    @Override
    public List<Notepad> getAll() {
        return jdbcTemplate.query("SELECT * FROM notepads ORDER BY title", ROW_MAPPER);
    }

    @Override
    public Notepad save(Notepad notepad) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(notepad);

        if (notepad.isNew()) {
            Number newKey = simpleJdbcInsert.executeAndReturnKey(parameterSource);
            notepad.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE notepads SET title=:title WHERE id=:id", parameterSource) == 0) {
            return null;
        }
        return notepad;
    }

    @Override
    public List<Notepad> saveAll(List<Notepad> notepads) {
        return notepads.stream().map(this::save).collect(Collectors.toList());
    }

    //TODO cascade removing
    //create abstract for each repo
    @Override
    public boolean delete(Notepad notepad) {
        return jdbcTemplate.update("DELETE FROM notepads WHERE id=?", notepad.getId()) != 0;
    }

    //TODO cascade removing
    @Override
    public boolean deleteAll() {
        return jdbcTemplate.update("DELETE FROM notepads") != 0;
    }
}