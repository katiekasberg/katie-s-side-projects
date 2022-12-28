package com.techelevator.tenmo.model;

public class Username {

    private String username;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Username(String username, int userId) {
        this.username = username;
        this.userId = userId;
    }

    public Username() {

    }
}
