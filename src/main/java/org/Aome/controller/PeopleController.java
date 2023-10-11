package org.Aome.controller;

import jakarta.validation.Valid;
import org.Aome.models.Person;
import org.Aome.services.PeopleService;
import org.Aome.util.PersonValidator;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonValidator personValidator;
    private final PeopleService peopleService;

    public PeopleController(PersonValidator personValidator, PeopleService peopleService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
    }


    //Show
    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("people", peopleService.getPeople());
        return "people/showAll";
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Optional<Person> person = peopleService.getPersonById(id);
        model.addAttribute("person", person.orElse(null));
        person.ifPresent(value -> {
            Hibernate.initialize(value.getBooks());
            model.addAttribute("personBooks", value.getBooks());
        });
        return "people/show";
    }


    //Create
    @GetMapping("/new")
    public String newPersonView(@ModelAttribute("person") Person person) {
        return "people/newPersonView";
    }

    @PostMapping()
    public String newPersonSet(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/newPersonView";
        }
        peopleService.save(person);
        return "redirect:/people";
    }


    //Update
    @GetMapping("/{id}/edit")
    public String editPersonView(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.getPersonById(id).orElse(null));
        return "people/editPersonView";
    }

    @PatchMapping("/{id}")
    public String editPerson(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/editPersonView";
        }
        peopleService.update(id, person);
        return "redirect:/people/{id}";
    }


    //Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
