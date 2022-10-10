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
    @GetMapping("/person/{id}")
    public ResponseEntity<?> getPerson(@PathVariable("id") Long id){
        Person person = personService.getPersonByID(id);
        if(person!=null){
            return ResponseEntity.ok().body(person);
        }
        else{
            return ResponseEntity.badRequest().body("No individual Identified by that id");
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> getPeople(){
        List<Person> people = personService.getAllPeople();
        if(people.isEmpty()){
            return ResponseEntity.badRequest().body("No Users exist!");
        }
        else{
            return ResponseEntity.ok().body(people);
        }
    }
    @PutMapping("/")
    public ResponseEntity<?> updatePerson(@RequestBody Person person){
        Person updatedPerson = personService.updatePerson(person);
        if(updatedPerson != null){
            return ResponseEntity.ok().body(updatedPerson );
        }
        else{
            return ResponseEntity.badRequest().body("No Users exist to update!");
        }
    }


}
