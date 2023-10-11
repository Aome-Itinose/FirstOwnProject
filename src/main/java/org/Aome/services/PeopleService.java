package org.Aome.services;

import org.Aome.models.Person;
import org.Aome.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> getPeople() {
        return peopleRepository.findAll();
    }

    public Optional<Person> getPersonById(int id) {
        Calendar now = new GregorianCalendar();
        Optional<Person> person = peopleRepository.findById(id);
        person.ifPresent(p -> p.getBooks().forEach(e -> {
            Calendar date = new GregorianCalendar();
            date.setTime(e.getDateAndTime());
            e.setOverdue(now.getTimeInMillis() - date.getTimeInMillis() > 864000000);
        }));
        return person;
    }

    public Optional<Person> getPersonByName(String name) {
        return peopleRepository.findByFullName(name);
    }

    @Transactional
    public void save(Person newPerson) {
        peopleRepository.save(newPerson);
    }

    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

}
