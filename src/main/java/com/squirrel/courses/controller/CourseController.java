package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.AppUser;
import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.service.course.ICourseService;
import com.squirrel.courses.service.user.IUserService;
import com.squirrel.courses.service.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class CourseController {
    private ICourseService courseService;
    private IUserService userService;

    @Autowired
    public CourseController(ICourseService courseService, IUserService userService){
        this.userService = userService;
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
    public String showLecturerInfo(Model model, Principal principal){
        List<Course> courses = courseService.getLecturerCourses(principal.getName());
        List<String> lecturerThemes = courseService.getLecturerCourseThemes(principal.getName());
        AppUser user = userService.findByLogin(principal.getName());

        String lecturerName = user.getUserName();
        String lecturerDesc = user.getDescription();
        model.addAttribute("specializations", lecturerThemes);
        model.addAttribute("courses", courses);
        model.addAttribute("lecturerName", lecturerName);
        model.addAttribute("lecturerDesc", lecturerDesc);

        return "lecturer";
    }
}
