package com.squirrel.courses.dataaccess.model;

public class Course {
    private int id;
    private String lecturer;
    private String courseName;
    private String theme;
    private String description;

    public Course(int id, String lecturer, String courseName, String theme, String description) {
        this.id = id;
        this.lecturer = lecturer;
        this.courseName = courseName;
        this.theme = theme;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
