package org.Aome.controller;

import org.Aome.dao.BookDAO;
import org.Aome.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    //Show
    @GetMapping()
    public String showAll(Model model){
        model.addAttribute("books", bookDAO.getBookList());
        return "books/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.getBook(id).orElse(null));
        return "books/show";
    }


    //Create
    @GetMapping("/new")
    public String newBookView(@ModelAttribute("book") Book book){
        return "books/newBookView";
    }

    @PostMapping()
    public String newBookSet(@ModelAttribute("book") Book book){
        bookDAO.create(book);
        return "redirect:/books";
    }


    //Update
    @GetMapping("/{id}/edit")
    public String editBookView(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.getBook(id).orElse(null));
        return "books/editBookView";
    }

    @PatchMapping("/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book")Book book){
        bookDAO.edit(id, book);
        return "redirect:/books/{id}";
    }


    //Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
