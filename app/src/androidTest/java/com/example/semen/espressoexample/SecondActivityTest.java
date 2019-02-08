package com.example.semen.espressoexample;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SecondActivityTest {

    @Rule
    public ActivityTestRule<SecondActivity> activityRule = new ActivityTestRule(SecondActivity.class);

    @Test
    public void showDataFromIntent(){
        Intent intent = new Intent();
        intent.putExtra(Constants.EXTRA_ID, "d");
        intent.putExtra(Constants.EXTRA_NAME, "d");

        activityRule.launchActivity(intent);
        onView(withId(R.id.tvResult)).check(matches(withText("d, d")));
    }

}