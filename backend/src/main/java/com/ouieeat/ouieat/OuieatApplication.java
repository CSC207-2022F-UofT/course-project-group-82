package com.ouieeat.ouieat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class OuieatApplication {

  public static void main(String[] args) {
    SpringApplication.run(OuieatApplication.class, args);
  }
}
