package com.example.pttmobile4.models;

import java.util.List;

public class ProjectsResponse
{
    private boolean error;
    private List<Project> projects;

    public ProjectsResponse(boolean error, List<Project> projects) {
        this.error = error;
        this.projects = projects;
    }

    public boolean isError() {
        return error;
    }

    public List<Project> getUsers() {
        return projects;
    }
}
