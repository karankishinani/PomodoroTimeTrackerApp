package com.example.pttmobile4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pttmobile4.R;
import com.example.pttmobile4.models.Report_sessions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DateFormatSymbols;
public class SessionListAdapter extends RecyclerView.Adapter<SessionListAdapter.SessionListViewHolder> {

    Context context;
    private List<Report_sessions> sessionList;
    private boolean selectingMultipleSessions = false;


    public SessionListAdapter(Context context, List<Report_sessions> sessionList){
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
        holder.mStartTime.setText(timeFormant(sessionList.get(position).getStartingTime()));
        holder.mEndtime.setText(timeFormant(sessionList.get(position).getEndingTime()));
        holder.mTimeWorked.setText(""+sessionList.get(position).getHoursWorked());

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

    private String timeFormant(String time){
        DateFormatSymbols df = new DateFormatSymbols();
        int month = Integer.valueOf(time.substring(5,7)) - 1;

//        "   May 7 2019, 18:39       "
        String ans = "\t" + df.getMonths()[month];
        ans += " " + time.substring(8,10);
        ans += " " + time.substring(0,4);
        ans += ",  " + time.substring(11,16);
        return ans;
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
