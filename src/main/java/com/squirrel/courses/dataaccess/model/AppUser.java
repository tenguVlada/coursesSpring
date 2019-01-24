package com.squirrel.courses.dataaccess.model;

public class AppUser {
    private String login;
    private String hashPass;
    private String role;
    private String userName;
    private String description;

    public AppUser(String login, String hashPass, String role, String userName, String description) {
        this.login = login;
        this.hashPass = hashPass;
        this.role = role;
        this.userName = userName;
        this.description = description;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPass() {
        return hashPass;
    }

    public void setHashPass(String hashPass) {
        this.hashPass = hashPass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
