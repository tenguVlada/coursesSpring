package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.AppUser;
import com.squirrel.courses.dataaccess.model.Lesson;
import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.dataaccess.model.Test;
import com.squirrel.courses.service.course.ICourseService;
import com.squirrel.courses.service.course.ITestService;
import com.squirrel.courses.service.lesson.ILessonService;
import com.squirrel.courses.service.user.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LessonsController {
    private ILessonService lessonService;
    private ICourseService courseService;
    private ITestService testService;

    @Autowired
    public LessonsController(ILessonService lessonService, ICourseService courseService, ITestService testService){
        this.lessonService = lessonService;
        this.courseService = courseService;
        this.testService = testService;
    }

    @GetMapping(value = {"/course"})
    public String test(Model model, Principal principal, @RequestParam("course_id") int courseId, @RequestParam("edit") boolean edit) {
        Course course = courseService.getCourseById(courseId);
        List<Lesson> lessons = lessonService.getLessonsByCourse(courseId);

        Map<Lesson, Test> testLesson = new HashMap();

        for (Lesson lesson: lessons) {
            testLesson.put(lesson, testService.findTestByLesson(lesson.getId()));
        }

        model.addAttribute("exam", lessonService.getLessonsByCourse(courseId));
        model.addAttribute("edit", edit);
        model.addAttribute("isAuthor", course.getLecturer().equals(principal.getName()));
        model.addAttribute("testlessons", testLesson);
        model.addAttribute("course", course);
        return "course";
    }

}

/*@GetMapping(value = {"/coursePage"})
public String test(Model model, @RequestParam(value = "idCourse", required=false) int id, Principal principal){
    int courseId = id;
    if(principal==null){
        return"login";
    }else{
    List<Lesson> lessons = lessonService.getLessonsByCourse(courseId);
    Course course = lessonService.getCourse(courseId);
    User loginedUser = (User) ((Authentication) principal).getPrincipal();

    Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();
    String userName = "";



    if (authorities != null) {
        String auth = authorities.iterator().next().getAuthority();
        if (auth.equals("ROLE_LECTURER")) {
            if(loginedUser.getUsername().equals(course.getLecturer()))
            userName = loginedUser.getUsername();
        }
    }
    model.addAttribute("userName",userName);
    model.addAttribute("lessons", lessons);
    model.addAttribute("course", course.getCourseName());
    return "coursePage";
    }
}*/
