package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.AppUser;
import com.squirrel.courses.dataaccess.model.Answer;
import com.squirrel.courses.dataaccess.model.Question;
import com.squirrel.courses.dataaccess.model.Test;
import com.squirrel.courses.service.course.ITestService;
import com.squirrel.courses.service.user.IUserService;
import com.squirrel.courses.service.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class TestController {
    private ITestService testService;
    private IUserService userService;

    @Autowired
    public TestController(ITestService testService, IUserService userService) {
        this.userService = userService;
        this.testService = testService;
    }

    @GetMapping({"/test"})
    public String showTestInfo(Model model){            //, @RequestParam("lessonId") int lessonId, @RequestParam("isExam") byte isExam
        int isExam = 0;
        byte lessonId = 7;
        Test test;
        List<Question> quests;
        List<Answer> answers = null;

        if(isExam == 0) {
            test = testService.findTestByLesson(lessonId);
        } else {
            test = testService.findExamByCourse(lessonId);
        }

        model.addAttribute("id", test.getId());
        model.addAttribute("course", lessonId);
        model.addAttribute("evaluation", test.getEvaluation());
        model.addAttribute("isExam", isExam);

        quests = testService.findQuestionsByTest(test.getId());
        model.addAttribute("questions", quests);

        for (Question quest: quests) {                  //добавление ответов ко всем закрытым вопросам
            if(quest.getIsOpen() == 0) {
                List<Answer> ans = testService.findAnswersByQuestion(quest.getId());

                if(ans != null){                        //если ответы есть, то добавляются
                    answers.addAll(ans);
                }
            }
        }

        model.addAttribute("answers", answers);

        return "test";
    }
}