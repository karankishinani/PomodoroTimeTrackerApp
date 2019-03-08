package com.example.pttmobile4.models;

public class Project {
    int id, userId;
    String projectname;

    public Project(int id, int userId, String projectname) {
        this.id = id;
        this.userId = userId;
        this.projectname = projectname;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getProjectname() {
        return projectname;
    }
}
