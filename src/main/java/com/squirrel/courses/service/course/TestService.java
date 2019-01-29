package com.squirrel.courses.service.course;

import com.squirrel.courses.dataaccess.dao.course.IAnswerDAO;
import com.squirrel.courses.dataaccess.dao.course.IQuestionDAO;
import com.squirrel.courses.dataaccess.dao.course.ITestDAO;
import com.squirrel.courses.dataaccess.model.Answer;
import com.squirrel.courses.dataaccess.model.Question;
import com.squirrel.courses.dataaccess.model.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestService implements ITestService{

    private IAnswerDAO answerDAO;
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

    @Autowired
    public void setAnswerDAO(IAnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    @Override
    public Test findTestByLesson(int lesson) {
        return testDAO.findTestByLesson(lesson);
    }

    @Override
    public Test findExamByCourse(int course) {
        return testDAO.findExamByCourse(course);
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
    public List<Question> findQuestionsByTest(int test) {
        return questionDAO.findQuestionsByTest(test);
    }

    @Override
    public boolean addQuestion(Question quest) {
        return questionDAO.addQuestion(quest);
    }

    @Override
    public int getLastQuestion() {
        return questionDAO.getLastQuestion();
    }

    @Override
    public List<Answer> findAnswersByQuestion(int quest){
        return answerDAO.findAnswersByQuestion(quest);
    }

    @Override
    public boolean addAnswer(Answer ans) {
        return answerDAO.addAnswer(ans);
    }
}
