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
import com.example.pttmobile4.models.User;

import java.util.ArrayList;

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
        userList.add(new User(1, "Bosheng","Jian","bosheng@gatech.edu"));
        userList.add(new User(2231, "B","J","bj@gatech.edu"));
        userList.add(new User(3234, "Alex","Orso","random@gt.cc"));
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
        loadRecyclerView();
//        userList.setAdapter();
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
