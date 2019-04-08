package com.example.pttmobile4.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.pttmobile4.R;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.Project;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GenerateReportActivity extends AppCompatActivity {
    DatePickerDialog picker,picker3;
    TimePickerDialog picker2,picker4;
    EditText startDate, startTime, endDate, endTime;
    CheckBox noOfCP,hoursWorked;
    Spinner dropdownBtn;



    //TODO: get project ID
    String userId,ProjectId, start, end;
    Button generateProjectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getString("userId");
        }



        //Initialize
        generateProjectBtn = findViewById(R.id.generateReportBtn);
        noOfCP = findViewById(R.id.noOfCP);
        hoursWorked = findViewById(R.id.hoursWorked);
        startDate = (EditText) findViewById(R.id.startDate);
        startDate.setInputType(InputType.TYPE_NULL);
        endDate = (EditText) findViewById(R.id.endDate);
        endDate.setInputType(InputType.TYPE_NULL);
        startTime = findViewById(R.id.startTime);
        endTime = (EditText) findViewById(R.id.endTime);
        dropdownBtn = (Spinner) findViewById(R.id.dropdownBtn);

        final List<String> spinnerArray =  new ArrayList<String>();

        Call<ArrayList<Project>> call = Client.getInstance().getApi().getProjects(Integer.valueOf(userId));

        call.enqueue(new Callback<ArrayList<Project>>() {
            @Override
            public void onResponse(Call<ArrayList<Project>> call, Response<ArrayList<Project>>  response) {
                ArrayList<Project> projects = response.body();
                if (projects==null){
                    System.out.println("User response is null");
                }
                else {
                    for (Project project : projects){
                        spinnerArray.add(project.getProjectname());
                    }
                }
            }


            @Override
            public void onFailure(Call<ArrayList<Project> > call, Throwable t) {
            }

        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownBtn.setAdapter(adapter);

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







        generateProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start = startDate.getText().toString() + "T" + startTime.getText().toString()+"Z";
                end =  endDate.getText().toString() + "T" + endTime.getText().toString()+"Z";

                Intent intent = new Intent(GenerateReportActivity.this, ReportActivity.class);
                intent.putExtra("userId", userId);
                //TODO: Get Project ID
                intent.putExtra("ProjectId", ProjectId);
                intent.putExtra("startTime", start);
                intent.putExtra("endTime", end);
                intent.putExtra("includeCompletedPomodoros", noOfCP.isChecked());
                intent.putExtra("includeTotalHoursWorkedOnProject", hoursWorked.isChecked());

                startActivity(intent);

            }
        });


    }



}
