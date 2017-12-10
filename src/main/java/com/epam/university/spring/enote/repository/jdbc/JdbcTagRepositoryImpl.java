package com.epam.university.spring.enote.repository.jdbc;

import com.epam.university.spring.enote.model.Tag;
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
public class JdbcTagRepositoryImpl extends JdbcAbstractGenericDao<Tag> implements
        GenericDao<Tag> {

    //takes the fields throw reflection with using getters
    private static final BeanPropertyRowMapper<Tag> ROW_MAPPER = BeanPropertyRowMapper
            .newInstance(Tag.class);
    //TODO why not create utils with getter?!
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //takes the fields throw reflection with using setters
    private final SimpleJdbcInsert simpleJdbcInsert;

    //for all possible deps?
    @Autowired
    public JdbcTagRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate
            namedParameterJdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("tags")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Tag get(Integer id) {
        List<Tag> TagList = jdbcTemplate.query("SELECT * FROM tags WHERE id=?", ROW_MAPPER,
                id);
        return DataAccessUtils.singleResult(TagList);
    }

    @Override
    public List<Tag> getAll() {
        return jdbcTemplate.query("SELECT * FROM tags ORDER BY title", ROW_MAPPER);
    }

    @Override
    public Tag save(Tag Tag) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(Tag);

        if (Tag.isNew()) {
            Number newKey = simpleJdbcInsert.executeAndReturnKey(parameterSource);
            Tag.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE tags SET title=:title WHERE id=:id", parameterSource) == 0) {
            return null;
        }
        return Tag;
    }

    @Override
    public List<Tag> saveAll(List<Tag> tags) {
        return tags.stream().map(this::save).collect(Collectors.toList());
    }

    //TODO cascade removing
    //create abstract for each repo
    @Override
    public boolean delete(Tag Tag) {
        return jdbcTemplate.update("DELETE FROM tags WHERE id=?", Tag.getId()) != 0;
    }

    //TODO cascade removing
    @Override
    public boolean deleteAll() {
        return jdbcTemplate.update("DELETE FROM tags") != 0;
    }
}