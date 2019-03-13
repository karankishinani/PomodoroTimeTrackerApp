package com.example.pttmobile4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pttmobile4.R;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginActivity extends AppCompatActivity {

    Button userLoginBtn;
    EditText userEmail;
    ArrayList<User> userList = new ArrayList<>();
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        userLoginBtn = findViewById(R.id.userLoginBtn);
        userEmail = findViewById(R.id.userEmail);

        userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValid()) return;

                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
    }

    boolean ans = false;

    private boolean isValid(){
        final String useremail = userEmail.getText().toString();
        if (useremail == null || useremail.length() == 0){
            Toast.makeText(UserLoginActivity.this, "user email id incorrect", Toast.LENGTH_LONG).show();
            return ans;
        }

        Call<ArrayList<User>> call = Client
                .getInstance().getApi().getUsers();

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userList = response.body();
                if (userList==null){
                    System.out.println("Userlist is null");
                }
                else {
                    for (User u: userList){
                        if (u.getEmail().equals(useremail)) {
                            ans = true;
                            userId = u.getId().toString();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
            }

        });
        return ans;
    }
}
