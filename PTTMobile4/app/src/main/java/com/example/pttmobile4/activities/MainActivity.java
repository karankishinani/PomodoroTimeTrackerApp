package com.example.pttmobile4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pttmobile4.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button mainUserLoginBtn;
    Button mainAdminLoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mainAdminLoginBtn = findViewById(R.id.mainAdminLoginBtn);
        mainAdminLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(intent);

            }
        });

        mainUserLoginBtn = findViewById(R.id.mainUserLoginBtn);
        mainUserLoginBtn.setOnClickListener(new View.OnClickListener() {
            public void  onClick(View v){
                Intent intent = new Intent(MainActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
