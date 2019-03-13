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

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateProjectActivity extends AppCompatActivity {

    Button createProjectOkBtn;
    EditText projectName;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getString("userId");
            //Toast.makeText(CreateProjectActivity.this,  "User logged in successfully, user id is " + userId, Toast.LENGTH_LONG).show();
            //The key argument here must match that used in the other activity
        }

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
                            System.out.println("Project response is null");
                        }
                        else {
                            Toast.makeText(CreateProjectActivity.this,  "Created: " + project.getProjectname(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Project> call, Throwable t) {
                        Toast.makeText(CreateProjectActivity.this,  "Failed! ", Toast.LENGTH_LONG).show();
                    }
                });

                // Go back to Last Activity
                finish();

            }
        });
    }
}
