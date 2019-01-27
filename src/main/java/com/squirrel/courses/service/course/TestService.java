package com.squirrel.courses.service.course;

import com.squirrel.courses.dataaccess.dao.course.ICourseDAO;
import com.squirrel.courses.dataaccess.dao.course.IQuestionDAO;
import com.squirrel.courses.dataaccess.dao.course.ITestDAO;
import com.squirrel.courses.dataaccess.model.Question;
import com.squirrel.courses.dataaccess.model.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestService implements ITestService{

    private IQuestionDAO questionDAO;
    private ITestDAO testDAO;

    @Autowired
    public void setQuestionDAO(IQuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Autowired
    public void setTestDAO(ITestDAO testDAO) {
        this.testDAO = testDAO;
    }

    @Override
    public boolean addTest(Test test) {
        return testDAO.addTest(test);
    }

    @Override
    public boolean deleteTest(int testId) {
        return testDAO.deleteTest(testId);
    }

    @Override
    public int getLastTest() {
        return testDAO.getLastTest();
    }


    @Override
    public boolean addQuestion(Question quest) {
        return questionDAO.addQuestion(quest);
    }

    @Override
    public int getLastQuestion() {
        return questionDAO.getLastQuestion();
    }
}
