package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.service.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CourseController {
    private ICourseService courseService;

    @Autowired
    public CourseController(ICourseService courseService){

        this.courseService = courseService;
    }

    @GetMapping("/allcourses")
    public String showAllCourses(Model model){
        List<Course> courses = courseService.getAllCourses();
        List<String> themes = courseService.getAllThemes();

        model.addAttribute("themes", themes);
        model.addAttribute("courses", courses);
        return "allcourses";
    }


    @GetMapping({"/course"})
    public String showCourseInfo(Model model, @RequestParam("course_id") int courseId){
        Course course = courseService.getCourseById(courseId);

        model.addAttribute("isAuthor", (course.getLecturer().equals("brett1973@hotmail.com")));
        return "course";
    }

    /*@GetMapping("/fragments/header")
    public String showHeader(Model model){
        String userName = userService.getUserName("brett1973@hotmail.com");

        model.addAttribute("username", userName);
        return "header";
    }*/

    @GetMapping(value = {"/lecturer"})
    public String showLecturerInfo(Model model){
        List<Course> courses = courseService.getLecturerCourses("brett1973@hotmail.com");
        List<String> lecturerThemes = courseService.getLecturerCourseThemes("brett1973@hotmail.com");
        //String lectureDescription = userService.getUserDescription("brett1973@hotmail.com");

        model.addAttribute("specializations", lecturerThemes);
        model.addAttribute("courses", courses);
        //model.addAttribute("lectureDesc", lectureDescription);

        return "lecturer";
    }
}
