package br.com.murilo.services;

import br.com.murilo.entities.Person;
import br.com.murilo.exceptions.NotFoundException;
import br.com.murilo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        logger.info("Finding all persons");
        return personRepository.findAll();
    }

    public Person findOne(UUID id) {
        logger.info("Finding person by id: " + id);
        return personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found"));
    }

    public Person save(Person person) {
        logger.info("Saving person: " + person);
        person.setId(UUID.randomUUID());
        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating person");
        Person entity =  personRepository.findById(person.getId()).orElseThrow(() -> new NotFoundException("Person not found"));
        entity.setEmail(person.getEmail());
        entity.setName(person.getName());
        entity.setPassword(person.getPassword());

        return personRepository.save(person);
    }

    public Person delete(UUID id) {
        logger.info("Deleting person by id: " + id);
        Person entity = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found"));
        personRepository.delete(entity);
        return entity;
    }
}
