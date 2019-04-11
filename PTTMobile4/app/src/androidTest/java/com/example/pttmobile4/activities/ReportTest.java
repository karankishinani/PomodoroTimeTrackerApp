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

import java.util.Calendar;

import androidx.annotation.VisibleForTesting;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ReportTest {

    @VisibleForTesting
    public void setTime(){
        PomodoroActivity.pomodoroTime = 2;
        PomodoroActivity.breakTime = 1;
    };

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void reportTest() {
        setTime();

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

        ViewInteraction test3_appCompatButton = onView(
                allOf(withId(R.id.mainUserLoginBtn), withText("USER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        test3_appCompatButton.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_appCompatEditText = onView(
                allOf(withId(R.id.userEmail),
                        childAtPosition(
                                allOf(withId(R.id.userLoginLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        test3_appCompatEditText.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_appCompatEditText2 = onView(
                allOf(withId(R.id.userEmail),
                        childAtPosition(
                                allOf(withId(R.id.userLoginLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        test3_appCompatEditText2.perform(replaceText("m@s.m"), closeSoftKeyboard());

        ViewInteraction test3_appCompatEditText3 = onView(
                allOf(withId(R.id.userEmail), withText("m@s.m"),
                        childAtPosition(
                                allOf(withId(R.id.userLoginLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        test3_appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction test3_appCompatButton2 = onView(
                allOf(withId(R.id.userLoginBtn), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.userLoginLayout),
                                        2),
                                0),
                        isDisplayed()));
        test3_appCompatButton2.perform(click());
        SystemClock.sleep(2000);


        ViewInteraction test3_appCompatButton3 = onView(
                allOf(withId(R.id.createProjectBtn), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        test3_appCompatButton3.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_appCompatEditText4 = onView(
                allOf(withId(R.id.projectName),
                        childAtPosition(
                                allOf(withId(R.id.createProjectLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        test3_appCompatEditText4.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_appCompatEditText5 = onView(
                allOf(withId(R.id.projectName),
                        childAtPosition(
                                allOf(withId(R.id.createProjectLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        test3_appCompatEditText5.perform(replaceText("p"), closeSoftKeyboard());

        ViewInteraction test3_appCompatEditText6 = onView(
                allOf(withId(R.id.projectName), withText("p"),
                        childAtPosition(
                                allOf(withId(R.id.createProjectLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        test3_appCompatEditText6.perform(pressImeActionButton());

        ViewInteraction test3_appCompatButton4 = onView(
                allOf(withId(R.id.createProjectOkBtn), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.createProjectLayout),
                                        2),
                                1),
                        isDisplayed()));
        test3_appCompatButton4.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_appCompatButton5 = onView(
                allOf(withId(R.id.startPomodoroBtn), withText("Start Pomodoro"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        test3_appCompatButton5.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_appCompatButton6 = onView(
                allOf(withId(android.R.id.button1), withText("Yes")));
        test3_appCompatButton6.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_linearLayout = onView(
                allOf(withId(R.id.layout),
                        childAtPosition(
                                allOf(withId(R.id.selectProjectList),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                0),
                        isDisplayed()));
        test3_linearLayout.perform(click());
        SystemClock.sleep(5000);

        ViewInteraction test3_appCompatButton7 = onView(
                allOf(withId(R.id.stopPomodoroBtn), withText("Stop Pomodoro"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0),
                        isDisplayed()));
        test3_appCompatButton7.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_appCompatButton8 = onView(
                allOf(withId(android.R.id.button2), withText("No")));
        test3_appCompatButton8.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_appCompatButton9 = onView(
                allOf(withId(R.id.startPomodoroBtn), withText("Start Pomodoro"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        test3_appCompatButton9.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_appCompatButton10 = onView(
                allOf(withId(android.R.id.button1), withText("Yes")));
        test3_appCompatButton10.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_linearLayout2 = onView(
                allOf(withId(R.id.layout),
                        childAtPosition(
                                allOf(withId(R.id.selectProjectList),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                0),
                        isDisplayed()));
        test3_linearLayout2.perform(click());
        SystemClock.sleep((2*60+2)*1000);

        ViewInteraction test3_appCompatButton11 = onView(
                allOf(withId(android.R.id.button1), withText("Yes")));
        test3_appCompatButton11.perform(click());
        SystemClock.sleep(5000);

        ViewInteraction test3_appCompatButton12 = onView(
                allOf(withId(R.id.stopPomodoroBtn), withText("Stop Pomodoro"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0),
                        isDisplayed()));
        test3_appCompatButton12.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_appCompatButton13 = onView(
                allOf(withId(android.R.id.button1), withText("Yes")));
        test3_appCompatButton13.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_appCompatButton14 = onView(
                allOf(withId(R.id.startPomodoroBtn), withText("Start Pomodoro"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        test3_appCompatButton14.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_appCompatButton15 = onView(
                allOf(withId(android.R.id.button1), withText("Yes")));
        test3_appCompatButton15.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction test3_linearLayout3 = onView(
                allOf(withId(R.id.layout),
                        childAtPosition(
                                allOf(withId(R.id.selectProjectList),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                0),
                        isDisplayed()));
        test3_linearLayout3.perform(click());
        SystemClock.sleep((2*60+2)*1000);

        ViewInteraction test3_appCompatButton16 = onView(
                allOf(withId(android.R.id.button1), withText("Yes")));
        test3_appCompatButton16.perform(click());
        SystemClock.sleep((2*60+2)*1000);

        ViewInteraction test3_appCompatButton17 = onView(
                allOf(withId(android.R.id.button2), withText("No")));
        test3_appCompatButton17.perform(click());


        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.startReportBtn), withText("Report"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton3.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.startDate),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                3),
                        isDisplayed()));
        //appCompatEditText4.perform(click());

        Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH) +1;
        int year = cldr.get(Calendar.YEAR);
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int min = cldr.get(Calendar.MINUTE);

        appCompatEditText4.perform(replaceText(""+month+"/"+day+"/"+(year-1)));

        SystemClock.sleep(2000);

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.startTime),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                5),
                        isDisplayed()));
        //appCompatEditText5.perform(click());

        appCompatEditText5.perform(replaceText(""+hour+":"+String.format("%02d", min)));


        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.endDate),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                7),
                        isDisplayed()));
        //appCompatEditText6.perform(click());
        appCompatEditText6.perform(replaceText(""+month+"/"+day+"/"+(year)));


        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.endTime),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                9),
                        isDisplayed()));
        //appCompatEditText7.perform(click());
        appCompatEditText7.perform(replaceText(""+hour+":"+String.format("%02d", min)));

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.noOfCP), withText("No. of Completed Pomodoros"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                10),
                        isDisplayed()));
        appCompatCheckBox.perform(click());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.hoursWorked), withText("Total Number of Hours Worked"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                11),
                        isDisplayed()));
        appCompatCheckBox2.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.generateReportBtn), withText("Generate Report"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                12),
                        isDisplayed()));
        appCompatButton8.perform(click());
        SystemClock.sleep(2000);

        pressBack();
        SystemClock.sleep(2000);

        ViewInteraction appCompatCheckBox3 = onView(
                allOf(withId(R.id.noOfCP), withText("No. of Completed Pomodoros"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                10),
                        isDisplayed()));
        appCompatCheckBox3.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.generateReportBtn), withText("Generate Report"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                12),
                        isDisplayed()));
        appCompatButton9.perform(click());
        SystemClock.sleep(2000);

        pressBack();
        SystemClock.sleep(2000);

        ViewInteraction appCompatCheckBox4 = onView(
                allOf(withId(R.id.hoursWorked), withText("Total Number of Hours Worked"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                11),
                        isDisplayed()));
        appCompatCheckBox4.perform(click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.generateReportBtn), withText("Generate Report"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                12),
                        isDisplayed()));
        appCompatButton10.perform(click());
        SystemClock.sleep(2000);


        pressBack();
        SystemClock.sleep(2000);

        pressBack();
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.createProjectBtn), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton11.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.projectName),
                        childAtPosition(
                                allOf(withId(R.id.createProjectLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText8.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.projectName),
                        childAtPosition(
                                allOf(withId(R.id.createProjectLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("p2"), closeSoftKeyboard());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.createProjectOkBtn), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.createProjectLayout),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton12.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.startReportBtn), withText("Report"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton13.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction niceSpinner2 = onView(
                allOf(withId(R.id.nice_spinner), withText("p"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                1),
                        isDisplayed()));
        niceSpinner2.perform(click());
        SystemClock.sleep(2000);


        DataInteraction appCompatTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.nice_spinner),
                        childAtPosition(
                                withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                                0)))
                .atPosition(0);
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.startDate),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText(""+month+"/"+day+"/"+(year-1)));
        SystemClock.sleep(2000);

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.startTime),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                5),
                        isDisplayed()));
        //appCompatEditText13.perform(click());
        appCompatEditText11.perform(replaceText(""+hour+":"+String.format("%02d", min)));


        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.endDate),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                7),
                        isDisplayed()));
        //appCompatEditText12.perform(click());
        appCompatEditText12.perform(replaceText(""+month+"/"+day+"/"+year));


        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.endTime),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                9),
                        isDisplayed()));
        //appCompatEditText13.perform(click());
        appCompatEditText13.perform(replaceText(""+hour+":"+String.format("%02d", min)));

        SystemClock.sleep(2000);

        ViewInteraction appCompatCheckBox5 = onView(
                allOf(withId(R.id.noOfCP), withText("No. of Completed Pomodoros"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                10),
                        isDisplayed()));
        appCompatCheckBox5.perform(click());

        ViewInteraction appCompatCheckBox6 = onView(
                allOf(withId(R.id.hoursWorked), withText("Total Number of Hours Worked"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                11),
                        isDisplayed()));
        appCompatCheckBox6.perform(click());

        ViewInteraction appCompatButton18 = onView(
                allOf(withId(R.id.generateReportBtn), withText("Generate Report"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                12),
                        isDisplayed()));
        appCompatButton18.perform(click());
        SystemClock.sleep(2000);

        pressBack();
        SystemClock.sleep(2000);

        pressBack();
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
