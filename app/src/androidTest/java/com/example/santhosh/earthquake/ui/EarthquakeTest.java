package com.example.santhosh.earthquake.ui;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;

import com.example.santhosh.earthquake.MainActivity;

import org.junit.Rule;
import org.junit.Test;

@LargeTest
public class EarthquakeTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void verifyContentHolders() throws InterruptedException {
        new EarthquakeRobot()
                .verifyDateTimeHolder()
                .verifyDepthHolder()
                .verifyMagnitudeHolder();
    }

    @Test
    public void verifyContentValues() throws InterruptedException {
        new EarthquakeRobot()
                .verifyDateTimeValue(3, "2012-04-11 08:43:09")
                .verifyDepthValue(2, "30")
                .verifyMagnitudeValue(4, "8.0");
    }

    @Test
    public void verifyClick() throws InterruptedException {
        new EarthquakeRobot()
                .clickRecyclerItem();
    }
}
