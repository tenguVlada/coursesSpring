package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.Lesson;
import com.squirrel.courses.service.lesson.ILessonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LessonController {
    private ILessonService lessonService;

    @Autowired
    public LessonController(ILessonService lessonService){
        this.lessonService = lessonService;
    }

    @GetMapping(value = {"/lesson"})
    public String test(Model model, @RequestParam(value = "idLesson", required = false) int id) {
        int lessonId = id;

        Lesson lesson = lessonService.findLessonById(lessonId);
        model.addAttribute("lesson", lesson);
        return "lesson";
    }
}
