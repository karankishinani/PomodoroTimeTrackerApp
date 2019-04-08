package com.example.pttmobile4.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.pttmobile4.R;

import java.sql.Time;
import java.util.Calendar;

public class GenerateReportActivity extends AppCompatActivity {

    //Params
    String userId,ProjectId, startTime, endTime;
    boolean isCompleted, hasTotalNumber;

    //Spinner dropdown = findViewById(R.id.dropdownBtn);

    Button generateProjectBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report);
        DatePicker dp =  (DatePicker)this.findViewById(R.id.startDateOpt);
        Calendar cal = Calendar.getInstance();
        int currYear = cal.get(Calendar.YEAR);
        int currMonth = cal.get(Calendar.MONTH);
        int currDate = cal.get(Calendar.DAY_OF_MONTH);
        dp.init(currYear, currMonth, currDate, null);
        DatePicker dp2 =  (DatePicker)this.findViewById(R.id.endDateOpt);
        dp2.init(2019, 4, 8, null);


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
