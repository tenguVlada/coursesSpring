package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Course;
import java.util.List;

public interface CourseDAO {
    List<Course> findAllCourses();
    List<Course> findCoursesByLecturer(String lecturer);
    List<Course> findCoursesByName(String name);
    List<String> findCoursesThemes();
    List<String> findCoursesThemesByLecturer(String lecturer);
    Course findCourseByID(int id);
    boolean addCourse(Course course);
    //boolean editCourse(Course course);
    //boolean deleteCourse(int id);
}
