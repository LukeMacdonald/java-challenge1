package com.s3888490.accountservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.s3888490.accountservice.controller.AccountController;
import com.s3888490.accountservice.model.Account;
import com.s3888490.accountservice.service.AccountService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DeleteTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private Account mockAccount;

    @BeforeEach
    public void setUp(){
        mockAccount = new Account();
        mockAccount.setId(5L);
        mockAccount.setAccountType("Term Investment");
        mockAccount.setAccountName("J Smith");
        mockAccount.setAccountNumber("23456788");
        mockAccount.setDate("2022-10-11");
        mockAccount.setBalance("350");
    }


    @Test
    public void deleteSuccessful() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(mockAccount);

        Mockito.when(accountService.deleteAccount(Mockito.any(Account.class))).thenReturn("Deleted");

        mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/")
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
        String json = ow.writeValueAsString(mockAccount);

        Mockito.when(accountService.deleteAccount(Mockito.any(Account.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
