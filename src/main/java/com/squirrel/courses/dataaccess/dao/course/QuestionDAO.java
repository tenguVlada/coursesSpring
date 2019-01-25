package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDAO implements IQuestionDAO{

    @Override
    public boolean addQuestion(Question quest) {
        String sql = "INSERT INTO question(test, q_text, points, isOpen) VALUES(?, ?, ?, ?)";
        Connection conn = null;

        try {
            //conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, quest.getTest());
            ps.setString(2, quest.getQuestText());
            ps.setInt(3, quest.getPoints());
            ps.setByte(4, quest.getIsOpen());

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
    public int getLastQuestion() {
        int questId;

        String sql = "SELECT MAX(id) as questId FROM question";
        Connection conn = null;

        try {
            //conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            questId = 0;
            if (rs.next()) {
                questId = rs.getInt("questId");
            }
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

        return questId;
    }
}
