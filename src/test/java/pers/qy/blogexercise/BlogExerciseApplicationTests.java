package pers.qy.blogexercise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.qy.blogexercise.service.AccountService;

@SpringBootTest
class BlogExerciseApplicationTests {
    @Autowired
    AccountService accountService;

    @Test
    void createAccount() {
        accountService.createAccount("admin", "admin", "admin");
    }

}
