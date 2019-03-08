package com.example.pttmobile4.models;

public class UserResponse {
    private boolean error;
    private User user;

    public UserResponse(boolean error, User user) {
        this.error = error;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public User getUsers() {
        return user;
    }
}
