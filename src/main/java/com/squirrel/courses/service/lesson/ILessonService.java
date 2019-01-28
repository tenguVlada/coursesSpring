package com.squirrel.courses.service.lesson;

import com.squirrel.courses.dataaccess.model.Lesson;
import com.squirrel.courses.dataaccess.model.Course;
import java.util.List;

public interface ILessonService {
    List<Lesson> getLessonsByCourse(int course);
    Course getCourse(int course);
    Lesson findLessonById(int lessonId);
}