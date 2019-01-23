package com.squirrel.courses.mapper;

import com.squirrel.courses.dataaccess.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    public static final String BASE_SQL = "SELECT login, hash_pass, role, user_name, description FROM user";
    public static final String INSERT_SQL = "INSERT INTO user (login, hash_pass, role, user_name, description)";

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        String login = resultSet.getString("login");
        String hashPass = resultSet.getString("hash_pass");
        String role = resultSet.getString("role");
        String userName = resultSet.getString("userName");
        String description = resultSet.getString("description");
        return new User(login, hashPass, role, userName, description);
    }
}
