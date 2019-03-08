package com.example.pttmobile4.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pttmobile4.R;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.User;
import com.example.pttmobile4.models.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserActivity extends AppCompatActivity {

    Button createUserOkBtn;
    EditText fName, lName, emailId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        emailId = findViewById(R.id.emailId);
        createUserOkBtn = findViewById(R.id.createUserOkBtn);
        createUserOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CREATE User and add them in the DB
                Call<UserResponse> call = Client
                        .getInstance().getApi().createUser(fName.getText().toString(),lName.getText().toString(),emailId.getText().toString());
                System.out.println(fName.getText().toString());
                System.out.println(lName.getText().toString());
                System.out.println(emailId.getText().toString());
                System.out.println("Hi guys I'm outside!");
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        UserResponse userResponse = response.body();
                        System.out.println("This is my response: " + response);
                        System.out.println("Hi guys onResponse now!");
                        if (userResponse==null){
                            System.out.println("User response is null");
                        }
                        if (!userResponse.isError()) {

                            Toast.makeText(CreateUserActivity.this, "Something WRONG", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(CreateUserActivity.this,  "Created: " + userResponse.getUsers().getEmail(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        System.out.println("Hi guys I Failed :( !");
                        System.out.println(t.getMessage());
                    }
                });

                // Go back to Last Activity
                finish();
            }
        });

    }
}
