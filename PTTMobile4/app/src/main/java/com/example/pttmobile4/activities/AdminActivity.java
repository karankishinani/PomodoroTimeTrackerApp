package com.example.pttmobile4.activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.pttmobile4.R;

public class AdminActivity extends AppCompatActivity {

    ListView userList;
    Button createUserBtn;
    Button adminLogoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        userList = findViewById(R.id.userList);
        createUserBtn = findViewById(R.id.createUserBtn);
        adminLogoutBtn = findViewById(R.id.adminLogoutBtn);
        createUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, EditUserActivity.class);
                startActivity(intent);
            }
        });

        adminLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        userList.setAdapter();
    }
}
