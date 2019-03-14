package com.example.pttmobile4.activities;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.pttmobile4.R;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.Project;
import com.example.pttmobile4.utils.CustomWarningToast;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProjectActivity extends AppCompatActivity {

    EditText projectName;
    Button updateProjectBtn;
    Button deleteProjectBtn;

    int userId;
    int projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            projectId = Integer.valueOf(extras.getString("PROJECT_ID"));
            userId = Integer.valueOf(extras.getString("userId"));
        }

        projectName = findViewById(R.id.projectName);

        // TODO: test the synchronous version, perhaps change the EditUserActivity to synchronous version
        // populate the EditText
        Call<Project> call = Client
                .getInstance().getApi().getProject(userId, projectId);

        call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                Project project = response.body();
                if (project==null){
                    System.out.println("Project response is null");
                }
                else {
                    projectName.setText(project.getProjectname());
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
            }

        });



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
                        new CustomWarningToast().Show_Toast(getApplicationContext(),findViewById(R.id.editProjectLayout) ,"Edit Project Failed");
                    }
                });

                // Go back to Last Activity
                finish();
            }
        });

        // delete project
        deleteProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: Currently, it always ask for confirmation.
                boolean hasTime = true;
                if (hasTime) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    Call<Project> call = Client
                                            .getInstance().getApi().deleteProject(userId, projectId);
                                    call.enqueue(new Callback<Project>() {
                                        @Override
                                        public void onResponse(Call<Project> call, Response<Project> response) {
                                            Project project = response.body();
                                            if (project == null) {
                                                System.out.println("Project response is null");
                                            } else {
                                                Toast.makeText(EditProjectActivity.this, "Deleted: " + project.getProjectname(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<Project> call, Throwable t) {
                                            new CustomWarningToast().Show_Toast(getApplicationContext(),findViewById(R.id.editProjectLayout) ,"Failed!");
                                        }
                                    });
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                            finish();
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditProjectActivity.this);
                    builder.setMessage("The project has time already logged to it. Do you really want to delete it?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
                } else {
                    Call<Project> call = Client
                            .getInstance().getApi().deleteProject(userId, projectId);
                    call.enqueue(new Callback<Project>() {
                        @Override
                        public void onResponse(Call<Project> call, Response<Project> response) {
                            Project project = response.body();
                            if (project == null) {
                                System.out.println("Project response is null");
                            } else {
                                Toast.makeText(EditProjectActivity.this, "Deleted: " + project.getProjectname(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Project> call, Throwable t) {
                            new CustomWarningToast().Show_Toast(getApplicationContext(),findViewById(R.id.editProjectLayout) ,"Failed!");
                        }
                    });
                    finish();
                }

                // Go back to Last Activity
                //
            }
        });

    }
}
