package com.epam.university.spring.enote.repository.jdbc;

import com.epam.university.spring.enote.model.User;
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

/*Jpa profile is on
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class JdbcUserRepositoryImpl extends JdbcAbstractGenericDao<User> implements
        GenericDao<User> {

    //takes the fields throw reflection with using getters
    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance
            (User.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //takes the fields throw reflection with using setters
    private final SimpleJdbcInsert simpleJdbcInsert;

    //for all possible deps?
    @Autowired
    public JdbcUserRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate
            namedParameterJdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public User get(Integer id) {
        List<User> userList = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(userList);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY email", ROW_MAPPER);
    }

    @Override
    public User save(User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);

        if (user.isNew()) {
            Number newKey = simpleJdbcInsert.executeAndReturnKey(parameterSource);
            user.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE users SET email=:email, password=:password, " +
                        "birth_date=:birthDate,registration_date=:registrationDate " +
                        "WHERE id=:id", parameterSource) == 0) {
            return null;
        }
        return user;
    }

    @Override
    public List<User> saveAll(List<User> users) {
        return users.stream().map(this::save).collect(Collectors.toList());
    }

    //TODO - optional - cascade removing
    //create abstract for each repo
    @Override
    public boolean delete(User user) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", user.getId()) != 0;
    }

    //TODO - optional - cascade removing
    @Override
    public boolean deleteAll() {
        return jdbcTemplate.update("DELETE FROM users") != 0;
    }
}
*/