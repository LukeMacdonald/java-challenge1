package com.s3888490.accountservice.model;

public enum AccountType {
    TermInvestment(1),
    Loan(2),
    Saving(3);

    private final int value;

    AccountType(final int value) {
        this.value = value;
    }


}
