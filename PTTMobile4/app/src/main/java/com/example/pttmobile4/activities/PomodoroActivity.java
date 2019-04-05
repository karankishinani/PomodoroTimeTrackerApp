package com.example.pttmobile4.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pttmobile4.R;

public class PomodoroActivity extends AppCompatActivity {
    private TextView timerTextView;
    Button stopPomodoroBtn;

    private CountDownTimer timer;
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

        timerTextView = (TextView) findViewById(R.id.timerText);
        stopPomodoroBtn = findViewById(R.id.stopPomodoroBtn);

        timer = new CountDownTimer(25 * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long totalRemainingSeconds = millisUntilFinished / 1000;
                long minutes = totalRemainingSeconds / 60;
                long seconds = totalRemainingSeconds % 60;
                String minutesString;
                String secondsString;
                if (totalRemainingSeconds > 5 * 60) {
                    minutesString = Long.toString(minutes - 5);
                    secondsString = Long.toString(seconds);
                    timerTextView.setText("Remaining Working Time: " + minutesString + ":" + secondsString);
                } else {
                    minutesString = Long.toString(minutes);
                    secondsString = Long.toString(seconds);
                    timerTextView.setText("Remaining Relaxing Time: " + minutesString + ":" + secondsString);
                }
            }

            @Override
            public void onFinish() {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent yes_intent = new Intent(PomodoroActivity.this, PomodoroActivity.class);
                                yes_intent.putExtra("userId",userId);
                                //TODO: increment the counter
                                finish();
                                startActivity(yes_intent);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                Intent no_intent = new Intent(PomodoroActivity.this, UserActivity.class);
                                no_intent.putExtra("userId",userId);
                                //TODO: log
                                finish();
                                startActivity(no_intent);
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(PomodoroActivity.this);
                builder.setMessage("Do you want to start a new pomodoro?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
            }
        };

        timer.start();

        stopPomodoroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
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
