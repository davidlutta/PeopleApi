package com.davidlutta.PersonApi.dao;

import com.davidlutta.PersonApi.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface PersonDAO {
    void insertPerson(UUID id, Person person);
    default void insertPerson(Person person){
        UUID id = UUID.randomUUID();
        insertPerson(id,person);
    }
    Optional<Person> selectPersonById(UUID id);
    List<Person> selectAllPeople();
    void deletePersonById(UUID id);
    void updatePersonById(UUID id, Person person);
    int getTotalNumberOfPeople();
}
