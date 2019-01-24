package com.squirrel.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoursesApplication {

    public static void main(String[] args) {

        /*AppUser user = new AppUser("user", "hashPass","lecturer", "userName", "it's me");
        userDAO.addUser(user);*/



        SpringApplication.run(CoursesApplication.class, args);
    }

}

