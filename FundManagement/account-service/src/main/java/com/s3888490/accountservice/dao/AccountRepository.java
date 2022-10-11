package com.s3888490.accountservice.dao;

import com.s3888490.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findAccountById(Long id);

    Account findAccountByAccountNumber(String accountNumber);

}
