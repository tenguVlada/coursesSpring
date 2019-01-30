package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.Lesson;
import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.dataaccess.model.Test;
import com.squirrel.courses.service.lesson.ILessonService;
import com.squirrel.courses.service.course.ITestService;
import com.squirrel.courses.service.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


import java.security.Principal;
import java.util.*;

@Controller
public class LessonsController {
    private ICourseService courseService;
    private ILessonService lessonService;
    private ITestService testService;

    @Autowired
    public LessonsController(ICourseService courseService, ILessonService lessonService, ITestService testService){
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.testService = testService;
    }

    @GetMapping(value = {"/course"})
    public String test(Model model, Principal principal, @RequestParam("courseId") int courseId,
                       @RequestParam("edit") Optional<Boolean> edit) {
        Course course = courseService.getCourseById(courseId);
        List<Lesson> lessons = lessonService.getLessonsByCourse(courseId);

        Map<Lesson, Test> testLesson = new HashMap();

        for (Lesson lesson: lessons) {
            testLesson.put(lesson, testService.findTestByLesson(lesson.getId()));
        }

        boolean isEdit = false;
        if (course.getLecturer().equals(principal.getName()))
            isEdit = edit.orElse(false);

        model.addAttribute("exam", lessonService.getLessonsByCourse(courseId));
        model.addAttribute("edit", isEdit);
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
