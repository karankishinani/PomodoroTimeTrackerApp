package com.example.pttmobile4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pttmobile4.R;
import com.example.pttmobile4.models.Session;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SessionListAdapter extends RecyclerView.Adapter<SessionListAdapter.SessionListViewHolder> {

    Context context;
    private ArrayList<Session> sessionList;
    private boolean selectingMultipleSessions = false;


    public SessionListAdapter(Context context, ArrayList<Session> sessionList){
        this.sessionList = sessionList;
        this.context = context;
    }

    @NonNull
    @Override
    public SessionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.session_item, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);

        SessionListViewHolder rcv = new SessionListViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull final SessionListViewHolder holder, final int position) {
        holder.mStartTime.setText(sessionList.get(position).getStartTime());
        holder.mEndtime.setText(sessionList.get(position).getEndTime());

        holder.mTimeWorked.setText(sessionList.get(position).getCounter());

        holder.mLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                updateSessionClick(holder);
                return true;
            }
        });

    }


    @Override
    public int getItemCount() {
        return sessionList.size();
    }


    public class SessionListViewHolder extends RecyclerView.ViewHolder{
        public TextView mStartTime, mEndtime, mTimeWorked;
        LinearLayout mLayout;
        SessionListViewHolder(View view){
            super(view);
            mStartTime = view.findViewById(R.id.start_time);
            mEndtime = view.findViewById(R.id.end_time);
            mTimeWorked = view.findViewById(R.id.time_worked);

            mLayout = view.findViewById(R.id.layout);
        }
    }
}
