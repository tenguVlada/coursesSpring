package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Question;

public interface IQuestionDAO {

    boolean addQuestion(Question quest);
    int getLastQuestion();
}
