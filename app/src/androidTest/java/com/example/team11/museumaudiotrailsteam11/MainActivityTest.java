package com.example.team11.museumaudiotrailsteam11;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.example.team11.museumaudiotrailsteam11.BeaconHistory.BeaconHistory;
import com.example.team11.museumaudiotrailsteam11.Fragments.HomeFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Created by Oliver on 5/11/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> hf = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ChecksIfTheHomeFragmentScanningButtonExists(){
        onView(withText(R.string.scanningButton)).check(matches(isDisplayed()));
    }

    @Test
    public void ChecksIfTheHomeFragmentHistoryButtonExists(){
        onView(withText(R.string.viewHistoryButton)).check(matches(isDisplayed()));
    }

    @Test
    public void ChecksIfTheHomeFragmentSettingsButtonExists(){
        onView(withText(R.string.settings)).check(matches(isDisplayed()));
    }
}