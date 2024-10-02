package br.com.murilo.services;

import br.com.murilo.controllers.PersonController;
import br.com.murilo.data.vo.v1.PersonVO;
import br.com.murilo.entities.Person;
import br.com.murilo.exceptions.NotFoundException;
import br.com.murilo.mapper.DozerMapper;
import br.com.murilo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository personRepository;

    public List<PersonVO> findAll() {
        logger.info("Finding all persons");
        List<PersonVO> listVo = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        listVo.forEach(p -> p.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).findOne(p.getUserId())).withSelfRel()));
        return listVo;
    }

    public PersonVO findOne(UUID id) {
        logger.info("Finding person by id: " + id);
        PersonVO vo = DozerMapper.parseObject(personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found")), PersonVO.class);
        vo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).findOne(id)).withSelfRel());
        return vo;
    }

    public PersonVO save(PersonVO person) {
        logger.info("Saving person: " + person);
        Person entity = DozerMapper.parseObject(person, Person.class);
        PersonVO vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).save(vo)).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating person");
        Person entity = personRepository.findById(person.getUserId()).orElseThrow(() -> new NotFoundException("Person not found"));
        entity.setEmail(person.getEmail());
        entity.setName(person.getName());
        entity.setPassword(person.getPassword());
        PersonVO vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).update(vo)).withSelfRel());
        return vo;
    }

    public PersonVO delete(UUID id) {
        logger.info("Deleting person by id: " + id);
        Person entity = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found"));
        personRepository.delete(entity);
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).delete(id)).withSelfRel());
        return vo;
    }
}
