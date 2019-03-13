package com.example.pttmobile4.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pttmobile4.R;
import com.example.pttmobile4.activities.AdminActivity;
import com.example.pttmobile4.activities.EditProjectActivity;
import com.example.pttmobile4.activities.UserActivity;
import com.example.pttmobile4.models.Project;

import java.util.ArrayList;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder> {

    Context context;
    private ArrayList<Project> projectList;
    private boolean selectingMultipleProjects = false;


    public ProjectListAdapter(Context context, ArrayList<Project> projectList){
        this.projectList = projectList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProjectListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);

        ProjectListViewHolder rcv = new ProjectListViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProjectListViewHolder holder, final int position) {
        holder.mProjectName.setText(projectList.get(position).getProjectname());

        holder.mLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                updateProjectClick(holder);
                return true;
            }
        });
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectingMultipleProjects){
//                    updateProjectClick(holder);
                    Toast.makeText((AdminActivity)context,  "Selected multiple projects ", Toast.LENGTH_LONG).show();
                }
                else{
                    int pos = holder.getAdapterPosition();
                    try {
                        int id = projectList.get(pos).getId();
                        if (id == 0){
                            Toast.makeText((AdminActivity)context,  "This Project's id is 0!!!!!!!!! ", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    String userId = "";

                    Bundle extras = ((UserActivity) context).getIntent().getExtras();
                    if (extras != null) {
                        userId = extras.getString("userId");
                        //Toast.makeText(UserActivity.this,  "User logged in successfully, user id is " + userId, Toast.LENGTH_LONG).show();
                        //The key argument here must match that used in the other activity
                    }

                    String id = "" + projectList.get(pos).getId();
                    Intent intent = new Intent(context, EditProjectActivity.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("PROJECT_ID",id);
                    context.startActivity(intent);

//                    projectList.get(position).setSelected(true);
//                    ((FindProjectActivity)context).createChat();
                }

            }
        });
    }

//    private void updateProjectClick(ProjectListViewHolder holder){
//        int position = holder.getAdapterPosition();
//
//        projectList.get(position).setSelected(!projectList.get(position).getSelected());
//
//        if(projectList.get(position).getSelected())
//            holder.mSelected.setBackground(context.getResources().getDrawable(R.drawable.ic_check));
//        else
//            holder.mSelected.setBackground(null);
//
//        updateSelectingMultipleProjects();
//    }

//    private void updateSelectingMultipleProjects() {
//        for(Project mProject : projectList){
//            if(mProject.getSelected()){
//                selectingMultipleProjects = true;
//                return;
//            }
//        }
//        selectingMultipleProjects = false;
//    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }


    class ProjectListViewHolder extends RecyclerView.ViewHolder{
        TextView mProjectName;
        LinearLayout mLayout;
        ProjectListViewHolder(View view){
            super(view);
            mProjectName = view.findViewById(R.id.project_name);
            mLayout = view.findViewById(R.id.layout);
        }
    }
}
