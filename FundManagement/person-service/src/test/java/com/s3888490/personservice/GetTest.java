package com.s3888490.personservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.s3888490.personservice.controller.PersonController;
import com.s3888490.personservice.model.Person;
import com.s3888490.personservice.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@DataJpaTest
public class GetTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private Person mockPerson;

    @BeforeEach
    public void setUp(){
        mockPerson = new Person();
        mockPerson.setId(1L);
        mockPerson.setAge("25");
        mockPerson.setAddress("506 Lonsdale St, Melbourne");
        mockPerson.setJob("Programmer");
        mockPerson.setName("John Doe");
        mockPerson.setPhoneNumber("1300655503");
        mockPerson.setEmail("johndoe@gmail.com");
        mockPerson.setPostcode("3000");
    }
    @Test
    public void returnsUser() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(mockPerson);

        Mockito.when(personService.getPersonByID(Mockito.anyLong())).thenReturn(mockPerson);

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/person/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }
    @Test
    public void returnsNull() throws Exception {

        Mockito.when(personService.getPersonByID(Mockito.anyLong())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/person/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
    @Test
    public void returnsUsers() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(mockPerson);

        Mockito.when(personService.getPersonByID(Mockito.anyLong())).thenReturn(mockPerson);

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/person/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }
    @Test
    public void returnsAllPeople() throws Exception {

        List<Person> mockPeople = new ArrayList<>();

        mockPeople.add(mockPerson);
        mockPeople.add(mockPerson);
        mockPeople.add(mockPerson);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(mockPeople);

        Mockito.when(personService.getAllPeople()).thenReturn(mockPeople);

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));

    }
    @Test
    public void returnsEmpty() throws Exception {

        Mockito.when(personService.getPersonByID(Mockito.anyLong())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/persons/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
