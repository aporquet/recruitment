package com.recruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Recruitment {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("driver loaded");
        } catch(ClassNotFoundException e) {
            System.out.println("Couldn't find Gum");
        }

        SpringApplication.run(Recruitment.class, args);
    }
}
