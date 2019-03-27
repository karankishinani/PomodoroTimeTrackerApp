package com.example.pttmobile4.activities;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.pttmobile4.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditDeleteUser {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void editDeleteUser() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.mainAdminLoginBtn), withText("Admin"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.createUserBtn), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.fName),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.fName),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("B"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.lName),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("J"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.emailId),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("b@j.c"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.createUserOkBtn), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.createUserLayout),
                                        6),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.userList),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                2),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        onView(allOf(isDisplayed(), withId(R.id.userList)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));

        ViewInteraction editText = onView(
                allOf(withId(R.id.fName), withText("Bosheng"),
                        childAtPosition(
                                allOf(withId(R.id.editUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.lName), withText("Jian"),
                        childAtPosition(
                                allOf(withId(R.id.editUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        editText2.check(matches(isDisplayed()));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.emailId), withText("bosheng@gatech.edu"),
                        childAtPosition(
                                allOf(withId(R.id.editUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                5),
                        isDisplayed()));
        editText3.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.updateUserBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.editUserLayout),
                                        6),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.deleteUserBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.editUserLayout),
                                        6),
                                1),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.fName), withText("Bosheng"),
                        childAtPosition(
                                allOf(withId(R.id.editUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(longClick());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.fName), withText("Bosheng"),
                        childAtPosition(
                                allOf(withId(R.id.editUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("B"));

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.fName), withText("B"),
                        childAtPosition(
                                allOf(withId(R.id.editUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText7.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.lName), withText("Jian"),
                        childAtPosition(
                                allOf(withId(R.id.editUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatEditText8.perform(longClick());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.lName), withText("Jian"),
                        childAtPosition(
                                allOf(withId(R.id.editUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("J"));

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.lName), withText("J"),
                        childAtPosition(
                                allOf(withId(R.id.editUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatEditText10.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.updateUserBtn), withText("Update"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.editUserLayout),
                                        6),
                                0),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.userList),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                2),
                        isDisplayed()));
        recyclerView3.check(matches(isDisplayed()));

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.userList),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2)));
        recyclerView4.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.deleteUserBtn), withText("Delete"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.editUserLayout),
                                        6),
                                1),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction recyclerView5 = onView(
                allOf(withId(R.id.userList),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                2),
                        isDisplayed()));
        recyclerView5.check(matches(isDisplayed()));

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.adminLogoutBtn), withText("Log Out"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton7.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
