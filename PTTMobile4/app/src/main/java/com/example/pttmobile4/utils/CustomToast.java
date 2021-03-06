package com.example.pttmobile4.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pttmobile4.R;


public class CustomToast {

    public void Show_Toast(boolean msgType, Context context, View view, String message) {
        //If it's success
        if (msgType) {
            show(context, view, message, R.layout.custom_success);
        } else {
            show(context, view, message, R.layout.custom_warning);
        }

    }

    private void show( Context context, View view, String message, int id){
        // Layout Inflater for inflating custom view
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // inflate the layout over view
        View layout = inflater.inflate(id,
                (ViewGroup) view.findViewById(R.id.toast_root));

        // Get TextView id and set error
        TextView text =  layout.findViewById(R.id.toast_error);
        text.setText(message);

        Toast toast = new Toast(context);// Get Toast Context
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);// Set
        // Toast
        // gravity
        // and
        // Fill
        // Horizoontal

        toast.setDuration(Toast.LENGTH_SHORT);// Set Duration
        toast.setView(layout); // Set Custom View over toast

        toast.show();// Finally show toast
    }

}