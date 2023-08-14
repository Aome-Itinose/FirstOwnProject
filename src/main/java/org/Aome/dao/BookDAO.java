package org.Aome.dao;

import org.Aome.model.Book;
import org.Aome.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    private final PersonDAO personDAO;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate, PersonDAO personDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.personDAO = personDAO;
    }

    public List<Book> getBookList(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> getBook(int id){
        Optional<Book> book = jdbcTemplate.query("SELECT * FROM Book where id=?", new BeanPropertyRowMapper<>(Book.class), new Object[]{id}).stream().findAny();
        if(book.isPresent() && book.get().getOwner_id()!=null){
            book.get().setOwner(personDAO.getPerson(book.get().getOwner_id()).get());
        }
        return book;
    }

    public void create(Book book){
        jdbcTemplate.update("insert into book(name, author, year) values (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void edit(int id, Book book){
        jdbcTemplate.update("update book set name=?, author=?, year=? where id=?", book.getName(), book.getAuthor(), book.getYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("delete from book where id=?", id);
    }

    public void clearOwner(int id){
        jdbcTemplate.update("update book set owner_id=null where id=?", id);
    }

    public List<Person> getPeopleList(){
        return personDAO.getPeopleList();
    }

    public void setOwner(int book_id, Book book){
        jdbcTemplate.update("update book set owner_id=? where id=?", book.getOwner_id(), book_id);
    }
}
