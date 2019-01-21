package com.squirrel.courses.service;

import com.squirrel.courses.dataaccess.dao.user.UserDAO;
import com.squirrel.courses.dataaccess.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ServiceImplementationExample implements ServiceInterfaceExample {

    @Override
    public String getUserDescription(String login) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("database/SpringModule.xml");
        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
        User user = userDAO.findByLogin("user");

        return user.getDescription();
    }
}
