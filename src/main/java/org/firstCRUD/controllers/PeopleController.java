package org.firstCRUD.controllers;

import org.firstCRUD.DAO.PersonDAO;
import org.firstCRUD.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
    @RequestMapping("/people")
    public class PeopleController{

        private  final PersonDAO personDAO;

        @Autowired
        public PeopleController(PersonDAO personDAO) {
            this.personDAO = personDAO;
        }


        @GetMapping()
    public String index(Model model){

        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        // getting 1 person by id from DAO
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }
//        //do it in a simple way
//        @GetMapping("/new")
//        public String newPerson(Model model){
//            model.addAttribute("person", new Person());
//            return "people/new";
//        }

    //do it in a simple way
    @GetMapping("/new")
       public String newPerson(@ModelAttribute("person") Person person){
           return "people/new";
       }

        @PostMapping()
        public String create(@ModelAttribute("person") Person person){
            personDAO.save(person);
            return "redirect:/people";
        }

    }


