package com.squirrel.courses.service.course;

import com.squirrel.courses.dataaccess.model.Course;
import java.util.List;

public interface ICourseService {
    public List<Course> getLecturerCourses(String lecturer);
}
