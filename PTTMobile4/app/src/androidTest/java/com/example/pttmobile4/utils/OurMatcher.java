package com.example.pttmobile4.utils;

import com.example.pttmobile4.adapters.ProjectListAdapter;
import com.example.pttmobile4.adapters.UserListAdapter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.internal.util.Checks;

public class OurMatcher {


    public static Matcher<RecyclerView.ViewHolder> withUser(final String fName, final  String lName, final  String email) {
        Checks.checkNotNull(fName);
        Checks.checkNotNull(lName);
        Checks.checkNotNull(email);
        return new BoundedMatcher<RecyclerView.ViewHolder, UserListAdapter.UserListViewHolder>(
                UserListAdapter.UserListViewHolder.class) {

            @Override
            protected boolean matchesSafely(UserListAdapter.UserListViewHolder viewHolder) {

                return viewHolder.mFistName.getText().toString().equals(fName) &&
                        viewHolder.mLastName.getText().toString().equals(lName) &&
                        viewHolder.mEmail.getText().toString().equals(email);
                //return true;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item with subject: ");
            }

        };
    }

    public static Matcher<RecyclerView.ViewHolder> withProject(final String projName) {
        Checks.checkNotNull(projName);
        return new BoundedMatcher<RecyclerView.ViewHolder, ProjectListAdapter.ProjectListViewHolder>(
                ProjectListAdapter.ProjectListViewHolder.class) {

            @Override
            protected boolean matchesSafely(ProjectListAdapter.ProjectListViewHolder viewHolder) {

                return viewHolder.mProjectName.getText().toString().equals(projName);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item with subject: ");
            }
        };
    }

}