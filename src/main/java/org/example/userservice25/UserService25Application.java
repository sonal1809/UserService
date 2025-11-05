package org.example.userservice25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserService25Application {

    public static void main(String[] args) {
        SpringApplication.run(UserService25Application.class, args);
    }

}
