package com.example.pttmobile4.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.pttmobile4.R;
import com.example.pttmobile4.adapters.SessionListAdapter;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.Report;
import com.example.pttmobile4.models.Session;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    //TODO: Receive Params from Bundle, i.e. project name, start Time, end Time, completed pomos, totalhour
    String userId, projectId, startTime, endTime;
    boolean includeCompletedPomodoros, includeTotalHoursWorkedOnProject;

    ArrayList<Session> sessionList = new ArrayList<>();
    Report report;

    private RecyclerView mSessionList;
    private RecyclerView.Adapter mSessionListAdapter;
    private RecyclerView.LayoutManager mSessionListLayoutManager;


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


    private void loadSessions(){
        // GET REQUEST to populate fields initially
        //TODO: to call this
        Call<Report> call = Client
                .getInstance().getApi().getReport(Integer.valueOf(userId),Integer.valueOf(projectId),startTime, endTime,
                        includeCompletedPomodoros, includeTotalHoursWorkedOnProject);

        call.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                report = response.body();
                if (report==null){
                    System.out.println("Sessionlist is null");
                }
                else {
                    //TODO: iterating the report, construct sessionList
                    //for (){
                    //
                    //}
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
