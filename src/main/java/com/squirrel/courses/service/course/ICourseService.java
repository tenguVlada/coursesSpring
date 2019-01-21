package com.squirrel.courses.service.course;

import com.squirrel.courses.dataaccess.model.Course;
import java.util.List;

public interface ICourseService {
    List<Course> getLecturerCourses(String lecturer);
    List<String> getLecturerCourseThemes(String lecturer);
}
