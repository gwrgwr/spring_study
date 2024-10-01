package br.com.murilo.controllers;

import br.com.murilo.data.vo.v1.PersonVO;
import br.com.murilo.entities.Person;
import br.com.murilo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/persons/v1")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(produces = "application/json")
    public List<PersonVO> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public PersonVO findOne(@PathVariable UUID id) {
        return personService.findOne(id);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public PersonVO save(@RequestBody PersonVO person) {
        return personService.save(person);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public PersonVO update(PersonVO person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
