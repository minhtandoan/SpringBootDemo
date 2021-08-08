package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("Postgres")
public class PostgreSQLDataAccessService implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgreSQLDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(@NonNull UUID id, @NonNull Person person) {
        String query = "INSERT INTO person(id,name) VALUES (?,?)";
        return jdbcTemplate.update(query, id, person.getName());
    }

    @Override
    public List<Person> getAllPerson() {
        String query = "SELECT id, name FROM person";
        return jdbcTemplate.query(query,
                (resultSet, i) -> {
                    return new Person(
                            UUID.fromString(resultSet.getString("id")),
                            resultSet.getString("name"));
                });
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        String query = "SELECT id, name FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(query,
                (resultSet, i) -> {
                    return new Person(
                            UUID.fromString(resultSet.getString("id")),
                            resultSet.getString("name"));
                },
                id);

        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        String query = "DELETE FROM person WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        String query = "UPDATE person SET name = ? WHERE id = ?";
        return jdbcTemplate.update(query,person.getName(),id);
    }
}
