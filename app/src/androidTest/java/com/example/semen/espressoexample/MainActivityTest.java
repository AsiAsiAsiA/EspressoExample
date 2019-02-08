package com.example.semen.espressoexample;

import android.graphics.Color;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static android.support.test.espresso.contrib.RecyclerViewActions.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    String text = "Android";
    String def = "Default";

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void changeText() {
        onView(withId(R.id.editText)).perform(typeText(text), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText(text)));
    }

    @Test
    public void isTextEmpty() {
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText(def)));
    }

    @Test
    public void textColor() {
        onView(withId(R.id.tvResult)).check(matches(not(hasColorText(R.color.colorRed))));
    }

    @Test
    public void listCountriesClick() {
        String brazil = "Уругвай";
        onData(equalTo(brazil)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText(brazil)));
    }

    @Test
    public void recyclerViewItemClick() {
        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition(1, click()));
        onView(withId(R.id.tvResult)).check(matches(withText("Elite z3")));
    }

    @Test
    public void testHolder() {
        onView(withId(R.id.recyclerView)).perform(actionOnHolderItem(holderMatcher("Elite z3"), click()));
        onView(withId(R.id.tvResult)).check(matches(withText("Elite z3")));
    }

    @Test
    public void testView() {
        onView(withId(R.id.recyclerView)).perform(actionOnItem(hasDescendant(withText("Galaxy S8")),click()));
        onView(withId(R.id.tvResult)).check(matches(withText("Galaxy S8")));
    }

    @Test
    public void testContainsView() {
        onView(withId(R.id.recyclerView)).perform(actionOnItem(hasDescendant(withText(containsString("Gal"))),click()));
        onView(withId(R.id.tvResult)).check(matches(withText("Galaxy S8")));
    }

    @Test
    public void testClickAtPosition() {
        onView(withId(R.id.recyclerView)).perform(actionOnItem(hasDescendant(withText(containsString("Gal"))),click()).atPosition(0));
        onView(withId(R.id.tvResult)).check(matches(withText("Galaxy S8")));
    }

    @Test
    public void idleTest(){
        CustomIdlingResource countingIdlingResource = activityRule.getActivity().getCustomIdlingResource();
        Espresso.registerIdlingResources(countingIdlingResource);
        onView(withId(R.id.idleButton)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("Idle change")));
        Espresso.unregisterIdlingResources(countingIdlingResource);

    }

    @Test
    public void changeActivity(){
        onView(withId(R.id.startActivity)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("Str, Name 1")));

        onView(withId(R.id.startActivity)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("Result")));
    }

    public static Matcher<DataAdapter.ViewHolder> holderMatcher(final String name) {
        return new TypeSafeMatcher<DataAdapter.ViewHolder>() {
            @Override
            protected boolean matchesSafely(DataAdapter.ViewHolder holder) {
                return holder.getPhone().getName().equals(name);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Phone name: " + name);
            }
        };
    }


    static Matcher<Object> withItemId(final long id) {
        return new BoundedMatcher<Object, Item>(Item.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with item id: " + id);
            }

            @Override
            protected boolean matchesSafely(Item item) {
                return item.getId() == id;
            }
        };
    }

    static class ColorTextMatcher extends TypeSafeMatcher<View> {

        private final int color;

        ColorTextMatcher(int color) {
            this.color = color;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("has red text color");
        }

        @Override
        public boolean matchesSafely(View view) {
            if (view instanceof TextView) {
                Log.d("TestColor", ((TextView) view).getCurrentTextColor() + "");
                Log.d("TestColor", color + "");
                return ((TextView) view).getCurrentTextColor() == color;
            }
            Log.d("TestColor", "different way");
            return false;
        }
    }

    public static Matcher<View> hasColorText(int color) {
        return new ColorTextMatcher(color);
    }

    public static Matcher<View> hasRedColorText() {
        return hasColorText(Color.RED);
    }

    public static Matcher<View> hasGreenColorText() {
        return hasColorText(Color.GREEN);
    }
}