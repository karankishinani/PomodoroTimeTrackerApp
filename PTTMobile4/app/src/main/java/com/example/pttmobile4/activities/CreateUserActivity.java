package com.example.pttmobile4.activities;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pttmobile4.R;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.User;
import com.example.pttmobile4.utils.CustomToast;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserActivity extends AppCompatActivity {

    Button createUserOkBtn;
    Button cancelCreateUserBtn;
    EditText fName, lName, emailId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        emailId = findViewById(R.id.emailId);
        cancelCreateUserBtn = findViewById(R.id.cancelCreateUserBtn);
        cancelCreateUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        createUserOkBtn = findViewById(R.id.createUserOkBtn);
        createUserOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> params = new ArrayMap<>();
                params.put("firstName", fName.getText().toString());
                params.put("lastName", lName.getText().toString());
                params.put("email", emailId.getText().toString());

                String email = emailId.getText().toString().trim();
                if (email.isEmpty()) {
                    emailId.setError("Email is required");
                    emailId.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailId.setError("Enter a valid email");
                    emailId.requestFocus();
                    return;
                }

                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(params)).toString());
                // CREATE User and add them in the DB
                Call<User> call = Client
                        .getInstance().getApi().createUser(body);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (user==null){
                            System.out.println("User response is null");
                        }
                         else {
                            new CustomToast().Show_Toast(true,getApplicationContext(),findViewById(R.id.createUserLayout) ,"Created: " + user.getEmail());

                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        new CustomToast().Show_Toast(false,getApplicationContext(),findViewById(R.id.createUserLayout) ,"Create User Failed");

                    }
                });

                // Go back to Last Activity
                finish();
            }
        });

    }
}
