package com.squirrel.courses.service.course;

import com.squirrel.courses.dataaccess.model.Course;
import java.util.List;

public interface ICourseService {
    boolean addCourse(Course course);
    boolean editCourse(Course course);
    boolean deleteCourse(int id);
    List<Course> getAllCourses();
    List<String> getAllThemes();
    List<Course> getLecturerCourses(String lecturer);
    List<String> getLecturerCourseThemes(String lecturer);
    List<Course> getCoursesByTheme(String theme);
    List<Course> getCoursesByLecturer(String lecturer);
}
