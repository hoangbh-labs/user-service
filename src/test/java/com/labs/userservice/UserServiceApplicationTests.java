package com.labs.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class UserServiceApplicationTests {

  @Test
  void contextLoads() {
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    System.out.println(new BCryptPasswordEncoder().encode("admin"));
    System.out.println(
        encoder.matches("admin",
            "$2a$10$Dow1sK8HqQx1Yh1gW8rQ5e5F6QnWk8T9XGq9qWZz8r8G1m1gYkK8C")
    );
  }

}
