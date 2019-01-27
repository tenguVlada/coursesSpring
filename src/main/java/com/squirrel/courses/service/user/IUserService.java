package com.squirrel.courses.service.user;

import com.squirrel.courses.dataaccess.model.AppUser;

public interface IUserService {
    boolean addUser(AppUser appUser);
    AppUser findByLogin(String login);
}
