package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Question;

import java.util.List;

public interface IQuestionDAO {

    List<Question> findQuestionsByTest(int test);
    boolean addQuestion(Question quest);
    int getLastQuestion();
}
