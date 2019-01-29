package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Answer;
import com.squirrel.courses.dataaccess.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.util.List;

@Repository
@javax.transaction.Transactional
public class AnswerDAO extends JdbcDaoSupport implements IAnswerDAO {

    @Autowired
    public AnswerDAO(DataSource dataSource){
        this.setDataSource(dataSource);
    }

    @Override
    public List<Answer> findAnswersByQuestion(int quest) {
        System.out.println("!!!!!!!!!!! " + quest);
        String sql = AnswerMapper.BASE_SQL + " WHERE question = ?";

        Object [] params = new Object[]{quest};
        AnswerMapper mapper = new AnswerMapper();

        try {
            List<Answer> answers = getJdbcTemplate().query(sql, params, mapper);                                //возможно неправильно
            return answers;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public boolean addAnswer(Answer answer){
        String sql = AnswerMapper.INSERT_SQL + " VALUES(?, ?, ?)";

        Object [] params = new Object[]{answer.getQuestion(), answer.getAnsText(), answer.getCoef()};
        try{
            this.getJdbcTemplate().update(sql, params);
            return true;
        }
        catch (DataAccessException e){
            return false;
        }
    }
}
