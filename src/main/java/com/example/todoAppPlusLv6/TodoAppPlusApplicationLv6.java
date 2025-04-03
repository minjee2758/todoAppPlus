package com.example.todoAppPlusLv6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TodoAppPlusApplicationLv6 {

    public static void main(String[] args) {
        SpringApplication.run(TodoAppPlusApplicationLv6.class, args);
    }

}
