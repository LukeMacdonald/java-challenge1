package com.s3888490.accountservice.controller;

import com.s3888490.accountservice.exception.ApiRequestException;
import com.s3888490.accountservice.model.Account;
import com.s3888490.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            throw new ApiRequestException("Account with same number already exists");
        }
    }
    @GetMapping("/account/{id}")
    public ResponseEntity<?> getPerson(@PathVariable("id") Long id){
        List<Account> accounts = accountService.getAccountByID(id);
        if(accounts.isEmpty()){
            throw new ApiRequestException("No accounts exists for user with this id!");
        }
        else{
            return ResponseEntity.ok().body(accounts);
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> getPeople(){
        List<Account> accounts = accountService.getAllAccounts();
        if(accounts.isEmpty()){
            throw new ApiRequestException("No accounts exist");
        }
        else{
            return ResponseEntity.ok().body(accounts);
        }
    }
    @PutMapping("/")
    public ResponseEntity<?> updateAccount(@RequestBody Account account){
        Account updatedAccount = accountService.updateAccount(account);
        if(updatedAccount  != null){
            return ResponseEntity.ok().body(updatedAccount);
        }
        else{
            throw new ApiRequestException("No Account exists to update!");
        }
    }
    @DeleteMapping("/")
    public ResponseEntity<?> deleteAccount(@RequestBody Account account){
        String deleted =  accountService.deleteAccount(account);
        if(deleted != null){
            return ResponseEntity.ok().body(deleted);
        }
        else{
            throw new ApiRequestException("No Account exists to delete!");
        }

    }

}
