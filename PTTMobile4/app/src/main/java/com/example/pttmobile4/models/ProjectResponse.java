package com.example.pttmobile4.models;

import java.util.List;

public class ProjectResponse {
    private boolean error;
    private Project project;

    public ProjectResponse(boolean error, Project project) {
        this.error = error;
        this.project = project;
    }

    public boolean isError() {
        return error;
    }

    public Project getUsers() {
        return project;
    }
}
