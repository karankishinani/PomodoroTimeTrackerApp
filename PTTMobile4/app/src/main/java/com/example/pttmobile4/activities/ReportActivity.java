package com.example.pttmobile4.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.pttmobile4.R;

public class ReportActivity extends AppCompatActivity {

    //TODO: Receive Params from Bundle, i.e. project name, start Time, end Time, completed pomos, totalhour
    String userId, ProjectId, startTime, endTime;
    boolean isCompleted, hasTotalNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //TODO: GET all params
            userId = extras.getString("userId");
        }

        //TODO: call the server with params and display
    }
}
