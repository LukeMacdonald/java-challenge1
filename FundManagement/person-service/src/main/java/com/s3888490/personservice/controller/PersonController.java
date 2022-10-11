package com.s3888490.personservice.controller;

import com.s3888490.personservice.exception.ApiRequestException;
import com.s3888490.personservice.model.Person;
import com.s3888490.personservice.service.PersonService;
import lombok.RequiredArgsConstructor;
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
            throw new ApiRequestException("ID already exists");
        }
    }
    @GetMapping("/person/{id}")
    public ResponseEntity<?> getPerson(@PathVariable("id") Long id){
        Person person = personService.getPersonByID(id);
        if(person!=null){
            return ResponseEntity.ok().body(person);
        }
        else{
            throw new ApiRequestException("No individual Identified by that id");
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> getPeople(){
        List<Person> people = personService.getAllPeople();
        if(people.isEmpty()){
            throw new ApiRequestException("No Users exist!");
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
            throw new ApiRequestException("No Person exist to update!");
        }
    }
    @DeleteMapping("/")
    public ResponseEntity<?> deletePerson(@RequestBody Person person){
        String deleted = personService.deletePerson(person);
        if(deleted != null){
            return ResponseEntity.ok().body(deleted);
        }
        else{
            throw new ApiRequestException("No Person exist to delete!");
        }

    }


}
