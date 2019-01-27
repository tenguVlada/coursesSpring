package com.squirrel.courses.service.course;

import com.squirrel.courses.dataaccess.model.Question;
import com.squirrel.courses.dataaccess.model.Test;

public interface ITestService {
    boolean addTest(Test test);
    boolean deleteTest(int testId);
    int getLastTest();

    boolean addQuestion(Question quest);
    int getLastQuestion();

}
