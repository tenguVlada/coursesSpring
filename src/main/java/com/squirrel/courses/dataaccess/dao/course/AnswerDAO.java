package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;


@Repository
@Transactional
public class AnswerDAO extends JdbcDaoSupport implements IAnswerDAO {

    @Autowired
    public AnswerDAO(DataSource dataSource){
        this.setDataSource(dataSource);
    }

    @Override
    public boolean addAnswer(Answer answer){
        String sql = "INSERT INTO answer(question, a_text, coefficient) VALUES(?, ?, ?)";
        Connection conn = null;

        try {
            //conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, answer.getQuestion());
            ps.setString(2, answer.getAnsText());
            ps.setDouble(3, answer.getCoef());

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
}
