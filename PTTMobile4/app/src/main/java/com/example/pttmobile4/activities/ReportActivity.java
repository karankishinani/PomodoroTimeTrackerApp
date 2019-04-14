package com.example.pttmobile4.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pttmobile4.R;
import com.example.pttmobile4.adapters.SessionListAdapter;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.Report;
import com.example.pttmobile4.models.Report_sessions;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportActivity extends AppCompatActivity {

    String userId, projectId, startTime, endTime;
    boolean includeCompletedPomodoros, includeTotalHoursWorkedOnProject;

    ArrayList<Report_sessions> sessionList = new ArrayList<>();
    Report report;

    private RecyclerView mSessionList;
    private RecyclerView.Adapter mSessionListAdapter;
    private RecyclerView.LayoutManager mSessionListLayoutManager;
    TextView sessionMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getString("userId");
            projectId = extras.getString("ProjectId");
            startTime = extras.getString("startTime");
            endTime = extras.getString("endTime");
            includeCompletedPomodoros = extras.getBoolean("includeCompletedPomodoros", false);
            includeTotalHoursWorkedOnProject = extras.getBoolean("includeTotalHoursWorkedOnProject", false);
        }

        if(includeCompletedPomodoros){
            TextView noOfCP_label = (TextView) findViewById(R.id.noOfCP_label);
            noOfCP_label.setVisibility(View.VISIBLE);
            TextView noOfCP_value = (TextView) findViewById(R.id.noOfCP_value);
            noOfCP_value.setVisibility(View.VISIBLE);
        }

        if(includeTotalHoursWorkedOnProject){
            final TextView hoursWorked_label = (TextView) findViewById(R.id.hoursWorked_label);
            hoursWorked_label.setVisibility(View.VISIBLE);
            final TextView hoursWorked_value = (TextView) findViewById(R.id.hoursWorked_value);
            hoursWorked_value.setVisibility(View.VISIBLE);
        }

        sessionMessage = findViewById(R.id.sessionMessage);

        mSessionList= findViewById(R.id.sessionList);
        //loadSessions();

    }

    public void onResume()
    {
        super.onResume();
        loadSessions();
    }

    private void loadSessions(){
        // GET REQUEST to populate fields initially
        Call<Report> call = Client
                .getInstance().getApi().getReport(Integer.valueOf(userId),Integer.valueOf(projectId),startTime, endTime,
                        includeCompletedPomodoros, includeTotalHoursWorkedOnProject);
        call.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                report = response.body();
                if (report==null){
                    System.out.println("Sessionlist is null");
                    sessionMessage.setVisibility(View.VISIBLE);
                }
                else {

                    if(includeCompletedPomodoros){
                        EditText noOfCP_value = findViewById(R.id.noOfCP_value);
                        noOfCP_value.setText(""+report.getCompletedPomodoros());
                    }

                    if(includeTotalHoursWorkedOnProject){
                        EditText hoursWorked_value = findViewById(R.id.hoursWorked_value);
                        hoursWorked_value.setText(""+report.getTotalHoursWorkedOnProject());
                    }

                    sessionList = report.getSessions();

                    if(sessionList.size() == 0)
                        sessionMessage.setVisibility(View.VISIBLE);
                    else
                        sessionMessage.setVisibility(View.GONE);


                    loadRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {
            }

        });
    }

    private void loadRecyclerView() {
        mSessionList.setNestedScrollingEnabled(false);
        mSessionList.setHasFixedSize(false);
        mSessionListLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayout.VERTICAL);
        mSessionList.addItemDecoration(dividerItemDecoration);
        mSessionList.setLayoutManager(mSessionListLayoutManager);
        mSessionListAdapter = new SessionListAdapter(this, sessionList);
        mSessionList.setAdapter(mSessionListAdapter);
        mSessionListAdapter.notifyDataSetChanged();
    }


}
