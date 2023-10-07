package org.Aome.services;

import org.Aome.models.Author;
import org.Aome.models.Book;
import org.Aome.models.Person;
import org.Aome.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        booksRepository.findById(id).orElse(new Book()).setOwner(null);
    }

    @Transactional
    public void setOwner(int id, Book updatedBook){
        Optional<Book> book = booksRepository.findById(id);
        book.ifPresent(value -> value.setOwner(peopleService.getPersonById(updatedBook.getOwnerId()).get()));
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

}
