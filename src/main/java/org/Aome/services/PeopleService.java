package org.Aome.services;

import org.Aome.models.Person;
import org.Aome.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> getPeople(){
        return peopleRepository.findAll();
    }

    public Optional<Person> getPersonById(int id){
        return peopleRepository.findById(id);
    }

    public Optional<Person> getPersonByName(String name){
        return peopleRepository.findByFullName(name);
    }

    @Transactional
    public void save(Person newPerson){
        peopleRepository.save(newPerson);
    }

    @Transactional
    public void update(int id, Person person){
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

}
