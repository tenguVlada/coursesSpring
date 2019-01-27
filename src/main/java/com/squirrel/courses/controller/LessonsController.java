package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.dataaccess.model.Lesson;
import com.squirrel.courses.service.lesson.ILessonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LessonsController {
    private ILessonService lessonService;

    @Autowired
    public LessonsController(ILessonService lessonService){
        this.lessonService = lessonService;
    }

    @GetMapping(value = {"/", "", "/lessons"})
    public String test(Model model) {

        List<Lesson> lessons = lessonService.getLessonsByCourse(1);
        String courseName = lessonService.getCourseName(1);
        model.addAttribute("lessons", lessons);
        model.addAttribute("course", courseName);
        return "courselessons";
    }
}
