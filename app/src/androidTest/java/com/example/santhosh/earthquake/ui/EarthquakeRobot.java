package com.example.santhosh.earthquake.ui;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.example.santhosh.earthquake.R;
import com.example.santhosh.earthquake.util.RecyclerViewMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;

public class EarthquakeRobot {

    private Context context;
    public static int RECYCLER_ID = R.id.recycler_view;


    public EarthquakeRobot() {
        context = InstrumentationRegistry.getTargetContext();
    }

    public EarthquakeRobot verifyDateTimeHolder() throws InterruptedException {
        sleep(1000);
        onView(withRecyclerView(RECYCLER_ID).atPosition(0))
                .check(matches(hasDescendant(withText(R.string.date_time_holder))));
        return this;
    }

    public EarthquakeRobot verifyDepthHolder() throws InterruptedException {
        onView(withRecyclerView(RECYCLER_ID).atPosition(2))
                .check(matches(hasDescendant(withText(R.string.depth_holder))));
        return this;
    }

    public EarthquakeRobot verifyMagnitudeHolder() throws InterruptedException {
        onView(withRecyclerView(RECYCLER_ID).atPosition(1))
                .check(matches(hasDescendant(withText(R.string.magnitude_holder))));
        return this;
    }

    public EarthquakeRobot verifyDateTimeValue(int position, String value) throws InterruptedException {
        onView(withRecyclerView(RECYCLER_ID).atPosition(position))
                .check(matches(hasDescendant(withText(value))));
        return this;
    }

    public EarthquakeRobot verifyDepthValue(int position, String value) throws InterruptedException {
        onView(withRecyclerView(RECYCLER_ID).atPosition(position))
                .check(matches(hasDescendant(withText(value))));
        return this;
    }

    public EarthquakeRobot verifyMagnitudeValue(int position, String value) throws InterruptedException {
        onView(withRecyclerView(RECYCLER_ID).atPosition(position))
                .check(matches(hasDescendant(withText(value))));
        return this;
    }

    public EarthquakeRobot clickRecyclerItem() throws InterruptedException {
        onView(withRecyclerView(RECYCLER_ID).atPosition(6)).perform(click());
        return this;
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }
}
