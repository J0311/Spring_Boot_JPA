package com.example.amigosDemo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 Here we will use CommandLineRunner to create new Student object.
 Removal of Long id because these will be generated via database
 auto-incrementation
 */

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mariam = new Student(
                    "Mariam",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            Student alex = new Student(
                    "Alex",
                    "alex.@gmail.com",
                    LocalDate.of(2004, Month.JANUARY, 5)

            );

            /**
            Now we save our new students into our database by invoking repository
             */

            repository.saveAll(
                    List.of(mariam, alex)
            );
        };
    }
}
