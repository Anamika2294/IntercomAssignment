package com.example.intercomassignment;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)

public class ButtonTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivity = new ActivityTestRule(MainActivity.class);


    @Test
    public void checkTextView_isDisplayed_and_notEmpty() throws Exception {
        // perform a click on the button
        onView(withId(R.id.btnHit)).perform(click());

        // passes if the textView does not match the empty string
        onView(withId(R.id.text_id)).check(matches(not(withText(""))));
    }
}
