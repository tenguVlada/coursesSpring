package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.AppUser;
import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.service.course.ICourseService;
import com.squirrel.courses.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Collection;
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

    @GetMapping({"/", "/allcourses"})
    public String showAllCourses(Model model){
        List<Course> courses = courseService.getAllCourses();
        List<String> themes = courseService.getAllThemes();

        model.addAttribute("themes", themes);
        model.addAttribute("courses", courses);
        return "allcourses";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/addcourse")
    public String addCoursePage(Model model){
        return "addcourse";
    }

    @GetMapping({"/profile"})
    public String profilePage(Model model, Principal principal){
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userName = loginedUser.getUsername();
        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();

        if (authorities != null){
            String auth = authorities.iterator().next().getAuthority();
            if (auth.equals("ROLE_ADMIN")){
                return "mockPage";
            }
            else if (auth.equals("ROLE_LECTURER")){
                return showLecturerInfo(model, principal);
            }
            else if (auth.equals("ROLE_STUDENT")){
                return "mockPage";
            }
        }
        return null;
    }

   // @GetMapping({"/course"})
    public String showCourseInfo(Model model, @RequestParam("course_id") int courseId){
        Course course = courseService.getCourseById(courseId);

        model.addAttribute("isAuthor", (course.getLecturer().equals("brett1973@hotmail.com")));
        return "course";
    }

    @PostMapping({"/postcourse"})
    public ModelAndView postNewCourse(ModelMap model, Principal principal, @RequestParam("course_title") String title, @RequestParam("theme") String theme,
                                      @RequestParam("description") String description) {
        Course course = new Course(principal.getName(), title, theme, description);
        boolean success = courseService.addCourse(course);

        if (success)
            model.addAttribute("message", "Course is added!");
        else
            model.addAttribute("message", "Course adding failed!");

        return new ModelAndView("redirect:/profile", model);
    }

    @GetMapping(value = {"/addtest"})
    public String showNewTest(Model model, Principal principal){

        return "addtest";
    }

    //@GetMapping(value = {"/lecturer"})
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

    @GetMapping({"/about"})
    public String showAboutPage(){
        return "aboutPage";
    }

    //@GetMapping({"/error"}) разкоментируйте чтоб винсент не вылетал
    public String showErrorPage(){
        return "error/errorPage";
    }
}
