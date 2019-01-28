package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.Lesson;
import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.service.lesson.ILessonService;
import com.squirrel.courses.service.user.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class LessonsController {
    private ILessonService lessonService;
    private IUserService userService;

    @Autowired
    public LessonsController(ILessonService lessonService, IUserService userService){
        this.userService = userService;
        this.lessonService = lessonService;
    }

    @GetMapping(value = {"/coursePage"})
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
    }
}
