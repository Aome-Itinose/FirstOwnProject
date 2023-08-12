package org.Aome.dao;

import org.Aome.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeopleList(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> getPerson(int id){
        return jdbcTemplate.query("SELECT * FROM Person where id=?", new BeanPropertyRowMapper<>(Person.class), new Object[]{id}).stream().findAny();
    }

    public void create(Person person){
        jdbcTemplate.update("insert into person(nsf, year) values(?, ?)", person.getNsf(), person.getYear());
    }

    public void edit(int id, Person person){
        jdbcTemplate.update("update person set nsf=?, year=? where id=?", person.getNsf(), person.getYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("delete from person where id=?", id);
    }
}
