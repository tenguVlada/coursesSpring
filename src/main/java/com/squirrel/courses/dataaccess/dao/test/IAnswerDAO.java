package com.squirrel.courses.dataaccess.dao.test;

import com.squirrel.courses.dataaccess.model.Answer;

import java.util.List;

public interface IAnswerDAO {
    List<Answer> findAnswersByQuestion(int quest);
    boolean addAnswer(Answer answer);
}

