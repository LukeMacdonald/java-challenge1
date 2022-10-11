package com.s3888490.accountservice.controller;

import com.s3888490.accountservice.model.Account;
import com.s3888490.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {


    private final AccountService accountService;

    @PostMapping("/")
    public ResponseEntity<?> savePerson(@RequestBody Account account){
        Account newAccount = accountService.saveAccount(account);
        if(newAccount != null){
            return ResponseEntity.ok().body(newAccount);
        }
        else{
            return ResponseEntity.badRequest().body("Account with same number already exists");
        }
    }

}
