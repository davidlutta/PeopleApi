package com.davidlutta.PersonApi.service;

import com.davidlutta.PersonApi.dao.PersonDAO;
import com.davidlutta.PersonApi.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDAO personDAO;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public void addPerson(Person person){
        personDAO.insertPerson(person);
    }

    public List<Person> getPeople(){ return personDAO.selectAllPeople(); }

    public Optional<Person> getPersonById(UUID id){
        return personDAO.selectPersonById(id);
    }

    public void deletePerson(UUID id){
        personDAO.deletePersonById(id);
    }

    public void updatePersonById(UUID id, Person newPerson){
        personDAO.updatePersonById(id, newPerson);
    }
}
