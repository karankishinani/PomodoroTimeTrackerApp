package com.example.pttmobile4.activities;

import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pttmobile4.R;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.Project;
import com.example.pttmobile4.models.User;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProjectActivity extends AppCompatActivity {

    EditText projectName;
    Button updateProjectBtn;
    Button deleteProjectBtn;

    // TODO
    int userId = 1;
    int projectId = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);

        projectName = findViewById(R.id.projectName);

        // populate the EditText
        Call<Project> call = Client
                .getInstance().getApi().getProject(userId, projectId);
        Response<Project> response = null;
        try {
            response = call.execute();
        } catch (Throwable e) {
            Toast.makeText(EditProjectActivity.this,  "Failed! ", Toast.LENGTH_LONG).show();
        }
        Project project = response.body();
        if (project == null) {
            System.out.println("Project response is null");
        } else {
            projectName.setText(project.getProjectname());
        }


        updateProjectBtn = findViewById(R.id.updateProjectBtn);
        deleteProjectBtn = findViewById(R.id.deleteProjectBtn);

        // update project
        updateProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> params = new ArrayMap<>();
                params.put("projectname", projectName.getText().toString());
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(params)).toString());

                Call<Project> call = Client
                        .getInstance().getApi().updateProject(userId, projectId, body);
                call.enqueue(new Callback<Project>() {
                    @Override
                    public void onResponse(Call<Project> call, Response<Project> response) {
                        Project project = response.body();
                        if (project==null){
                            System.out.println("Project response is null");
                        }
                        else {
                            Toast.makeText(EditProjectActivity.this,  "Updated: " + project.getProjectname(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Project> call, Throwable t) {
                        Toast.makeText(EditProjectActivity.this,  "Failed! ", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        // delete project
        deleteProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<Project> call = Client
                        .getInstance().getApi().deleteProject(userId, projectId);
                call.enqueue(new Callback<Project>() {
                    @Override
                    public void onResponse(Call<Project> call, Response<Project> response) {
                        Project project = response.body();
                        if (project==null){
                            System.out.println("Project response is null");
                        }
                        else {
                            Toast.makeText(EditProjectActivity.this,  "Deleted: " + project.getProjectname(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Project> call, Throwable t) {
                        Toast.makeText(EditProjectActivity.this,  "Failed! ", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}
