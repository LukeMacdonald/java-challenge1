package com.s3888490.personservice.dao;
import com.s3888490.personservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findPersonById(Long id);

}
