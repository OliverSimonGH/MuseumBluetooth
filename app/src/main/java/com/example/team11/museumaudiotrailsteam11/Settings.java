package com.example.team11.museumaudiotrailsteam11;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by c1633899 on 03/04/2017.
 */
public class Settings extends AppCompatActivity {

    //References
    private android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_action_bar);
        Toolbar parent =(Toolbar) actionBar.getCustomView().getParent();
        parent.setContentInsetsAbsolute(0,0);

        TextView actionTitle = (TextView) findViewById(R.id.action_bar_title);
        actionTitle.setText(R.string.settings);

        Fragment fragment = new SettingsPreference();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (savedInstanceState == null){
            fragmentTransaction.add(R.id.relative_layout, fragment, "settings_fragment");
            fragmentTransaction.commit();
        } else {
            fragment = getFragmentManager().findFragmentByTag("settings_fragment");
        }
    }
}
