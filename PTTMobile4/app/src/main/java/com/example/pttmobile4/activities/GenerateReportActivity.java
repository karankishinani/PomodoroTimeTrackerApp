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
    EditText eText, eText2, eText3, eText4;
    Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report);
        eText = (EditText) findViewById(R.id.startDate);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
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
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        eText2 = (EditText) findViewById(R.id.startTime);
        eText2.setOnClickListener(new View.OnClickListener() {
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
                                    eText2.setText(hour + ":0" + min);
                                }
                                else{
                                    eText2.setText(hour + ":" + min);

                                }

                            }
                        }, hour, min, true);
                picker2.show();
            }
        });

        eText3 = (EditText) findViewById(R.id.endDate);
        eText3.setInputType(InputType.TYPE_NULL);
        eText3.setOnClickListener(new View.OnClickListener() {
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
                                eText3.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker3.show();
            }
        });

        eText4 = (EditText) findViewById(R.id.endTime);
        eText4.setOnClickListener(new View.OnClickListener() {
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
                                    eText4.setText(hour + ":0" + min);
                                }
                                else{
                                    eText4.setText(hour + ":" + min);

                                }
                            }
                        }, hour, min, true);
                picker4.show();
            }
        });
    }
}
