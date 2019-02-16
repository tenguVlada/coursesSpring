package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.*;
import com.squirrel.courses.service.course.ICourseService;
import com.squirrel.courses.service.test.ITestService;
import com.squirrel.courses.service.lesson.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Class TestController realizes controller's methods working with test.
 *
 * @author    Natalie Tkachenko, Maxim Tytskiy
 */
@Controller
public class TestController {
    private static final String LESSON = "lesson";
    private static final String COURSE = "course";
    private static final String IS_EXAM = "isExam";

    private ITestService testService;
    private ILessonService lessonService;
    private ICourseService courseService;

    @Autowired
    public TestController(ITestService testService, ICourseService courseService, ILessonService lessonService) {
        this.testService = testService;
        this.lessonService = lessonService;
        this.courseService = courseService;
    }

    @GetMapping({"/test"})
    public String showTestInfo(Model model, @RequestParam("lessonId") int lessonId, @RequestParam("isExam") byte isExam){
        Test test;
        List<Question> quests;
        List<Answer> answers = new ArrayList();

        if(isExam == 0) {
            test = testService.findTestByLesson(lessonId);
        } else {
            test = testService.findExamByCourse(lessonId);
        }

        model.addAttribute("id", test.getId());
        model.addAttribute(COURSE, lessonId);
        model.addAttribute("evaluation", test.getEvaluation());
        model.addAttribute(IS_EXAM, isExam);

        quests = testService.findQuestionsByTest(test.getId());
        model.addAttribute("questions", quests);

        for (Question quest: quests) {                  //добавление ответов ко всем закрытым вопросам
            if(quest.getIsOpen() == 0) {
                List<Answer> ans = testService.findAnswersByQuestion(quest.getId());

                if(!ans.isEmpty()){                        //если ответы есть, то добавляются
                    answers.addAll(ans);
                }
            }
        }

        model.addAttribute("answers", answers);

        return "test";
    }

    @GetMapping("/addtest")
    public String showNewTest(Model model, @RequestParam("courseId") int courseId,
                              @RequestParam("lessonId") Optional<Integer> lessonId)
    {
        byte isExam;
        Lesson lesson = null;

        if (lessonId.isPresent()) {
            isExam = 0;
            lesson = lessonService.getLessonById(lessonId.get());
        }else{
            isExam = 1;
        }
        Course course = courseService.getCourseById(courseId);

        model.addAttribute(IS_EXAM, isExam);
        model.addAttribute(COURSE, course);
        model.addAttribute(LESSON, lesson);

        return "addtest";
    }

    @GetMapping("/edittest")
    public String showTest(Model model, @RequestParam("courseId") int courseId,
                           @RequestParam("testId") int testId)
    {
        Course course = courseService.getCourseById(courseId);
        Test test = testService.findTestById(testId);
        Lesson lesson = null;

        if (test.getIsExam() == 0)
            lesson = lessonService.getLessonById(test.getLesson());

        List<Question> questions = testService.findQuestionsByTest(testId);
        Map<Question, List<Answer>> questionMap = new TreeMap<>();

        for(Question question: questions){
            questionMap.put(question, testService.findAnswersByQuestion(question.getId()));
        }

        model.addAttribute(IS_EXAM, test.getIsExam());
        model.addAttribute("testId", test.getId());
        model.addAttribute(COURSE, course);
        model.addAttribute(LESSON, lesson);
        model.addAttribute("questionMap", questionMap);

        return "edittest";
    }

    @RequestMapping({"/posttest"})
    public ModelAndView addTest(ModelMap model, @RequestParam Map<String, String> params)
    {
        String testId = params.get("testId");
        int courseId = Integer.parseInt(params.get("courseId"));
        int lessonId;
        byte isExam;

        if ((params.get("lessonId").equals("null"))){
            isExam = 1;
            lessonId=courseId;
        }
        else {
            isExam = 0;
            lessonId = Integer.parseInt(params.get("lessonId"));
        }

        if (testId != null){
            testService.deleteTest(Integer.parseInt(testId));
        }

        String questCount = params.get("questCount");
        String questText;//"quest" + q_num
        String ansCount; //"ans_cnt" + q_num
        int points; //"mark" + q_num //баллы за вопрос
        String ansText;  //i + "ans" + q_num
        double ansCoef;  //i + "coef" + q_num

        int evaluation = 0;

        int i = 0;
        while (i < Integer.parseInt(questCount)){
             String grade = params.get("mark"+i);
             if (grade != null){
                 evaluation += Integer.parseInt(grade);
                 i++;
             }
        }

        testService.addTest(new Test(lessonId, evaluation, isExam));

        int addedTestId = testService.getLastTest();
        byte questType;

        i = 0;
        while (i < Integer.parseInt(questCount))
        {
            questText = params.get("quest" + i);
            if (questText != null)
            {
                points = Integer.parseInt(params.get("mark" + i));
                ansCount = params.get("ans_cnt" + i);

                if (ansCount == null) {
                    questType = 1;
                } else {
                    questType = 0;
                }

                testService.addQuestion(new Question(addedTestId, questText, points, questType));

                if(questType == 0){
                    for (int j = 0; j < Integer.parseInt(ansCount); j++) {
                        ansText = params.get(j + "ans" + i);
                        ansCoef = Double.parseDouble(params.get(j + "coef" + i));

                        int addedQuestId = testService.getLastQuestion();
                        testService.addAnswer(new Answer(addedQuestId, ansText, ansCoef));
                    }
                }
                i++;
            }
        }

        return new ModelAndView("redirect:/course?courseId=" + courseId, model);
    }
}