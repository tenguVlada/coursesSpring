package com.squirrel.courses.dataaccess.model;

public class Answer {
    private int id;
    private int question;
    private String ansText;
    private double coef;

    public Answer(int id, int question, String ansText, double coef) {
        this.id = id;
        this.question = question;
        this.ansText = ansText;
        this.coef = coef;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public String getAnsText() {
        return ansText;
    }

    public void setAnsText(String ansText) {
        this.ansText = ansText;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }
}
