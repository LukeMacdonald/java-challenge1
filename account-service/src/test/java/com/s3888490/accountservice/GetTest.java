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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(value = AccountController.class)
public class GetTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private Account mockAccount;

    @BeforeEach
    public void setUp() {
        mockAccount = new Account();
        mockAccount.setId(1L);
        mockAccount.setAccountType("Term Investment");
        mockAccount.setAccountName("J Smith");
        mockAccount.setAccountNumber("23456788");
        mockAccount.setDate("2022-10-11");
        mockAccount.setBalance("350");
    }

    @Test
    public void getAccountsByIDSuccessful() throws Exception {

        List<Account> mockAccounts = new ArrayList<>();

        mockAccounts.add(mockAccount);
        mockAccounts.add(mockAccount);
        mockAccounts.add(mockAccount);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(mockAccounts);

        Mockito.when(accountService.getAccountByID(Mockito.anyLong())).thenReturn(mockAccounts);

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/account/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));

    }

    @Test
    public void getAccountsByIDEmpty() throws Exception {

        Mockito.when(accountService.getAccountByID(Mockito.anyLong())).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/account/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void getAllAccountsSuccessful() throws Exception {

        List<Account> mockAccounts = new ArrayList<>();

        mockAccounts.add(mockAccount);
        mockAccounts.add(mockAccount);
        mockAccounts.add(mockAccount);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(mockAccounts);

        Mockito.when(accountService.getAllAccounts()).thenReturn(mockAccounts);

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json));

    }

    @Test
    public void getAllAccountsEmpty() throws Exception {

        Mockito.when(accountService.getAllAccounts()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
