package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.Lesson;
import com.squirrel.courses.service.lesson.ILessonService;
import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.service.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LessonController {
    private ILessonService lessonService;
    private ICourseService courseService;

    @Autowired
    public LessonController(ILessonService lessonService, ICourseService courseService){
        this.lessonService = lessonService;
        this.courseService = courseService;
    }

    @GetMapping(value = {"/lesson"})
    public String test(Model model, @RequestParam(value = "idLesson", required = false) int id) {
        int lessonId = id;

        Lesson lesson = lessonService.findLessonById(lessonId);
        model.addAttribute("lesson", lesson);
        return "lesson";
    }

    @GetMapping(value = {"/addlesson"})
    public String addlesson(Model model) {      //, @RequestParam("courseId") int courseId
        int courseId = 7;
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "addlesson";
    }
}
