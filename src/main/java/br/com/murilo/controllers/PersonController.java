package br.com.murilo.controllers;

import br.com.murilo.data.vo.v1.PersonVO;
import br.com.murilo.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/persons/v1")
@Tag(name = "Person", description = "Endpoint for managing person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(produces = {"application/json", MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Find all persons", description = "Find all persons in the database", tags = {"Person"}, responses = {
            @ApiResponse(responseCode = "200", description = "Persons found", content = {
                    @Content(mediaType = "application/json"),
                    @Content(mediaType = "application/xml")
            }),
            @ApiResponse(responseCode = "404", description = "Persons not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<PersonVO> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}", produces = {"application/json", MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Find one person", description = "Find one person in the database", tags = {"Person"}, responses = {
            @ApiResponse(responseCode = "200", description = "Person found", content = {
                    @Content(mediaType = "application/json"),
                    @Content(mediaType = "application/xml")
            }),
            @ApiResponse(responseCode = "404", description = "Persons not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public PersonVO findOne(@PathVariable UUID id) {
        return personService.findOne(id);
    }

    @PostMapping(produces = {"application/json", MediaType.APPLICATION_XML_VALUE}, consumes = {"application/json", MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Create one person", description = "Create one person in the database", tags = {"Person"}, responses = {
            @ApiResponse(responseCode = "201", description = "Person created", content = {
                    @Content(mediaType = "application/json"),
                    @Content(mediaType = "application/xml")
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public PersonVO save(@RequestBody PersonVO person) {
        return personService.save(person);
    }

    @PutMapping(produces = {"application/json", MediaType.APPLICATION_XML_VALUE}, consumes = {"application/json", MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Update one person", description = "Update one person in the database", tags = {"Person"}, responses = {
            @ApiResponse(responseCode = "201", description = "Person updated", content = {
                    @Content(mediaType = "application/json"),
                    @Content(mediaType = "application/xml")
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Person Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public PersonVO update(@RequestBody PersonVO person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json", MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Delete one person", description = "Delete one person in the database", tags = {"Person"}, responses = {
            @ApiResponse(responseCode = "204", description = "Person deleted", content = {
                    @Content(mediaType = "application/json"),
                    @Content(mediaType = "application/xml")
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Person Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}