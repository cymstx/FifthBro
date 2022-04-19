package com.example.testbro;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.banner)).check(matches(isDisplayed()));
    }

    @Test
    public void clickRegister(){
        onView(withId(R.id.register)).perform(click());

        // This view is in a different Activity
        onView(withId(R.id.registerUser)).check(matches(isDisplayed()));
        onView(withId(R.id.phoneNumber)).check(matches(isDisplayed()));
    }
}