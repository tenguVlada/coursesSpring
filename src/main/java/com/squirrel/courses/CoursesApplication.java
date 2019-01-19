package com.squirrel.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.squirrel.courses.dataaccess.dao.UserDAO;
import com.squirrel.courses.dataaccess.model.User;

@SpringBootApplication
public class CoursesApplication {

    public static void main(String[] args) {

        /*User user = new User("user", "hashPass","lecturer", "userName", "it's me");
        userDAO.addUser(user);*/



        SpringApplication.run(CoursesApplication.class, args);
    }

}

