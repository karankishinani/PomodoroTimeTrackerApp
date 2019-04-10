package com.example.pttmobile4.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.pttmobile4.R;
import com.example.pttmobile4.adapters.ProjectListAdapter;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.Project;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    ArrayList<Project> projectList = new ArrayList<>();

    private RecyclerView mProjectList;
    private RecyclerView.Adapter mProjectListAdapter;
    private RecyclerView.LayoutManager mProjectListLayoutManager;

    Button userLogoutBtn;
    Button createProjectBtn;
    Button startPomodoroBtn;
    Button startReportBtn;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getString("userId");
        }

        mProjectList = findViewById(R.id.projectList);
        createProjectBtn = findViewById(R.id.createProjectBtn);
        userLogoutBtn = findViewById(R.id.userLogoutBtn);
        startPomodoroBtn = findViewById(R.id.startPomodoroBtn);

        userLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        createProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, CreateProjectActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        //TODO: add a yes/no pop-up
        startPomodoroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent yes_intent = new Intent(UserActivity.this, SelectProjectActivity.class);
                                yes_intent.putExtra("userId",userId);
                                startActivity(yes_intent);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                Intent no_intent = new Intent(UserActivity.this, PomodoroActivity.class);
                                no_intent.putExtra("userId",userId);
                                no_intent.putExtra("SeperatePomodoro", true);
                                startActivity(no_intent);
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                builder.setMessage("Do you want to associate it to a project?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
            }
        });
        //loadProjects();

        //TODO: Hide this Button if the user has no Projects
        startReportBtn = findViewById(R.id.startReportBtn);
        startReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, GenerateReportActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
    }

    public void onResume()
    {
        super.onResume();
        loadProjects();
    }

    private void loadProjects(){
        // GET REQUEST to populate fields initially
        Call<ArrayList<Project>> call = Client
                .getInstance().getApi().getProjects(Integer.valueOf(userId));

        call.enqueue(new Callback<ArrayList<Project>>() {
            @Override
            public void onResponse(Call<ArrayList<Project>> call, Response<ArrayList<Project>> response) {
                projectList = response.body();
                if (projectList==null){
                    System.out.println("Projectlist is null");
                }
                else {
                    loadRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Project>> call, Throwable t) {
            }

        });
    }
    private void loadRecyclerView() {
        mProjectList.setNestedScrollingEnabled(false);
        mProjectList.setHasFixedSize(false);
        mProjectListLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayout.VERTICAL);
        mProjectList.addItemDecoration(dividerItemDecoration);
        mProjectList.setLayoutManager(mProjectListLayoutManager);
        mProjectListAdapter = new ProjectListAdapter(this, projectList);
        mProjectList.setAdapter(mProjectListAdapter);
        mProjectListAdapter.notifyDataSetChanged();
    }
}
