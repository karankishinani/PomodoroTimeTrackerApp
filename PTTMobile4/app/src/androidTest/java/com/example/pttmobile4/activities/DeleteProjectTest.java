package com.example.pttmobile4.activities;


import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.pttmobile4.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DeleteProjectTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void deleteProjectTest() {

        SystemClock.sleep(500);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.mainAdminLoginBtn), withText("Admin"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        SystemClock.sleep(500);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.createUserBtn), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        SystemClock.sleep(500);

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
        appCompatEditText2.perform(replaceText("a"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.lName),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("s"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.emailId),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("a@s.m"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.createUserOkBtn), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.createUserLayout),
                                        6),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        SystemClock.sleep(500);

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.adminLogoutBtn), withText("Log Out"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton4.perform(click());

        SystemClock.sleep(500);

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.mainUserLoginBtn), withText("USER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton5.perform(click());

        SystemClock.sleep(500);

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.userEmail),
                        childAtPosition(
                                allOf(withId(R.id.userLoginLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.userEmail),
                        childAtPosition(
                                allOf(withId(R.id.userLoginLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("a@s.m"), closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.userLoginBtn), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.userLoginLayout),
                                        2),
                                0),
                        isDisplayed()));
        appCompatButton6.perform(click());

        SystemClock.sleep(500);

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.createProjectBtn), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton7.perform(click());

        SystemClock.sleep(500);

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.projectName),
                        childAtPosition(
                                allOf(withId(R.id.createProjectLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText7.perform(click());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.projectName),
                        childAtPosition(
                                allOf(withId(R.id.createProjectLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("project to be deleted"), closeSoftKeyboard());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.createProjectOkBtn), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.createProjectLayout),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton8.perform(click());

        SystemClock.sleep(500);

        ViewInteraction checkProj = onView(allOf(withId(R.id.project_name),withText("project to be deleted"), isDisplayed()));
        checkProj.check(matches(isDisplayed()));
        checkProj.perform(click());


        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.deleteProjectBtn), withText("Delete"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.editProjectLayout),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton9.perform(click());

        SystemClock.sleep(500);

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton10.perform(scrollTo(), click());

        SystemClock.sleep(500);

        ViewInteraction deleteProj = onView(allOf(withId(R.id.project_name),withText("project to be deleted"), isDisplayed()));
        deleteProj.check(doesNotExist());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.userLogoutBtn), withText("Log Out"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton11.perform(click());

        SystemClock.sleep(500);

        pressBack();

        SystemClock.sleep(500);

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.mainAdminLoginBtn), withText("Admin"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton12.perform(click());

        SystemClock.sleep(500);

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.layout),
                        childAtPosition(
                                allOf(withId(R.id.userList),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        linearLayout2.perform(click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.deleteUserBtn), withText("Delete"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.editUserLayout),
                                        6),
                                1),
                        isDisplayed()));
        appCompatButton13.perform(click());

        SystemClock.sleep(500);

        ViewInteraction deleteCheck = onView(allOf(withId(R.id.email),withText("a@s.m"), isDisplayed()));
        deleteCheck.check(doesNotExist());

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.adminLogoutBtn), withText("Log Out"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton14.perform(click());
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
