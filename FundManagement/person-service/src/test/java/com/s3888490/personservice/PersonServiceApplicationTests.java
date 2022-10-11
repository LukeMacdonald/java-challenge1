package com.s3888490.personservice;

import com.s3888490.personservice.controller.PersonController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonServiceApplicationTests {

    @Autowired
    PersonController personController;
    @Test
    public void contextLoads() {
        Assertions.assertThat(personController);
    }


}
