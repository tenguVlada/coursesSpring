package com.squirrel.courses.config;

import com.squirrel.courses.dataaccess.dao.course.CourseDAO;
import com.squirrel.courses.dataaccess.dao.course.ICourseDAO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/*
@Configuration
public class CourseConfig {
    /*@Bean
    @ConfigurationProperties("app.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public ICourseDAO courseDao() {

        return new CourseDAO(dataSource());
    }
}*/
