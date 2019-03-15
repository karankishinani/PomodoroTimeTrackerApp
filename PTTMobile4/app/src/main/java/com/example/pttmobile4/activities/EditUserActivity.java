package com.example.pttmobile4.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pttmobile4.R;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.Project;
import com.example.pttmobile4.models.User;
import com.example.pttmobile4.utils.CustomToast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends AppCompatActivity {

    Button updateUserBtn;
    Button deleteUserBtn;
    EditText fName, lName, emailId;
    int id;
    ArrayList<Project> projectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String userid = extras.getString("USER_ID");
            id = Integer.valueOf(userid);
        }

        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        emailId = findViewById(R.id.emailId);

        // GET REQUEST to populate fields initially

        Call<User> call = Client
                .getInstance().getApi().getUser(id);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user==null){
                    System.out.println("User response is null");
                }
                else {
                    fName.setText(user.getFirstName());
                    lName.setText(user.getLastName());
                    emailId.setText(user.getEmail());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }

        });


        updateUserBtn = findViewById(R.id.updateUserBtn);
        deleteUserBtn = findViewById(R.id.deleteUserBtn);

        // Update a user

        updateUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> params = new ArrayMap<>();
                params.put("firstName", fName.getText().toString());
                params.put("lastName", lName.getText().toString());
                params.put("email", emailId.getText().toString());
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(params)).toString());
                // Edit User and add them in the DB



                Call<User> call = Client
                        .getInstance().getApi().editUser(id, body);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (user==null){
                            System.out.println("User response is null");
                        }
                        else {
                            new CustomToast().Show_Toast(true,getApplicationContext(),findViewById(R.id.editUserLayout) ,"Updated: " + user.getEmail());

                        }
                        // Go back to Last Activity
                        finish();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        // Go back to Last Activity
                        finish();
                    }
                });


            }
        });

        // Delete a user

        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<ArrayList<Project>> call = Client
                        .getInstance().getApi().getProjects(Integer.valueOf(id));

                call.enqueue(new Callback<ArrayList<Project>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Project>> call, Response<ArrayList<Project>> response) {
                        projectList = response.body();
                        if (projectList==null){
                            System.out.println("Projectlist is null");
                        }
                        else if (projectList.size() > 0) {
                            // Delete User
                            System.out.println("I am here!!!");
                            DialogInterface.OnClickListener dialogClickListener= new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which){
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            Call<User> call2 = Client.getInstance().getApi().deleteUser(id);
                                            call2.enqueue(new Callback<User>() {
                                                @Override
                                                public void onResponse(Call<User> call, Response<User> response) {
                                                    User user = response.body();
                                                    if (user == null) {
                                                        System.out.println("Project response is null");
                                                    } else {
                                                        new CustomToast().Show_Toast(true,getApplicationContext(),findViewById(R.id.editUserLayout) ,"Deleted: " + user.getEmail());

                                                    }
                                                }
                                                @Override
                                                public void onFailure(Call<User> call, Throwable t) {
                                                    new CustomToast().Show_Toast(false,getApplicationContext(),findViewById(R.id.editUserLayout) ,"Failed!");
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
                            AlertDialog.Builder builder = new AlertDialog.Builder(EditUserActivity.this);
                            builder.setMessage("The User has projects associated to it. Do you really want to delete it?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
                            } else if (projectList.size() == 0){
                                Call<User> call2 = Client
                                        .getInstance().getApi().deleteUser(id);
                                call2.enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        User user = response.body();
                                        if (user==null){
                                            System.out.println("User response is null");
                                        }
                                        else {
                                            new CustomToast().Show_Toast(true,getApplicationContext(),findViewById(R.id.editUserLayout) ,"Deleted: " + user.getEmail());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {
                                        new CustomToast().Show_Toast(false,getApplicationContext(),findViewById(R.id.editUserLayout) ,"Failed!");
                                    }
                                });

                                finish();

                                }


                    }

                    @Override
                    public void onFailure(Call<ArrayList<Project>> call, Throwable t) {
                        new CustomToast().Show_Toast(false,getApplicationContext(),findViewById(R.id.editUserLayout) ,"Failed!");

                    }

                });

            }


        });
    }


}