package com.squirrel.courses.config;

import com.squirrel.courses.service.test.ITestService;
import com.squirrel.courses.service.test.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseConfig {

    @Bean
    public ITestService testService(){
        return new TestService();
    }
}
