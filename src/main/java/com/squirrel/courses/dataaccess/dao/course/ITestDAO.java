package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Test;

import java.util.List;

public interface ITestDAO {
    Test findTestById(int id);
    Test findTestByLesson(int lesson);
    Test findExamByCourse(int course);
    boolean addTest(Test test);
    boolean deleteTest(int testId);
    int getLastTest();
}
