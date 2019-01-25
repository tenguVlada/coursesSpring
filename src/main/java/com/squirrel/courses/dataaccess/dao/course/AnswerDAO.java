package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnswerDAO implements IAnswerDAO {

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
