package com.example.semen.espressoexample;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class CustomSecondActivityTestRule {

    @Rule
    public SecondActivityTestRule secondActivityTest = new SecondActivityTestRule();

    @Test
    public void showDataFromIntent(){
        onView(withId(R.id.tvResult)).check(matches(withText("3, Name 3")));
    }
}
