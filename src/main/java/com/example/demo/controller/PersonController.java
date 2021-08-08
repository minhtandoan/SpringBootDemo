package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPerson(){
        return personService.getAllPerson();
    }

    @GetMapping("{id}")
    public Person getPersonById(@PathVariable UUID id){
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public void deletePersonById(@PathVariable UUID id){
        personService.deletePersonById(id);
    }

    @PutMapping("{id}")
    public void updatePersonById(@PathVariable UUID id, @RequestBody Person person){
        personService.updatePersonById(id,person);
    }
}
