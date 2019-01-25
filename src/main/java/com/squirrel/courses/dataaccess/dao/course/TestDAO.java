package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Test;
import com.squirrel.courses.dataaccess.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;

public class TestDAO implements ITestDAO{

    @Override
    public boolean addTest(Test test) {
        String sql = TestMapper.INSERT_SQL + " VALUES(?, ?, ?)";

        Object [] params = new Object[]{test.getLesson(), test.getEvaluation(), test.getIsExam()};
        try{
            this.getJdbcTemplate().update(sql, params);
            return true;
        }
        catch (DataAccessException e){
            return false;
        }
    }

    public boolean deleteTest(int testId){
        String sql = TestMapper.DELETE_SQL + " WHERE id = ?";

        Object[] params = new Object[]{testId};
        try{
            this.getJdbcTemplate().update(sql, params);
            return true;
        }
        catch (DataAccessException e){
            return false;
        }
    }

    public int getLastTest() {
        String sql = "SELECT MAX(id) as testId FROM test";

        TestMapper mapper = new TestMapper();
        try {
            int testId = this.getJdbcTemplate().queryForObject(sql, mapper);        //возможно неправильно
            return testId;
        } catch (EmptyResultDataAccessException e) {
            return -1;
        }
    }
}
