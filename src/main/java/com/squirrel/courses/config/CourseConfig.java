package com.squirrel.courses.config;

import com.squirrel.courses.service.course.ITestService;
import com.squirrel.courses.service.course.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseConfig {

    @Bean
    public ITestService testService(){
        return new TestService();
    }
}
