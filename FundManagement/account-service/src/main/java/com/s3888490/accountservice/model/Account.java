package com.s3888490.accountservice.model;

import com.s3888490.accountservice.exception.ApiRequestException;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(schema = "account")
public class Account {

    @Id
    @Column(name = "account_number", nullable = false)
    private Long accountNumber;
    //@Id
    @Column(name = "id")
    private Long id;

    @Column(name = "account_type")
    private String accountType;

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
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
        String upper = accountType.toUpperCase().trim();
        switch (upper){
            case("TERMINVESTMENT"):
            case("TERM INVESTMENT"):
                this.accountType = "Term Investment";
                break;
            case("LOAN"):
                this.accountType = "Loan";
                break;
            case("SAVING"):
                this.accountType = "Saving";
                break;
            default:
                throw new ApiRequestException("Invalid account type!");
        }
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
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
    public void setDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate dateTime = LocalDate.parse(date);
        if(dateTime.equals(LocalDate.now())){
            this.date=date;
        }
        else{
            throw new ApiRequestException("Invalid date (Must be today!)");
        }

    }
}
