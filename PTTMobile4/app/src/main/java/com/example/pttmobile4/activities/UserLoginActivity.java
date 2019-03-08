package com.example.pttmobile4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pttmobile4.R;

public class UserLoginActivity extends AppCompatActivity {

    Button userLoginBtn;
    EditText userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        userLoginBtn = findViewById(R.id.userLoginBtn);
        userEmail = findViewById(R.id.userEmail);
        if (userEmail.getText().toString().length() == 0){
            // Err Handling
        }
        userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intent);
            }
        });
    }
}
