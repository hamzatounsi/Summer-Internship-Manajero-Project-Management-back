package com.example.back_asd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.back_asd.repositories")
public class BackAsdApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackAsdApplication.class, args);
    }

}
