package com.squirrel.courses.dataaccess.model;

public class Question implements Comparable<Question>{
    private int id;
    private int test;
    private String questText;
    private int points;
    private byte isOpen;

    public Question(int id, int test, String questText, int points, byte isOpen) {
        this.id = id;
        this.test = test;
        this.questText = questText;
        this.points = points;
        this.isOpen = isOpen;
    }

    public Question(int test, String questText, int points, byte isOpen) {
        this.test = test;
        this.questText = questText;
        this.points = points;
        this.isOpen = isOpen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public String getQuestText() {
        return questText;
    }

    public void setQuestText(String questText) {
        this.questText = questText;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public byte getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(byte isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public int compareTo(Question question) {
        if (this.id < question.getId()) {
            return -1;
        }
        else if (this.id == question.getId()){
            return 0;
        }
        else {
            return 1;
        }
    }
}
