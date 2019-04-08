package com.example.pttmobile4.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report_sessions {

    @SerializedName("startingTime")
    @Expose
    private String startingTime;
    @SerializedName("endingTime")
    @Expose
    private String endingTime;
    @SerializedName("hoursWorked")
    @Expose
    private int hoursWorked;

    public String getStartingTime() {
        return startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }
}
