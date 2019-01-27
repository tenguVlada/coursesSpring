package com.squirrel.courses.dataaccess.dao.lesson;

import com.squirrel.courses.dataaccess.model.Lesson;
import java.util.List;

public interface ILessonDAO{

    List<Lesson> allLessonsByCourse(int idCourse);
    String getCourseName(int idCourse);
    Lesson getLessonByID(int id);
    List<Lesson> getLessonsByName(String name);
    boolean addLesson(Lesson lesson);
    boolean deleteLesson(int id);
}
