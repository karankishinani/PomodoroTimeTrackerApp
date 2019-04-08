package com.example.pttmobile4.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.pttmobile4.R;

import java.sql.Time;
import java.util.Calendar;

public class GenerateReportActivity extends AppCompatActivity {
    DatePickerDialog picker,picker3;
    TimePickerDialog picker2,picker4;
    EditText startDate, startTime, endDate, endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report);
        startDate = (EditText) findViewById(R.id.startDate);
        startDate.setInputType(InputType.TYPE_NULL);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(GenerateReportActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                startDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        startTime = (EditText) findViewById(R.id.startTime);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int min = cldr.get(Calendar.MINUTE);
                // date picker dialog
                picker2 = new TimePickerDialog(GenerateReportActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hour, int min) {
                                String x = Integer.toString(min);
                                if(x.length() == 1){
                                    startTime.setText(hour + ":0" + min);
                                }
                                else{
                                    startTime.setText(hour + ":" + min);

                                }

                            }
                        }, hour, min, true);
                picker2.show();
            }
        });

        endDate = (EditText) findViewById(R.id.endDate);
        endDate.setInputType(InputType.TYPE_NULL);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker3 = new DatePickerDialog(GenerateReportActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                endDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker3.show();
            }
        });

        endTime = (EditText) findViewById(R.id.endTime);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int min = cldr.get(Calendar.MINUTE);
                // date picker dialog
                picker4 = new TimePickerDialog(GenerateReportActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hour, int min) {
                                String x = Integer.toString(min);
                                if(x.length() == 1){
                                    endTime.setText(hour + ":0" + min);
                                }
                                else{
                                    endTime.setText(hour + ":" + min);

                                }
                            }
                        }, hour, min, true);
                picker4.show();
            }
        });


    }
}
