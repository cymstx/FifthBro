package com.example.testbro;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ActivityQRGenTest {

    @Rule
    public ActivityTestRule<ActivityQRGen> rule = new ActivityTestRule<ActivityQRGen>(ActivityQRGen.class){
        @Override
        protected Intent getActivityIntent(){
            Intent intent = new Intent();
            intent.putExtra("item","0xDEADBEEF");
            return intent;
        }
    };

    @Test
    public void onCreate() {
            onView(withId(R.id.qr_image)).check(matches(isDisplayed()));
    }

}