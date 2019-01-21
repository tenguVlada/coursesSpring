package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.service.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LecturerController {
    private ICourseService courseService;

    @Autowired
    public LecturerController(ICourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping(value = {"/", "", "/lecturer"})
    public String showLecturerCourses(Model model){
        List<Course> courses = courseService.getLecturerCourses("brett1973@hotmail.com");

        model.addAttribute("courses", courses);
        return "lecturer";
    }
}
