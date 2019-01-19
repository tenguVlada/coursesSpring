package com.squirrel.courses.controller;

import com.squirrel.courses.service.ServiceImplementationExample;
import com.squirrel.courses.service.ServiceInterfaceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerExample {
    private ServiceInterfaceExample serviceIE;

    @Autowired
    public ControllerExample(ServiceImplementationExample serviceIE){
        this.serviceIE = serviceIE;
    }

    @RequestMapping("/")
    public String showString(Model model){
        model.addAttribute("description", serviceIE.getUserDescription("user"));
        return "htmlexample";
    }
}
