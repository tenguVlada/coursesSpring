package com.squirrel.courses.dataaccess.model;

public class Question {
    private int test;
    private String questText;
    private int points;
    private byte isOpen;

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
}
