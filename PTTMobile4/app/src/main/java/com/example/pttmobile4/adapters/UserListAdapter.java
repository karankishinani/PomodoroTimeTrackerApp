package com.example.pttmobile4.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pttmobile4.R;
import com.example.pttmobile4.activities.AdminActivity;
import com.example.pttmobile4.activities.CreateProjectActivity;
import com.example.pttmobile4.activities.CreateUserActivity;
import com.example.pttmobile4.activities.EditUserActivity;
import com.example.pttmobile4.models.User;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder> {

    Context context;
    private ArrayList<User> userList;
    private boolean selectingMultipleUsers = false;


    public UserListAdapter(Context context, ArrayList<User> userList){
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);

        UserListViewHolder rcv = new UserListViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserListViewHolder holder, final int position) {
        holder.mFistName.setText(userList.get(position).getFirstName());
        holder.mLastName.setText(userList.get(position).getLastName());
        holder.mEmail.setText(userList.get(position).getEmail());

        holder.mLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                updateUserClick(holder);
                return true;
            }
        });
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectingMultipleUsers){
//                    updateUserClick(holder);
                    Toast.makeText((AdminActivity)context,  "Selected multiple users ", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText((AdminActivity)context,  "Click on one user! ", Toast.LENGTH_LONG).show();
                    int pos = holder.getAdapterPosition();
                    // todo:
//                    int id = userList.get(pos).getId();
                    String id = "1";

                    Intent intent = new Intent(context, EditUserActivity.class);
                    intent.putExtra("USER_ID",id);
                    context.startActivity(intent);

//                    userList.get(position).setSelected(true);
//                    ((FindUserActivity)context).createChat();
                }

            }
        });
    }

//    private void updateUserClick(UserListViewHolder holder){
//        int position = holder.getAdapterPosition();
//
//        userList.get(position).setSelected(!userList.get(position).getSelected());
//
//        if(userList.get(position).getSelected())
//            holder.mSelected.setBackground(context.getResources().getDrawable(R.drawable.ic_check));
//        else
//            holder.mSelected.setBackground(null);
//
//        updateSelectingMultipleUsers();
//    }

//    private void updateSelectingMultipleUsers() {
//        for(User mUser : userList){
//            if(mUser.getSelected()){
//                selectingMultipleUsers = true;
//                return;
//            }
//        }
//        selectingMultipleUsers = false;
//    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    class UserListViewHolder extends RecyclerView.ViewHolder{
        TextView mFistName, mLastName, mEmail;
        ImageView mSelected;
        LinearLayout mLayout;
        UserListViewHolder(View view){
            super(view);
            mFistName = view.findViewById(R.id.firstname);
            mLastName = view.findViewById(R.id.lastname);
            mEmail = view.findViewById(R.id.email);
            mLayout = view.findViewById(R.id.layout);
            mSelected = view.findViewById(R.id.selected);
        }
    }
}
