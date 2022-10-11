package com.s3888490.accountservice.service;

import com.s3888490.accountservice.dao.AccountRepository;
import com.s3888490.accountservice.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account saveAccount(Account account) {
        if(accountRepository.findAccountByAccountNumber(account.getAccountNumber())==null){
            return  accountRepository.save(account);
        }
        return null;
    }
}
