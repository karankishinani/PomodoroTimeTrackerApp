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
import com.example.pttmobile4.models.Session;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;
import okhttp3.RequestBody;
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
    String sessionId;
    String startTime;
    int counter;
    public static int pomodoroTime = 30;
    public static int breakTime = 5;


    @Override
    public void onBackPressed()
    {
        //Do nothing
        return;

        // code here to show dialog
        //super.onBackPressed();  // optional depending on your needs
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Get Current date-time
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH) +1;
        int year = cldr.get(Calendar.YEAR);
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int min = cldr.get(Calendar.MINUTE);

        startTime = String.format("%d", year) + "-" + String.format("%02d", month) + "-" + String.format("%02d", day) + "T" + String.format("%02d", hour) + ":" + String.format("%02d", min) +"Z";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getString("userId");
            projectId = extras.getString("PROJECT_ID");
            sessionId = extras.getString("sessionId", "");
            System.out.println("Session id from intent " + sessionId);
            isSeperatePomodoro = extras.getBoolean("SeperatePomodoro", false);
            counter = extras.getInt("counter", 0);
            startTime = extras.getString("startTime", startTime);
            System.out.println("Number of pomodoros in this session "+ counter);

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

        timer = new CountDownTimer(pomodoroTime*60 * 1000, 1000) {
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

                if (totalRemainingSeconds > breakTime*60) {
                    // secondsString = Long.toString(seconds - 10);
                    timerTextView.setText("Remaining Working Time: \n" + (minutes - breakTime) + ":" + secondsString);
                } else {
                    timerTextView.setText("Remaining Break Time: \n" + minutesString + ":" + secondsString);
                }
            }



            @Override
            public void onFinish() {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final Calendar cldr = Calendar.getInstance();
                        int day = cldr.get(Calendar.DAY_OF_MONTH);
                        int month = cldr.get(Calendar.MONTH) +1;
                        int year = cldr.get(Calendar.YEAR);
                        int hour = cldr.get(Calendar.HOUR_OF_DAY);
                        int min = cldr.get(Calendar.MINUTE);

                        String endTime = String.format("%d", year) + "-" + String.format("%02d", month) + "-" + String.format("%02d", day) + "T" + String.format("%02d", hour) + ":" + String.format("%02d", min) +"Z";

                        counter = counter + 1;

                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                final Intent yes_intent = new Intent(PomodoroActivity.this, PomodoroActivity.class);
                                yes_intent.putExtra("userId",userId);
                                yes_intent.putExtra("SeperatePomodoro", isSeperatePomodoro);

                                if(!isSeperatePomodoro) {

                                    yes_intent.putExtra("PROJECT_ID", projectId);
                                    yes_intent.putExtra("counter", counter);
                                    yes_intent.putExtra("startTime", startTime);

                                    if (counter == 1) {
                                        // POST

                                        final Map<String, Object> params = new ArrayMap<>();
                                        params.put("startTime", startTime);
                                        params.put("endTime", endTime);
                                        params.put("counter", counter);
                                        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());

                                        Call<Session> call = Client
                                                .getInstance().getApi().createSession(Integer.valueOf(userId), Integer.valueOf(projectId), body);

                                        call.enqueue(new Callback<Session>() {
                                            @Override
                                            public void onResponse(Call<Session> call, Response<Session> response) {
                                                Session session = response.body();
                                                if (session == null) {
                                                    System.out.println("User response is null");
                                                } else {
                                                    sessionId = "" + session.getId();
                                                    yes_intent.putExtra("sessionId", sessionId);
                                                    System.out.println("this is the session id YES create " + sessionId);
                                                }
                                                finish();
                                                startActivity(yes_intent);
                                            }

                                            @Override
                                            public void onFailure(Call<Session> call, Throwable t) {
                                            }

                                        });

                                    } else {
                                        // PUT
                                        final Map<String, Object> params = new ArrayMap<>();
                                        params.put("startTime", startTime);
                                        params.put("endTime", endTime);
                                        params.put("counter", counter);
                                        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(params)).toString());

                                        Call<Session> call = Client
                                                .getInstance().getApi().updateSession(Integer.valueOf(userId), Integer.valueOf(projectId), Integer.valueOf(sessionId), body);

                                        call.enqueue(new Callback<Session>() {
                                            @Override
                                            public void onResponse(Call<Session> call, Response<Session> response) {
                                                Session session = response.body();
                                                if (session == null) {
                                                    System.out.println("User response is null");
                                                } else {
                                                    yes_intent.putExtra("sessionId", "" + session.getId());
                                                }
                                                finish();
                                                startActivity(yes_intent);
                                            }

                                            @Override
                                            public void onFailure(Call<Session> call, Throwable t) {
                                            }

                                        });


                                    }
                                }

                                else {
                                    finish();
                                    startActivity(yes_intent);
                                }

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                Intent no_intent = new Intent(PomodoroActivity.this, UserActivity.class);
                                no_intent.putExtra("userId",userId);

                                if (!isSeperatePomodoro){
                                    // PUT REQUEST
                                    final Map<String,Object> params = new ArrayMap<>();
                                    params.put("startTime", startTime);
                                    params.put("endTime", endTime);
                                    params.put("counter", counter);
                                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(params)).toString());

                                    Call<Session> call = Client
                                            .getInstance().getApi().updateSession(Integer.valueOf(userId), Integer.valueOf(projectId), Integer.valueOf(sessionId), body);

                                    call.enqueue(new Callback<Session>() {
                                        @Override
                                        public void onResponse(Call<Session> call, Response<Session> response) {
                                            Session session = response.body();
                                            if (session==null){
                                                System.out.println("User response is null");
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Session> call, Throwable t) {
                                        }

                                    });
                                }

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
                if (isSeperatePomodoro){
                    timer.cancel();

                    final Intent intent = new Intent(PomodoroActivity.this, UserActivity.class);
                    intent.putExtra("userId",userId);
                    finish();
                    startActivity(intent);

                } else {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            final Calendar cldr = Calendar.getInstance();
                            int day = cldr.get(Calendar.DAY_OF_MONTH);
                            int month = cldr.get(Calendar.MONTH) +1;
                            int year = cldr.get(Calendar.YEAR);
                            int hour = cldr.get(Calendar.HOUR_OF_DAY);
                            int min = cldr.get(Calendar.MINUTE);

                            String endTime = String.format("%d", year) + "-" + String.format("%02d", month) + "-" + String.format("%02d", day) + "T" + String.format("%02d", hour) + ":" + String.format("%02d", min) +"Z";

                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:

                                    timer.cancel();
                                    final Intent yes_intent = new Intent(PomodoroActivity.this, UserActivity.class);
                                    yes_intent.putExtra("userId",userId);

                                    if(counter == 0){

                                        // POST

                                        final Map<String,Object> params = new ArrayMap<>();
                                        params.put("startTime", startTime);
                                        params.put("endTime", endTime);
                                        params.put("counter", counter);
                                        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(params)).toString());

                                        Call<Session> call = Client
                                                .getInstance().getApi().createSession(Integer.valueOf(userId), Integer.valueOf(projectId), body);

                                        call.enqueue(new Callback<Session>() {
                                            @Override
                                            public void onResponse(Call<Session> call, Response<Session> response) {
                                                Session session = response.body();
                                                if (session==null){
                                                    System.out.println("User response is null");
                                                }
                                                else {
                                                    sessionId = ""+session.getId();
                                                    yes_intent.putExtra("sessionId", sessionId);
                                                    System.out.println("this is the session id YES create "+ sessionId);
                                                }
                                                finish();
                                                startActivity(yes_intent);
                                            }

                                            @Override
                                            public void onFailure(Call<Session> call, Throwable t) {
                                            }

                                        });

                                    }

                                    else {
                                        // PUT REQUEST
                                        final Map<String,Object> params = new ArrayMap<>();
                                        params.put("startTime", startTime);
                                        params.put("endTime", endTime);
                                        params.put("counter", counter);
                                        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(params)).toString());

                                        Call<Session> call = Client
                                                .getInstance().getApi().updateSession(Integer.valueOf(userId), Integer.valueOf(projectId), Integer.valueOf(sessionId), body);

                                        call.enqueue(new Callback<Session>() {
                                            @Override
                                            public void onResponse(Call<Session> call, Response<Session> response) {
                                                Session session = response.body();
                                                if (session==null){
                                                    System.out.println("User response is null");
                                                }
                                                finish();
                                                startActivity(yes_intent);
                                            }

                                            @Override
                                            public void onFailure(Call<Session> call, Throwable t) {
                                            }

                                        });

                                    }

                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    timer.cancel();
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
