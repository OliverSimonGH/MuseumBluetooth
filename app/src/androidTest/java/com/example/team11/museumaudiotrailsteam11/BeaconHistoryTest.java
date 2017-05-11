package com.example.team11.museumaudiotrailsteam11;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

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

@RunWith(AndroidJUnit4.class)
public class BeaconHistoryTest {

    @Rule
    public ActivityTestRule<BeaconHistory> hf = new ActivityTestRule<>(BeaconHistory.class);

//    http://stackoverflow.com/questions/28390574/checking-toast-message-in-android-espresso
    @Test
    public void NothingInTheHistoryPageChecksIfToastPopsUp(){
        onView(withText(R.string.NoBeaconsFound)).inRoot(withDecorView(not(is(hf.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}