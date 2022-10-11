package com.s3888490.personservice;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.s3888490.personservice.controller.PersonController;
import com.s3888490.personservice.dao.PersonRepository;
import com.s3888490.personservice.model.Person;
import com.s3888490.personservice.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value = PersonController.class)
public class DeleteTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

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
    public void deleteSuccessful() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(mockPerson);

        Mockito.when(personService.deletePerson(Mockito.any(Person.class))).thenReturn("Deleted");

        mockMvc.perform(MockMvcRequestBuilders.delete("/persons/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted"));

    }
    @Test
    public void deleteFailure() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(mockPerson);

        Mockito.when(personService.deletePerson(Mockito.any(Person.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.delete("/persons/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }


}