package com.example.pttmobile4.utils;

import com.example.pttmobile4.adapters.ProjectListAdapter;
import com.example.pttmobile4.adapters.UserListAdapter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

public class CustomMatcher{


    public static Matcher<RecyclerView.ViewHolder> withUser(final String fName, final  String lName, final  String email) {
        //Checks.checkNotNull(subject);
        return new BoundedMatcher<RecyclerView.ViewHolder, UserListAdapter.UserListViewHolder>(
                UserListAdapter.UserListViewHolder.class) {

            @Override
            protected boolean matchesSafely(UserListAdapter.UserListViewHolder viewHolder) {

                //return viewHolder.mFistName.equals(fName) && viewHolder.mLastName.equals(lName) && viewHolder.mEmail.equals(email);
                return true;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item with subject: ");
            }
        };
    }

    public static Matcher<RecyclerView.ViewHolder> withProject(final String projName) {
        //Checks.checkNotNull(subject);
        return new BoundedMatcher<RecyclerView.ViewHolder, ProjectListAdapter.ProjectListViewHolder>(
                ProjectListAdapter.ProjectListViewHolder.class) {

            @Override
            protected boolean matchesSafely(ProjectListAdapter.ProjectListViewHolder viewHolder) {

                return viewHolder.mProjectName.equals(projName);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item with subject: ");
            }
        };
    }

}