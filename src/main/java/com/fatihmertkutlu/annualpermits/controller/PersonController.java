package com.fatihmertkutlu.annualpermits.controller;

import com.fatihmertkutlu.annualpermits.dto.OperationResult;
import com.fatihmertkutlu.annualpermits.dto.PersonDto;
import com.fatihmertkutlu.annualpermits.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping(value ="api/person")
public class PersonController {

    private final PersonService personService;

    @PostMapping(value = "newUser")
    public ResponseEntity<OperationResult> insertNewUser(@RequestBody PersonDto personDto){
        log.info("insertNewUser started for {}" , personDto.getFirstName() );
        OperationResult response = personService.insertNewPerson(personDto);
        log.info("insertNewUser finished");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
