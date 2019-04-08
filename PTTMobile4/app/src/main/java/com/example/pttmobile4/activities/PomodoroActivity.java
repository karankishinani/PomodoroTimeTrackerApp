package com.example.pttmobile4.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pttmobile4.R;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.Project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        if (!isSeperatePomodoro){

            TextView project_name_label = (TextView) findViewById(R.id.project_name_label);
            project_name_label.setVisibility(View.VISIBLE);

            final TextView project_name = (TextView) findViewById(R.id.project_name);
            project_name.setVisibility(View.VISIBLE);

            // GET REQUEST to populate fields initially

            Call<Project> call = Client
                    .getInstance().getApi().getProject(Integer.valueOf(userId), Integer.valueOf(projectId));

            call.enqueue(new Callback<Project>() {
                @Override
                public void onResponse(Call<Project> call, Response<Project> response) {
                    Project project = response.body();
                    if (project==null){
                        System.out.println("User response is null");
                    }
                    else {
                        project_name.setText(project.getProjectname());
                    }
                }

                @Override
                public void onFailure(Call<Project> call, Throwable t) {
                }

            });

        }

        timerTextView = (TextView) findViewById(R.id.timerText);
        stopPomodoroBtn = findViewById(R.id.stopPomodoroBtn);

        timer = new CountDownTimer(15 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long totalRemainingSeconds = millisUntilFinished / 1000;
                long minutes = totalRemainingSeconds / 60;
                long seconds = totalRemainingSeconds % 60;
                String minutesString;
                String secondsString;
                minutesString = Long.toString(minutes);
                secondsString = Long.toString(seconds);

                // Pad zero when less than 10 seconds
                if(secondsString.length()==1)
                    secondsString = "0" + secondsString;

                if (totalRemainingSeconds > 10) {
                    // secondsString = Long.toString(seconds - 10);
                    timerTextView.setText("Remaining Working Time: \n" + minutesString + ":" + secondsString);
                } else {
                    timerTextView.setText("Remaining Break Time: \n" + minutesString + ":" + secondsString);
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
                                yes_intent.putExtra("PROJECT_ID",projectId);
                                yes_intent.putExtra("SeperatePomodoro",isSeperatePomodoro);
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
