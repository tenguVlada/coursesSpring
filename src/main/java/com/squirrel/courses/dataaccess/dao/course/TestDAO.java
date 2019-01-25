package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDAO implements ITestDAO{

    @Override
    public boolean addTest(Test test) {
        String sql = "INSERT INTO test(lesson, evaluation, isExam) VALUES(?, ?, ?)";
        Connection conn = null;

        try {
            //conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, test.getLesson());
            ps.setInt(2, test.getEvaluation());
            ps.setByte(3, test.getIsExam());

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

    public boolean deleteTest(int testId){
        String sql = "DELETE FROM test WHERE id = ?";
        Connection conn = null;

        try {
            //conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, testId);

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

    public int getLastTest() {
        int testId;

        String sql = "SELECT MAX(id) as testId FROM test";
        Connection conn = null;

        try {
            //conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            testId = 0;
            if (rs.next()) {
                testId = rs.getInt("testId");
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

        return testId;
    }
}
