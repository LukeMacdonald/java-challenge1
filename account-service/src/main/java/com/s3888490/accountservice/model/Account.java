package com.s3888490.accountservice.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Account {

    @Id
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "account_type", columnDefinition = "ENUM('TermInvestment', 'Loan', 'Saving')")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "balance")
    private String balance;

    @Column(name = "date")
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAccountType() {
        if (this.accountType == AccountType.Loan) {
            return "Loan";
        }
        else if(this.accountType == AccountType.Saving){
            return "Saving";
        }
        else {
            return "Term Investment";
        }
    }

    public void setAccountType(String accountType) {
        String upper = accountType.toUpperCase().trim();
        switch (upper){
            case("TERMINVESTMENT"):
            case("TERM INVESTMENT"):
                this.accountType = AccountType.TermInvestment;
                break;
            case("LOAN"):
                this.accountType = AccountType.Loan;
                break;
            case("SAVING"):
                this.accountType = AccountType.Saving;
                break;
            default:
                this.accountType= null;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date=date;
    }
}
