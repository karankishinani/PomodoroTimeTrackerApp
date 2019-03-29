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
public class CancelTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void cancelTest() {
        SystemClock.sleep(2000);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.mainAdminLoginBtn), withText("Admin"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.createUserBtn), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());
        SystemClock.sleep(2000);

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
        appCompatEditText2.perform(replaceText("ka"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.cancelCreateUserBtn), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.createUserLayout),
                                        6),
                                0),
                        isDisplayed()));
        appCompatButton3.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.createUserBtn), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton4.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.fName),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.fName),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("k"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.lName),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("s"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.emailId),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("k@s.a"), closeSoftKeyboard());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.createUserOkBtn), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.createUserLayout),
                                        6),
                                1),
                        isDisplayed()));
        appCompatButton5.perform(click());
        SystemClock.sleep(2000);


        ViewInteraction checkUser= onView(allOf(withId(R.id.email),withText("k@s.a"), isDisplayed()));
        checkUser.check(matches(isDisplayed()));
        checkUser.perform(click());

        SystemClock.sleep(2000);

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.deleteUserBtn), withText("Delete"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.editUserLayout),
                                        6),
                                1),
                        isDisplayed()));
        appCompatButton6.perform(click());
        SystemClock.sleep(2000);


        ViewInteraction deleteUser= onView(allOf(withId(R.id.email),withText("k@s.a"), isDisplayed()));
        deleteUser.check(doesNotExist());



        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.createUserBtn), withText("Create"),
                        isDisplayed()));
        appCompatButton7.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.fName),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText7.perform(click());
        SystemClock.sleep(2000);


        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.fName),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("k"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.lName),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("s"), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.emailId),
                        childAtPosition(
                                allOf(withId(R.id.createUserLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("k@s.a"), closeSoftKeyboard());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.createUserOkBtn), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.createUserLayout),
                                        6),
                                1),
                        isDisplayed()));
        appCompatButton8.perform(click());
        SystemClock.sleep(2000);


        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.adminLogoutBtn), withText("Log Out"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton9.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.mainUserLoginBtn), withText("USER"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton10.perform(click());

        SystemClock.sleep(2000);

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.userEmail),
                        childAtPosition(
                                allOf(withId(R.id.userLoginLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText11.perform(click());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.userEmail),
                        childAtPosition(
                                allOf(withId(R.id.userLoginLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText12.perform(replaceText("k@s.a"), closeSoftKeyboard());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.userLoginBtn), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.userLoginLayout),
                                        2),
                                0),
                        isDisplayed()));
        appCompatButton11.perform(click());
        SystemClock.sleep(2000);



        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.createProjectBtn), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton12.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.projectName),
                        childAtPosition(
                                allOf(withId(R.id.createProjectLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText13.perform(click());
        SystemClock.sleep(2000);




        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.projectName),
                        childAtPosition(
                                allOf(withId(R.id.createProjectLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText14.perform(replaceText("p1"), closeSoftKeyboard());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.cancelCreateProjectBtn), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.createProjectLayout),
                                        2),
                                0),
                        isDisplayed()));
        appCompatButton13.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction checkproj= onView(allOf(withId(R.id.project_name),withText("p1"), isDisplayed()));
        checkproj.check(doesNotExist());


        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.createProjectBtn), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton14.perform(click());
        SystemClock.sleep(2000);



        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.projectName),
                        childAtPosition(
                                allOf(withId(R.id.createProjectLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText16.perform(replaceText("p1"), closeSoftKeyboard());

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(R.id.createProjectOkBtn), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.createProjectLayout),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton15.perform(click());
        SystemClock.sleep(2000);

        checkproj= onView(allOf(withId(R.id.project_name),withText("p1"), isDisplayed()));
        checkproj.check(matches(isDisplayed()));
        checkproj.perform(click());
        SystemClock.sleep(2000);


        ViewInteraction appCompatButton16 = onView(
                allOf(withId(R.id.deleteProjectBtn), withText("Delete"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.editProjectLayout),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton16.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton17 = onView(
                allOf(withId(android.R.id.button2), withText("No"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatButton17.perform(scrollTo(), click());
        SystemClock.sleep(2000);

        pressBack();
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton18 = onView(
                allOf(withId(R.id.userLogoutBtn), withText("Log Out"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton18.perform(click());

        SystemClock.sleep(2000);

        pressBack();
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton19 = onView(
                allOf(withId(R.id.mainAdminLoginBtn), withText("Admin"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton19.perform(click());
        SystemClock.sleep(2000);

        checkUser = onView(allOf(withId(R.id.email),withText("k@s.a"), isDisplayed()));
        checkUser.check(matches(isDisplayed()));
        checkUser.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton20 = onView(
                allOf(withId(R.id.deleteUserBtn), withText("Delete"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.editUserLayout),
                                        6),
                                1),
                        isDisplayed()));
        appCompatButton20.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton21 = onView(
                allOf(withId(android.R.id.button2), withText("No"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        appCompatButton21.perform(scrollTo(), click());
        SystemClock.sleep(2000);

        pressBack();

        SystemClock.sleep(2000);

        checkUser = onView(allOf(withId(R.id.email),withText("k@s.a"), isDisplayed()));
        checkUser.check(matches(isDisplayed()));
        checkUser.perform(click());
        SystemClock.sleep(2000);
        ViewInteraction appCompatButton22 = onView(
                allOf(withId(R.id.deleteUserBtn), withText("Delete"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.editUserLayout),
                                        6),
                                1),
                        isDisplayed()));
        appCompatButton22.perform(click());
        SystemClock.sleep(2000);

        ViewInteraction appCompatButton23 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton23.perform(scrollTo(), click());

        SystemClock.sleep(2000);

        deleteUser= onView(allOf(withId(R.id.email),withText("k@s.a"), isDisplayed()));
        deleteUser.check(doesNotExist());

        SystemClock.sleep(2000);


        ViewInteraction appCompatButton24 = onView(
                allOf(withId(R.id.adminLogoutBtn), withText("Log Out"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton24.perform(click());
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
