package com.example.team11.museumaudiotrailsteam11;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.team11.museumaudiotrailsteam11.BeaconHistory.BeaconHistory;
import com.example.team11.museumaudiotrailsteam11.BeaconScan.BeaconsListScreen;
import com.example.team11.museumaudiotrailsteam11.Fragments.HomeFragment;
import com.example.team11.museumaudiotrailsteam11.Fragments.MapFragment;
import com.example.team11.museumaudiotrailsteam11.Fragments.SearchFragment;
import com.example.team11.museumaudiotrailsteam11.Fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.onFragmentClickListener{

    // Declaring a BottomNavigationView object - This was added with the widget.
    private BottomNavigationView bottomNavigationView;
    private android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_action_bar);
        Toolbar parent =(Toolbar) actionBar.getCustomView().getParent();
        parent.setContentInsetsAbsolute(0,0);

        TextView actionTitle = (TextView) findViewById(R.id.action_bar_title);
        actionTitle.setText("Museum Guide");

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navBot);

        // Declaring a FragmentTransaction which is two methods - Get the fragment manager and begin a transaction (Switch to a Fragment)
        final android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        // Creating an instance of the HomeFragment page.
        Fragment onLoad = HomeFragment.newInstance();
        // Locating the relative Layout where the fragment is going to be loaded into.
        fragmentTransaction.add(R.id.fragment_container, onLoad);
        // Commit the change.
        fragmentTransaction.commit();

        // Setting an on click listner for every item in menu
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            // http://www.truiton.com/2017/01/android-bottom-navigation-bar-example/
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment chosenFragment = null; // Making a Fragment setting it ro Null
                switch (item.getItemId()) { // Get an item from the menu
                    case R.id.home_item:    // If Home Item....
                        chosenFragment = HomeFragment.newInstance();    // Set the fragment to Home.
                        break;
                    case R.id.search_item:  // If search item....
                        chosenFragment = SearchFragment.newInstance();  // Set the fragment to Search.
                        break;
                    case R.id.settings_item: // If settings item
                        chosenFragment = SettingsFragment.newInstance();    // Set the fragment to Settings.
                        break;
                    case R.id.map_item: // If map item
                        chosenFragment = MapFragment.newInstance(); // Set the fragment to Map.
                        break;

                }
                android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                // Locating the relative Layout where the fragment is going to be loaded into.
                fragmentTransaction.replace(R.id.fragment_container, chosenFragment);
                // Commit the fragment to be loaded into the container..
                fragmentTransaction.commit();
                return true;
            }
        });
    }

    @Override
    public void switchToScan() {    // Method from interface onFragmentSwitchListener
        Intent i = new Intent(getApplicationContext(), BeaconsListScreen.class);    // Making a new intent to switch to the BeaconListScreen.
        startActivity(i);   // Start the intent
    }

    @Override
    public void switchToHistory() { // Method from interface onFragmentSwitchListener
        Intent i = new Intent(getApplicationContext(), BeaconHistory.class);    // Making a new intent to switch to the BeaconHistory.
        startActivity(i);   // Start the Intent
    }
}
