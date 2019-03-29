package com.example.pttmobile4.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pttmobile4.R;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.Project;
import com.example.pttmobile4.utils.CustomToast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateProjectActivity extends AppCompatActivity {

    Button createProjectOkBtn;
    Button cancelCreateProjectBtn;

    EditText projectName;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getString("userId");

        }

        cancelCreateProjectBtn = findViewById(R.id.cancelCreateProjectBtn);
        cancelCreateProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        projectName = findViewById(R.id.projectName);
        createProjectOkBtn = findViewById(R.id.createProjectOkBtn);
        createProjectOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,Object> params = new ArrayMap<>();
                params.put("projectname", projectName.getText().toString());
                params.put("userId", Integer.parseInt(userId));

                // addition check begin
                Call<ArrayList<Project>> preCall = Client
                        .getInstance().getApi().getProjects(Integer.valueOf(userId));

                preCall.enqueue(new Callback<ArrayList<Project>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Project>> call, Response<ArrayList<Project>> response) {
                        ArrayList<Project> projectList = response.body();
                        ArrayList<String> projectNameList = new ArrayList<String>();
                        for (Project project : projectList) {
                            projectNameList.add(project.getProjectname());
                        }
                        if (projectNameList.contains(projectName.getText())) {
                            new CustomToast().Show_Toast(false,getApplicationContext(),findViewById(R.id.createProjectLayout) ,"Duplicate project name!");
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Project>> call, Throwable t) {
                    }

                });
                // addition check end

                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(params)).toString());

                Call<Project> call = Client
                        .getInstance().getApi().createProject(Integer.valueOf(userId), body);
                call.enqueue(new Callback<Project>() {
                    @Override
                    public void onResponse(Call<Project> call, Response<Project> response) {
                        Project project = response.body();
                        if (project==null){
                            new CustomToast().Show_Toast(false, getApplicationContext(),findViewById(R.id.createProjectLayout) ,"Create Project Response is null");
                        }
                        else {

                            new CustomToast().Show_Toast(true, getApplicationContext(),findViewById(R.id.createProjectLayout) ,"Created: " + project.getProjectname());

                        }
                        // Go back to Last Activity
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Project> call, Throwable t) {
                        new CustomToast().Show_Toast(false,getApplicationContext(),findViewById(R.id.createProjectLayout) ,"Create Project Failed");
                        // Go back to Last Activity
                        finish();
                    }
                });


            }
        });
    }
}
