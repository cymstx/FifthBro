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

public class ActivityLoggedInTest {
    @Rule
    public ActivityScenarioRule<ActivityLoggedIn> activityScenarioRule = new ActivityScenarioRule<>(ActivityLoggedIn.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.welcome)).check(matches(isDisplayed()));
    }
}