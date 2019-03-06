package com.example.pttmobile4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                // Send Itent

            }
        });
        mainUserLoginBtn.setOnClickListener(new View.OnClickListener() {
            public void  onClick(View v){
                // Send Intent
            }
        });
    }
}
