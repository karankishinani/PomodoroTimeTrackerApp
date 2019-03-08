package com.example.pttmobile4.models;

public class UserResponse {
    private boolean error;
    private String message;
    private User user;

    public UserResponse(boolean error, String message, User user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUsers() {
        return user;
    }
}
