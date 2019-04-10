package com.example.pttmobile4.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
                final Map<String,Object> params = new ArrayMap<>();
                params.put("projectname", projectName.getText().toString());
                params.put("userId", userId);

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
                        if (projectNameList.contains(projectName.getText().toString())) {
                            new CustomToast().Show_Toast(false,getApplicationContext(),findViewById(R.id.editProjectLayout) ,"Duplicate project name!");
                            finish();
                        }
                        else{
                            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(params)).toString());

                            Call<Project> call2 = Client
                                    .getInstance().getApi().updateProject(userId, projectId, body);
                            call2.enqueue(new Callback<Project>() {
                                @Override
                                public void onResponse(Call<Project> call, Response<Project> response) {
                                    Project project = response.body();
                                    if (project==null){
                                        System.out.println("Project response is null");
                                    }
                                    else {
                                        new CustomToast().Show_Toast(true,getApplicationContext(),findViewById(R.id.editProjectLayout) ,"Updated: " + project.getProjectname());

                                    }
                                    // Go back to Last Activity
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<Project> call, Throwable t) {
                                    new CustomToast().Show_Toast(false,getApplicationContext(),findViewById(R.id.editProjectLayout) ,"Edit Project Failed");
                                    // Go back to Last Activity
                                    finish();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Project>> call, Throwable t) {
                    }
                });


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
                                                new CustomToast().Show_Toast(true,getApplicationContext(),findViewById(R.id.editProjectLayout) ,"Deleted: " + project.getProjectname());
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<Project> call, Throwable t) {
                                            new CustomToast().Show_Toast(false,getApplicationContext(),findViewById(R.id.editProjectLayout) ,"Failed!");
                                        }
                                    });
                                    // Go back to Last Activity
                                    finish();
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }

                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditProjectActivity.this);
                    builder.setMessage("Do you really want to delete it?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show(); //The project has time already logged to it.
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
                                new CustomToast().Show_Toast(true,getApplicationContext(),findViewById(R.id.editProjectLayout) ,"Deleted: " + project.getProjectname());

                            }
                        }

                        @Override
                        public void onFailure(Call<Project> call, Throwable t) {
                            new CustomToast().Show_Toast(false,getApplicationContext(),findViewById(R.id.editProjectLayout) ,"Failed!");
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
