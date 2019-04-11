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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreatePomodoroWithProjTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createPomodoroWithProjTest() {
        ViewInteraction test2_appCompatButton = onView(
                allOf(withId(R.id.mainAdminLoginBtn), withText("Admin"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        test2_appCompatButton.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test2_appCompatButton2 = onView(
                allOf(withId(R.id.createUserBtn), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        test2_appCompatButton2.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test2_appCompatEditText = onView(
                allOf(withId(R.id.fName),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        test2_appCompatEditText.perform(click());

        ViewInteraction test2_appCompatEditText2 = onView(
                allOf(withId(R.id.fName),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        test2_appCompatEditText2.perform(replaceText("m"), closeSoftKeyboard());

        ViewInteraction test2_appCompatEditText3 = onView(
                allOf(withId(R.id.lName),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        test2_appCompatEditText3.perform(replaceText("s"), closeSoftKeyboard());

        ViewInteraction test2_appCompatEditText4 = onView(
                allOf(withId(R.id.emailId),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                5),
                        isDisplayed()));
        test2_appCompatEditText4.perform(replaceText("m@s.m"), closeSoftKeyboard());

        ViewInteraction test2_appCompatButton3 = onView(
                allOf(withId(R.id.createUserOkBtn), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.createUserLayout),
                                        6),
                                1),
                        isDisplayed()));
        test2_appCompatButton3.perform(click());
        SystemClock.sleep(2000);


        ViewInteraction test2_appCompatButton4 = onView(
                allOf(withId(R.id.adminLogoutBtn), withText("Log Out"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        test2_appCompatButton4.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.mainUserLoginBtn), withText("USER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.userEmail),
                        childAtPosition(
                                allOf(withId(R.id.userLoginLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.userEmail),
                        childAtPosition(
                                allOf(withId(R.id.userLoginLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("m@s.m"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.userEmail), withText("m@s.m"),
                        childAtPosition(
                                allOf(withId(R.id.userLoginLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.userLoginBtn), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.userLoginLayout),
                                        2),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.createProjectBtn), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.projectName),
                        childAtPosition(
                                allOf(withId(R.id.createProjectLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.projectName),
                        childAtPosition(
                                allOf(withId(R.id.createProjectLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("p"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.projectName), withText("p"),
                        childAtPosition(
                                allOf(withId(R.id.createProjectLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(pressImeActionButton());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.createProjectOkBtn), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.createProjectLayout),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton4.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.startPomodoroBtn), withText("Start Pomodoro"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton5.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(android.R.id.button1), withText("Yes")));
        appCompatButton6.perform(scrollTo(), click());
        SystemClock.sleep(2000);

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.layout),
                        childAtPosition(
                                allOf(withId(R.id.selectProjectList),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                0),
                        isDisplayed()));
        linearLayout.perform(click());
        SystemClock.sleep(5000);

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.stopPomodoroBtn), withText("Stop Pomodoro"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0),
                        isDisplayed()));
        appCompatButton7.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(android.R.id.button2), withText("No"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        appCompatButton8.perform(scrollTo(), click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.startPomodoroBtn), withText("Start Pomodoro"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton9.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(android.R.id.button1), withText("Yes")));
        appCompatButton10.perform(scrollTo(), click());
        SystemClock.sleep(2000);

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.layout),
                        childAtPosition(
                                allOf(withId(R.id.selectProjectList),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                0),
                        isDisplayed()));
        linearLayout2.perform(click());
        SystemClock.sleep(31*60*1000);

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton11.perform(scrollTo(), click());
        SystemClock.sleep(5000);

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.stopPomodoroBtn), withText("Stop Pomodoro"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0),
                        isDisplayed()));
        appCompatButton12.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton13.perform(scrollTo(), click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.startPomodoroBtn), withText("Start Pomodoro"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton14.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton15.perform(scrollTo(), click());
        SystemClock.sleep(2000);

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.layout),
                        childAtPosition(
                                allOf(withId(R.id.selectProjectList),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                0),
                        isDisplayed()));
        linearLayout3.perform(click());
        SystemClock.sleep(31*60*1000);

        ViewInteraction appCompatButton16 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton16.perform(scrollTo(), click());
        SystemClock.sleep(31*60*1000);

        ViewInteraction appCompatButton17 = onView(
                allOf(withId(android.R.id.button2), withText("No"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        appCompatButton17.perform(scrollTo(), click());
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
