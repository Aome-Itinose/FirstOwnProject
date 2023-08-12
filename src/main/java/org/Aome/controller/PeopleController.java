package org.Aome.controller;

import org.Aome.dao.PersonDAO;
import org.Aome.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDao;

    @Autowired
    public PeopleController(PersonDAO personDao) {
        this.personDao = personDao;
    }


    //Show
    @GetMapping()
    public String showAll(Model model){
        model.addAttribute("people", personDao.getPeopleList());
        return "people/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDao.getPerson(id).orElse(null));
        return "people/show";
    }


    //Create
    @GetMapping("/new")
    public String newPersonView(@ModelAttribute("person") Person person){
        return "people/newPersonView";
    }

    @PostMapping()
    public String newPersonSet(@ModelAttribute("person")Person person){
        personDao.create(person);
        return "redirect:/people";
    }


    //Update
    @GetMapping("/{id}/edit")
    public String editPersonView(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDao.getPerson(id).orElse(null));
        return "people/editPersonView";
    }

    @PatchMapping("/{id}")
    public String editPerson(@PathVariable("id") int id, @ModelAttribute("person")Person person){
        personDao.edit(id, person);
        return "redirect:/people/{id}";
    }


    //Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        personDao.delete(id);
        return "redirect:/people";
    }
}
