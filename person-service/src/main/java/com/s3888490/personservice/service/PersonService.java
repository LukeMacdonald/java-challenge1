package com.s3888490.personservice.service;

import com.s3888490.personservice.dao.PersonRepository;
import com.s3888490.personservice.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person savePerson(Person person) {
        if(personRepository.findPersonById(person.getId())==null){
            return personRepository.save(person);
        }
        return null;
    }
    public Person getPersonByID(Long id){
        return personRepository.findPersonById(id);
    }
    public List<Person> getAllPeople(){
        return personRepository.findAll();
    }
    public Person updatePerson(Person person){
        if(personRepository.findPersonById(person.getId()) != null){
            return personRepository.save(person);
        }
        else{
            return null;
        }
    }


}
