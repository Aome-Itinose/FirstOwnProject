package org.Aome.controller;

import jakarta.validation.Valid;
import org.Aome.models.Author;
import org.Aome.services.AuthorsService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorsController {
    private final AuthorsService authorsService;

    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping()
    public String showAll(Model model){
        model.addAttribute("authors", authorsService.getAuthors());
        return "authors/showAll";
    }

    @Transactional
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        Optional<Author> author = authorsService.getAuthorById(id);
        model.addAttribute("author", author.orElse(null));
        author.ifPresent(value -> {
            Hibernate.initialize(value.getWrittenBooks());
            model.addAttribute("writtenBooks", value.getWrittenBooks());});
        return "authors/show";
    }


    //Create
    @GetMapping("/new")
    public String newAuthorView(@ModelAttribute("author") Author author){
        return "authors/newAuthorView";
    }

    @PostMapping()
    public String newAuthorSet(@ModelAttribute("author") Author author){
        authorsService.save(author);
        return "redirect:/authors";
    }


    //Update
    @GetMapping("/{id}/edit")
    public String editAuthorView(Model model, @PathVariable("id") int id){
        model.addAttribute("author", authorsService.getAuthorById(id).orElse(null));
        return "authors/editAuthorView";
    }

    @PatchMapping("/{id}")
    public String editAuthor(@PathVariable("id") int id, @ModelAttribute("author")@Valid Author author){
        authorsService.update(id, author);
        return "redirect:/authors/{id}";
    }


    //Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        authorsService.delete(id);
        return "redirect:/authors";
    }
}
