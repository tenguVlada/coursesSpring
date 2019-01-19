package com.squirrel.courses.dataaccess.dao;

import com.squirrel.courses.dataaccess.model.User;

public interface UserDAO {
    boolean addUser(User user);
    User findByLogin(String login);
}
