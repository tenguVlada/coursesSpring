package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.*;
import com.squirrel.courses.service.test.ITestService;
import com.squirrel.courses.service.lesson.ILessonService;
import com.squirrel.courses.service.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class LessonController realizes controller's methods working with lesson/lessons.
 *
 * @author    Maxim Tytskiy, Vladislava Prokopenko
 */
@Controller
public class LessonController {
    private ILessonService lessonService;
    private ICourseService courseService;

    @Autowired
    public LessonController(ILessonService lessonService, ICourseService courseService, ITestService testService){
        this.lessonService = lessonService;
        this.courseService = courseService;
    }

    @GetMapping(value = {"/lesson"})
    public String test(Model model, @RequestParam("lessonId") int id) {

        Lesson lesson = lessonService.getLessonById(id);
        model.addAttribute("lesson", lesson);
        return "lesson";
    }

    @GetMapping({"/addlesson"})
    public String addlesson(Model model, @RequestParam("courseId") int courseId) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "addlesson";
    }

    @GetMapping({"/editlesson"})
    public String editlesson(Model model, @RequestParam("courseId") int courseId,
                             @RequestParam("lessonId") int lessonId)
    {
        Course course = courseService.getCourseById(courseId);

        model.addAttribute("course", course);
        model.addAttribute("lesson", lessonService.getLessonById(lessonId));
        return "editlesson";
    }

    @PostMapping({"/postlesson"})
    public ModelAndView postNewLesson(ModelMap model, @RequestParam("courseId") int courseId, @RequestParam("lessName") String lessName, @RequestParam("description") String description,
                                      @RequestParam("material") String material) {
        Lesson lesson = new Lesson(courseId, lessName, description, material);
        boolean success = lessonService.addLesson(lesson);

        if (success)
            model.addAttribute("message", "Lesson is added!");
        else
            model.addAttribute("message", "Lesson adding failed!");

        return new ModelAndView("redirect:/course?courseId=" + courseId, model);
    }
}
