package com.example.pttmobile4.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pttmobile4.R;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.UserResponse;

import retrofit2.Call;

public class CreateUserActivity extends AppCompatActivity {

    Button createUserOkBtn;
    EditText fName, lName, emailId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        emailId = findViewById(R.id.lName);
        createUserOkBtn = findViewById(R.id.createUserOkBtn);
        createUserOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CREATE User and add them in the DB
                Call<UserResponse> call = Client
                        .getInstance().getApi().createUser(fName.getText().toString(),lName.getText().toString(),emailId.getText().toString());
                // Go back to Last Activity
                finish();
            }
        });

    }
}
