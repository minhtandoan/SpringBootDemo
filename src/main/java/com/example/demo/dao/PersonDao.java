package com.example.demo.dao;

import com.example.demo.model.Person;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(@NotNull UUID id, @NotNull Person person);
    default int addPerson(Person person){
        UUID id=UUID.randomUUID();
        return insertPerson(id,person);
    }
    List<Person> getAllPerson();
    Optional<Person> getPersonById(UUID id);
    int deletePersonById(UUID id);
    int updatePersonById(UUID id, Person person);

}
