package com.squirrel.courses.service.course;

import com.squirrel.courses.dataaccess.dao.course.CourseDAO;
import com.squirrel.courses.dataaccess.model.Course;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService{

    @Override
    public List<Course> getLecturerCourses(String lecturer) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("database/SpringModule.xml");
        CourseDAO courseDAO = (CourseDAO) context.getBean("courseDAO");

        return courseDAO.findCoursesByLecturer(lecturer);
    }

    @Override
    public List<String> getLecturerCourseThemes(String lecturer) {


        return null;
    }
}
