package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Test;

public interface ITestDAO {

    boolean addTest(Test test);
    boolean deleteTest(int testId);
    int getLastTest();
}
