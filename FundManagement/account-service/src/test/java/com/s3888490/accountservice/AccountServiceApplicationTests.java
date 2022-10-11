package com.s3888490.accountservice;

import com.s3888490.accountservice.controller.AccountController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AccountServiceApplicationTests {

    @Autowired
    AccountController accountController;
    @Test
    public void contextLoads() {
        Assertions.assertThat(accountController);
    }

}
