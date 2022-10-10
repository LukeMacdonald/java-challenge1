package com.s3888490.personservice.controller;

import com.s3888490.personservice.model.Person;
import com.s3888490.personservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @PostMapping("/")
    public ResponseEntity<?> savePerson(@RequestBody Person person){
        Person newPerson = personService.savePerson(person);
        if(newPerson != null){
            return ResponseEntity.ok().body(newPerson);
        }
        else{
            return ResponseEntity.badRequest().body("ID already exists");
        }
    }


}
