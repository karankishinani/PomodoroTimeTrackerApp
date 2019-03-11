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
import com.example.pttmobile4.models.User;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends AppCompatActivity {

    Button updateUserBtn;
    Button deleteUserBtn;
    EditText fName, lName, emailId;
    // TODO: generalize
    int id = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String userid = extras.getString("USER_ID");
            id = Integer.valueOf(userid);
            Toast.makeText(EditUserActivity.this,  "User to be edited is " + id, Toast.LENGTH_LONG).show();
            //The key argument here must match that used in the other activity
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
                            Toast.makeText(EditUserActivity.this,  "Updated: " + user.getEmail(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                    }
                });

                // Go back to Last Activity
                finish();
            }
        });

        // Delete a user

        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete User
                Call<User> call = Client
                        .getInstance().getApi().deleteUser(id);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (user==null){
                            System.out.println("User response is null");
                        }
                        else {
                            Toast.makeText(EditUserActivity.this,  "Deleted: " + user.getEmail(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                    }
                });

                // Go back to Last Activity
                finish();
            }
        });
    }
}