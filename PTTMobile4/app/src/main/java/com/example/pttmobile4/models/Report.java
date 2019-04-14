package com.example.pttmobile4.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Report {

    @SerializedName("sessions")
    @Expose
    private ArrayList<Report_sessions> sessions;
    @SerializedName("completedPomodoros")
    @Expose
    private int completedPomodoros;
    @SerializedName("totalHoursWorkedOnProject")
    @Expose
    private double totalHoursWorkedOnProject;


    public ArrayList<Report_sessions> getSessions() {
        return sessions;
    }

    public int getCompletedPomodoros() {
        return completedPomodoros;
    }

    public double getTotalHoursWorkedOnProject() {
        return totalHoursWorkedOnProject;
    }
}
