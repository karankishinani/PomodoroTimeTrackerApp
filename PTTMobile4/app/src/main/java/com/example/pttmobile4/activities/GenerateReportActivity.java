package com.example.pttmobile4.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pttmobile4.R;

public class GenerateReportActivity extends AppCompatActivity {

    //Params
    String userId,ProjectId, startTime, endTime;
    boolean isCompleted, hasTotalNumber;

    Button generateProjectBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getString("userId");
        }

        generateProjectBtn = findViewById(R.id.generateReportBtn);
        generateProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GenerateReportActivity.this, ReportActivity.class);
                intent.putExtra("userId", userId);
                //TODO: Send Params, i.e. project name, start Time, end Time, completed pomos, totalhour
                startActivity(intent);

            }
        });


    }
}
