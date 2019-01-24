package com.squirrel.courses.dataaccess.dao.user;

import com.squirrel.courses.dataaccess.model.AppUser;

public interface UserDAO {

    boolean addUser(AppUser appUser);

    AppUser findByLogin(String login);
}
