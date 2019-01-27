package com.squirrel.courses.service.lesson;

import com.squirrel.courses.dataaccess.model.Lesson;
import java.util.List;

public interface ILessonService {
    List<Lesson> getLessonsByCourse(int course);
    String getCourseName(int course);
}