package com.example.todoAppPlusLv6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TodoAppPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoAppPlusApplication.class, args);
    }

}
