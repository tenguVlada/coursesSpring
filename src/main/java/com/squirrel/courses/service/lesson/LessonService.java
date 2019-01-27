package com.squirrel.courses.service.lesson;

import com.squirrel.courses.dataaccess.dao.lesson.ILessonDAO;
import com.squirrel.courses.dataaccess.model.Lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LessonService implements ILessonService{

    private ILessonDAO lessonDAO;

    @Autowired
    public void setLessonDAO(ILessonDAO lessonDAO) {
        this.lessonDAO = lessonDAO;
    }

    @Override
    public List<Lesson> getLessonsByCourse(int course){
        return lessonDAO.allLessonsByCourse(course);
    }

    @Override
    public String getCourseName(int course){
        return lessonDAO.getCourseName(course);
    }
}
