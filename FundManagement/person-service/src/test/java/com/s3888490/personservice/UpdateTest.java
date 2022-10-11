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

@SpringBootTest
@AutoConfigureMockMvc
@DataJpaTest
public class UpdateTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;
    private Person mockPerson;

    @BeforeEach
    public void setUp() {
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
    public void returnsUpdatedUser() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(mockPerson);

        Mockito.when(personService.updatePerson(Mockito.any(Person.class))).thenReturn(mockPerson);

        mockMvc.perform(MockMvcRequestBuilders.put("/persons/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));

    }
    @Test
    public void returnsNull() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(mockPerson);

        Mockito.when(personService.savePerson(Mockito.any(Person.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/persons/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
