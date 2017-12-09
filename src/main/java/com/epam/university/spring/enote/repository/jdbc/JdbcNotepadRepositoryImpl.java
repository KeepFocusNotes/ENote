package com.epam.university.spring.enote.repository.jdbc;

import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.repository.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;

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
        return null;
    }

    @Override
    public List<Notepad> getAll() {
        return null;
    }

    @Override
    public Notepad save(Notepad entity) {
        return null;
    }

    @Override
    public List<Notepad> saveAll(List<Notepad> entity) {
        return null;
    }

    @Override
    public boolean delete(Notepad entity) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}