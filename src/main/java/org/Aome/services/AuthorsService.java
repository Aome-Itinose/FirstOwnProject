package org.Aome.services;

import org.Aome.models.Author;
import org.Aome.repositories.AuthorsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AuthorsService {
    private final AuthorsRepository authorsRepository;

    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public List<Author> getAuthors(){
        return authorsRepository.findAll();
    }
    public Optional<Author> getAuthorById(int id){
        return authorsRepository.findById(id);
    }

    @Transactional
    public void save(Author newAuthor){
        authorsRepository.save(newAuthor);
    }

    @Transactional
    public void delete(int id){
        authorsRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Author newAuthor){
        newAuthor.setId(id);
        authorsRepository.save(newAuthor);
    }
}
