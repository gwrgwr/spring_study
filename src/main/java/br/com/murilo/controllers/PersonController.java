package br.com.murilo.controllers;

import br.com.murilo.entities.Person;
import br.com.murilo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Person> findAll() {
        return personService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Person findOne(@PathVariable UUID id) {
        return personService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Person save(Person person) {
        return personService.save(person);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public Person update(Person person) {
        return personService.update(person);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}",produces = "application/json")
    public Person delete(@PathVariable UUID id) {
        return personService.delete(id);
    }
}
