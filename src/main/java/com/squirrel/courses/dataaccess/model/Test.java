package com.squirrel.courses.dataaccess.model;

public class Test {
    private int id;
    private int lesson;
    private int evaluation;
    private byte isExam;

    public Test(int id, int lesson, int evaluation, byte isExam) {
        this.id = id;
        this.lesson = lesson;
        this.evaluation = evaluation;
        this.isExam = isExam;
    }

    public Test(int lesson, int evaluation, byte isExam) {
        this.lesson = lesson;
        this.evaluation = evaluation;
        this.isExam = isExam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public byte getIsExam() {
        return isExam;
    }

    public void setIsExam(byte isExam) {
        this.isExam = isExam;
    }
}
