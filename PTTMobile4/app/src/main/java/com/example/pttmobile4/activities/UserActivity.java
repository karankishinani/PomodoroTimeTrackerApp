package com.example.pttmobile4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.pttmobile4.R;
import com.example.pttmobile4.adapters.ProjectListAdapter;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.Project;

import java.util.ArrayList;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mProjectList = findViewById(R.id.projectList);
        createProjectBtn = findViewById(R.id.createProjectBtn);
        userLogoutBtn = findViewById(R.id.userLogoutBtn);

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
                startActivity(intent);
            }
        });

        //loadProjects();

    }

    public void onResume()
    {
        super.onResume();
        loadProjects();
    }

    private void loadProjects(){
        // GET REQUEST to populate fields initially
        // TODO: user ID for projects
        Call<ArrayList<Project>> call = Client
                .getInstance().getApi().getProjects(1);

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
        mProjectListLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayout.VERTICAL);
        mProjectList.addItemDecoration(dividerItemDecoration);
        mProjectList.setLayoutManager(mProjectListLayoutManager);
        mProjectListAdapter = new ProjectListAdapter(this, projectList);
        mProjectList.setAdapter(mProjectListAdapter);
        mProjectListAdapter.notifyDataSetChanged();
    }
}
