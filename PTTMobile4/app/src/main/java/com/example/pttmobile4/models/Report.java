package com.example.pttmobile4.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Report {

    // TODO: complete this
    @SerializedName("sessions")
    @Expose
    private List<Report_sessions> sessions;
    @SerializedName("completedPomodoros")
    @Expose
    private int completedPomodoros;
    @SerializedName("totalHoursWorkedOnProject")
    @Expose
    private int totalHoursWorkedOnProject;


    public List<Report_sessions> getSessions() {
        return sessions;
    }

    public int getCompletedPomodoros() {
        return completedPomodoros;
    }

    public int getTotalHoursWorkedOnProject() {
        return totalHoursWorkedOnProject;
    }
}
