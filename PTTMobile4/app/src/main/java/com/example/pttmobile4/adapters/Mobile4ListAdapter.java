package com.example.pttmobile4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pttmobile4.R;

import java.util.List;

public class Mobile4ListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    List<String> mdata;

    public Mobile4ListAdapter(Context context, List<String> data){
        mInflater = LayoutInflater.from(context);
        mdata = data;
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null){
//            convertView = mInflater.inflate(R.layout)

//        }

        return convertView;
    }
}
