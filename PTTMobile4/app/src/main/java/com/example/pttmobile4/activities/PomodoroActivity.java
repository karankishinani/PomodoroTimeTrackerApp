package com.example.pttmobile4.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pttmobile4.R;

public class PomodoroActivity extends AppCompatActivity {

    Button stopPomodoroBtn;
    boolean isSeperatePomodoro;
    String userId;
    String projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getString("userId");
            projectId = extras.getString("PROJECT_ID");
            isSeperatePomodoro = extras.getBoolean("SeperatePomodoro", false);
        }


        stopPomodoroBtn = findViewById(R.id.stopPomodoroBtn);
        stopPomodoroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSeperatePomodoro){
                    finish();
                } else {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    Intent yes_intent = new Intent(PomodoroActivity.this, UserActivity.class);
                                    yes_intent.putExtra("userId",userId);
                                    //TODO: log time
                                    finish();
                                    startActivity(yes_intent);
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    Intent no_intent = new Intent(PomodoroActivity.this, UserActivity.class);
                                    no_intent.putExtra("userId",userId);
                                    finish();
                                    startActivity(no_intent);
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(PomodoroActivity.this);
                    builder.setMessage("Do you want to log time to the project?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
                }

            }
        });
    }
}
