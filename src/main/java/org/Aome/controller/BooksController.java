package org.Aome.controller;

import jakarta.validation.Valid;
import org.Aome.models.Book;
import org.Aome.services.BooksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }


    //Show
    @GetMapping()
    public String showAll(Model model){
        model.addAttribute("books", booksService.getBooks());
        return "books/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksService.getBook(id).orElse(null));
        model.addAttribute("people", booksService.getPeople());
        return "books/show";
    }


    //Create
    @GetMapping("/new")
    public String newBookView(@ModelAttribute("book") Book book, Model model){
        model.addAttribute("authors", booksService.getAuthors());
        return "books/newBookView";
    }

    @PostMapping()
    public String newBookSet(@ModelAttribute("book")@Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/newBookView";
        }
        book.setAuthor(booksService.getAuthorById(book.getAuthorId()).get());
        booksService.save(book);
        return "redirect:/books";
    }


    //Update
    @GetMapping("/{id}/edit")
    public String editBookView(Model model, @PathVariable("id") int id){
        model.addAttribute("book", booksService.getBook(id).orElse(null));
        return "books/editBookView";
    }

    @PatchMapping("/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book")@Valid Book book,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/editBookView";
        }
        booksService.update(id, book);
        return "redirect:/books/{id}";
    }


    //Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        booksService.delete(id);
        return "redirect:/books";
    }


    @PatchMapping("/{id}/edit/clearOwner")
    public String clearOwner(@PathVariable("id")int id){
        booksService.clearOwner(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/edit/setOwner")
    public String setOwner(@PathVariable("id")int id, @ModelAttribute("book")Book book){
        booksService.setOwner(id, book);
        return "redirect:/books/{id}";
    }
}
