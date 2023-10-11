package org.Aome.services;

import org.Aome.models.Author;
import org.Aome.models.Book;
import org.Aome.models.Person;
import org.Aome.repositories.BooksRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleService peopleService;
    private final AuthorsService authorsService;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleService peopleService, AuthorsService authorsService) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
        this.authorsService = authorsService;
    }

    public List<Book> getBooks(){
        return booksRepository.findAll();
    }

    public List<Book> getBooksWithInitializeOwnerAndAuthor(){
        List<Book> books = booksRepository.findAll();
        books.forEach(book -> {
            Hibernate.initialize(book.getOwner());
            Hibernate.initialize(book.getAuthor());
        });
        return books;
    }

    public List<Book> getBooks(Optional<Integer> page, Optional<Integer> booksPerPage, boolean sortByYear){
        List<Book> books;
        if (page.isPresent() & booksPerPage.isPresent()){
            if (sortByYear){
                books = booksRepository.findAll(
                        PageRequest.of(page.get(), booksPerPage.get(), Sort.by("year"))).getContent();
            }else{
                books = booksRepository.findAll(
                        PageRequest.of(page.get(), booksPerPage.get())).getContent();
            }
        }else{
            if (sortByYear){
                books = booksRepository.findAll(Sort.by("year"));
            }else{
                books = booksRepository.findAll();
            }
        }
        return books;
    }

    public Optional<Book> getBook(int id){
        return booksRepository.findById(id);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book newBook){
        newBook.setId(id);
        booksRepository.save(newBook);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void clearOwner(int id){
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(null);
            book.setDateAndTime(null);
        });
    }

    @Transactional
    public void setOwner(int id, Book updatedBook){
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwnerId(updatedBook.getOwnerId());
            book.setOwner(peopleService.getPersonById(updatedBook.getOwnerId()).orElse(null));
            book.setDateAndTime(new Date());
        });
    }

    public List<Person> getPeople(){
        return peopleService.getPeople();
    }

    public List<Author> getAuthors(){
        return authorsService.getAuthors();
    }

    public Optional<Author> getAuthorById(int id){
        return authorsService.getAuthorById(id);
    }


    public List<Book> findByNameStartWith(String startString){
        List<Book> books = booksRepository.findByNameStartsWith(startString);
        books.forEach(e -> Hibernate.initialize(e.getOwner()));
        books.forEach(book -> Hibernate.initialize(book.getAuthor()));
        return books;
    }

}
