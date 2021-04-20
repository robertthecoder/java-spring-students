package com.example.demo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {
    // Bean will make this code run when starting server
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args-> {

            Student mariam = new Student(
                        1L,
                        "Mariam",

                        LocalDate.of(2000, JANUARY, 23),
                        "mariam@gmail.com"
                );

            Student sarah = new Student(1L, "Sarah G", LocalDate.of(1996, Month.APRIL, 5) , "Sarah.G@gmail.com" );

            Student bob = new Student(2L, "Bob G", LocalDate.of(1993, Month.APRIL, 5), "Bob.G@gmail.com");


            repository.saveAll(List.of(sarah, bob));

        };

    }


}
