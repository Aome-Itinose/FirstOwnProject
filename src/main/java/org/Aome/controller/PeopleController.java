package org.Aome.controller;

import jakarta.validation.Valid;
import org.Aome.dao.PersonDAO;
import org.Aome.model.Person;
import org.Aome.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonValidator personValidator;
    private final PersonDAO personDao;

    @Autowired
    public PeopleController(PersonValidator personValidator, PersonDAO personDao) {
        this.personValidator = personValidator;
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
        model.addAttribute("personBooks", personDao.getPersonBooks(id));
        return "people/show";
    }


    //Create
    @GetMapping("/new")
    public String newPersonView(@ModelAttribute("person") Person person){
        return "people/newPersonView";
    }

    @PostMapping()
    public String newPersonSet(@ModelAttribute("person")@Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "people/newPersonView";
        }
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
    public String editPerson(@PathVariable("id") int id, @ModelAttribute("person")@Valid Person person,
                             BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "people/editPersonView";
        }
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
