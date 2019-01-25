package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Question;
import com.squirrel.courses.dataaccess.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;

public class QuestionDAO implements IQuestionDAO{

    @Override
    public boolean addQuestion(Question quest) {
        String sql = QuestionMapper.INSERT_SQL + " VALUES(?, ?, ?, ?)";

        Object [] params = new Object[]{quest.getTest(), quest.getQuestText(), quest.getPoints(), quest.getIsOpen()};
        try{
            this.getJdbcTemplate().update(sql, params);
            return true;
        }
        catch (DataAccessException e){
            return false;
        }
    }

    @Override
    public int getLastQuestion() {
        String sql = "SELECT MAX(id) as testId FROM test";

        QuestionMapper mapper = new QuestionMapper();
        try {
            int testId = this.getJdbcTemplate().queryForObject(sql, mapper);        //возможно неправильно
            return testId;
        } catch (EmptyResultDataAccessException e) {
            return -1;
        }
    }
}
