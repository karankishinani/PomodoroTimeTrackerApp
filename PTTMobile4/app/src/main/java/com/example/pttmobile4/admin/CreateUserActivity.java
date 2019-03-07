package com.example.pttmobile4.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pttmobile4.R;

public class CreateUserActivity extends AppCompatActivity {

    Button createUserOkBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        createUserOkBtn = findViewById(R.id.createUserOkBtn);
        createUserOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CREATE User and add them in the DB

                // Go back to Last Activity
                finish();
            }
        });
    }
}
