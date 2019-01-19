package com.squirrel.courses.dataaccess.dao;

import com.squirrel.courses.dataaccess.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class JdbcUserDAO implements UserDAO{

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO `USER` " +
                "(login, hash_pass, role, user_name, description) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getHashPass());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getUserName());
            ps.setString(5, user.getDescription());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return true;
    }

    @Override
    public User findByLogin(String login) {
        String sql = "SELECT * FROM `USER` WHERE login = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login);

            User user = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getString("login"),
                        rs.getString("hash_pass"),
                        rs.getString("role"),
                        rs.getString("user_name"),
                        rs.getString("description")
                );
            }
            rs.close();
            ps.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }


}
