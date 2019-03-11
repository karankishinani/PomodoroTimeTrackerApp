package com.example.pttmobile4.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.pttmobile4.R;

public class EditUserActivity extends AppCompatActivity {

//    Button editUserBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String id = extras.getString("USER_ID");
            Toast.makeText(EditUserActivity.this,  "User to be edited is " + id, Toast.LENGTH_LONG).show();
            //The key argument here must match that used in the other activity
        }

//        editUserBtn =findViewById(R.id.us)
    }
}
