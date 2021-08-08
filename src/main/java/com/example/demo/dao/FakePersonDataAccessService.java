package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> db = new ArrayList<>();

    @Override
    public int insertPerson(@NonNull UUID id, @NonNull Person person) {
        db.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> getAllPerson() {
        return db;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return db.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = getPersonById(id);
        if (personMaybe.isPresent()) {
            db.remove(personMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return getPersonById(id).map(p -> {
            int index = db.indexOf(p);
            db.set(index, new Person(id, person.getName()));
            return 1;
        }).orElse(0);
    }
}
