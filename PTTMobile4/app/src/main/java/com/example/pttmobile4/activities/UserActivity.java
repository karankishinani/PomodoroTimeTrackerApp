package com.example.pttmobile4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pttmobile4.R;

public class UserActivity extends AppCompatActivity {

    Button userLogoutBtn;
    Button createProjectBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userLogoutBtn = findViewById(R.id.userLogoutBtn);
        createProjectBtn = findViewById(R.id.createProjectBtn);

        userLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        createProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"HELLO",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UserActivity.this, ProjectActivity.class);
                startActivity(intent);
            }
        });
    }
}
