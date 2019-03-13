package com.example.pttmobile4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        userLoginBtn = findViewById(R.id.userLoginBtn);
        userEmail = findViewById(R.id.userEmail);

        userLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                userLogin();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!isLoggedIn) {
            userLogin();
        }
    }

    private void userLogin() {

        final String useremail = userEmail.getText().toString();

        if (useremail == null || useremail.length() == 0){
            return;
        }

        System.out.println("Email inputted is " + useremail);

        Call<ArrayList<User>> call = Client
                .getInstance().getApi().getUsers();

        call.enqueue(new Callback<ArrayList<User>>() {

            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userList = response.body();
                boolean ans = false;

                if (userList==null){
                    System.out.println("Userlist is null");
                }
                else {
                    for (User u: userList){
                        System.out.println("user is "+ u.getId() + "email is " + u.getEmail());
                        System.out.println(useremail);
                        if (u.getEmail().equals(useremail)) {
                            System.out.println("Inside");
                            ans = true;
                            userId = u.getId().toString();
                        }
                    }
                    if (ans) {
                        isLoggedIn = true;
                        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                    }
                }
            }

            // TODO: toast login failed

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
            }

        });
    }

    /*userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValid()) {
                    Toast.makeText(UserLoginActivity.this, "user email id incorrect " + userId, Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
    }

    boolean ans;

    private boolean isValid(){
        userId = "";
        ans = false;
        final String useremail = userEmail.getText().toString();
        if (useremail == null || useremail.length() == 0){
            return ans;
        }

        System.out.println(ans);
        return ans;
    }*/
}
