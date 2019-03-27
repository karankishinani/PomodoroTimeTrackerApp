package com.example.pttmobile4.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.pttmobile4.R;
import com.example.pttmobile4.adapters.UserListAdapter;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.User;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    Button createUserBtn;
    Button adminLogoutBtn;

    //todo:
    ArrayList<User> userList = new ArrayList<>();

    private RecyclerView mUserList;
    private RecyclerView.Adapter mUserListAdapter;
    private RecyclerView.LayoutManager mUserListLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mUserList = findViewById(R.id.userList);
        createUserBtn = findViewById(R.id.createUserBtn);
        adminLogoutBtn = findViewById(R.id.adminLogoutBtn);

        adminLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        createUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, CreateUserActivity.class);
                startActivity(intent);
            }
        });

        //loadUsers();
//        userList.setAdapter();
    }

    public void onResume()
    {
        super.onResume();
        loadUsers();
    }

    private void loadUsers(){
        // GET REQUEST to populate fields initially
        Call<ArrayList<User>> call = Client
                .getInstance().getApi().getUsers();

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userList = response.body();
                if (userList==null){
                    System.out.println("Userlist is null");
                }
                else {
                    loadRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
            }

        });
    }
    private void loadRecyclerView() {
        mUserList.setNestedScrollingEnabled(false);
        mUserList.setHasFixedSize(false);
        mUserListLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayout.VERTICAL);
        mUserList.addItemDecoration(dividerItemDecoration);
        mUserList.setLayoutManager(mUserListLayoutManager);
        mUserListAdapter = new UserListAdapter(this, userList);
        mUserList.setAdapter(mUserListAdapter);
        mUserListAdapter.notifyDataSetChanged();
    }
}
