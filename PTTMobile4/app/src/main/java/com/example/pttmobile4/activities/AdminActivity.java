package com.example.pttmobile4.activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.pttmobile4.R;
import com.example.pttmobile4.adapters.UserListAdapter;
import com.example.pttmobile4.api.Client;
import com.example.pttmobile4.models.User;

import java.util.ArrayList;
import java.util.List;

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
        userList.add(new User("Bosheng","Jian","bosheng@gatech.edu"));
        userList.add(new User("B","J","bj@gatech.edu"));
        userList.add(new User("Alex","Orso","random@gt.cc"));
        mUserList = findViewById(R.id.userList);
        createUserBtn = findViewById(R.id.createUserBtn);
        adminLogoutBtn = findViewById(R.id.adminLogoutBtn);



        createUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, CreateUserActivity.class);
                startActivity(intent);
            }
        });

        adminLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadUsers();
//        userList.setAdapter();
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
        mUserListLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayout.VERTICAL);
        mUserList.addItemDecoration(dividerItemDecoration);
        mUserList.setLayoutManager(mUserListLayoutManager);
        mUserListAdapter = new UserListAdapter(this, userList);
        mUserList.setAdapter(mUserListAdapter);
    }
}
