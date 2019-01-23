package com.squirrel.courses.dataaccess.dao.user;

import com.squirrel.courses.dataaccess.model.User;
import com.squirrel.courses.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
@Transactional
public class JdbcUserDAO extends JdbcDaoSupport implements UserDAO{

    @Autowired
    public JdbcUserDAO(DataSource dataSource){
        this.setDataSource(dataSource);
    }

    @Override
    public boolean addUser(User user) {

        String sql = UserMapper.INSERT_SQL + " VALUES(?, ?, ?, ?, ?)";
        Object [] params = new Object[]{user.getLogin(), user.getHashPass(), user.getRole(), user.getUserName(), user.getDescription()};
        try{
            this.getJdbcTemplate().update(sql, params);
            return true;
        }
        catch (DataAccessException e){
            return false;
        }
    }

    @Override
    public User findByLogin(String login) {

        String sql = UserMapper.BASE_SQL + " WHERE login = ?";

        Object[] params = new Object[]{login};
        UserMapper mapper = new UserMapper();
        try {
            User user = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /*String sql = "INSERT INTO `USER` " +
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
    }*/

        /*String sql = "SELECT * FROM `USER` WHERE login = ?";

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
        }*/
}
