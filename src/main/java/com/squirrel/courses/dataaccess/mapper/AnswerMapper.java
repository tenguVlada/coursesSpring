package com.squirrel.courses.dataaccess.mapper;

import com.squirrel.courses.dataaccess.model.Answer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper<Answer> {
    public static final String BASE_SQL = "SELECT id, question, a_text, coefficient FROM answer";
    public static final String INSERT_SQL = "INSERT INTO answer(question, a_text, coefficient)";

    @Override
    public Answer mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        int question = resultSet.getInt("question");
        String ansText = resultSet.getString("a_text");
        double coef = resultSet.getDouble("coefficient");

        return new Answer(id, question, ansText, coef);
    }
}
