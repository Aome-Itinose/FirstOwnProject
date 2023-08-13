package org.Aome.dao;

import org.Aome.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBookList(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> getBook(int id){
        return jdbcTemplate.query("SELECT * FROM Book where id=?", new BeanPropertyRowMapper<>(Book.class), new Object[]{id}).stream().findAny();
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
}
