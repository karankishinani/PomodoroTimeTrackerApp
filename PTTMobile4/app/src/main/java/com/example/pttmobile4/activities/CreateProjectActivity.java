package com.example.pttmobile4.activities;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pttmobile4.R;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.Project;
import com.example.pttmobile4.utils.CustomToast;

import org.json.JSONObject;

import java.util.Map;

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
                    }

                    @Override
                    public void onFailure(Call<Project> call, Throwable t) {
                        new CustomToast().Show_Toast(false,getApplicationContext(),findViewById(R.id.createProjectLayout) ,"Create Project Failed");
                    }
                });

                // Go back to Last Activity
                finish();

            }
        });
    }
}
