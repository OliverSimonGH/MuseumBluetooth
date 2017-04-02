package com.example.team11.museumaudiotrailsteam11;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by c1630186 on 25/03/2017.
 */
public class MainPageActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navBot);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment chosenFragment = null;
                switch (item.getItemId()) {
                    case R.id.home_item:
                        chosenFragment = HomeFragment.newInstance();
                        break;
                    case R.id.search_item:
                        chosenFragment = SearchFragment.newInstance();
                        break;
                    case R.id.history_item:
                        chosenFragment = HistoryFragment.newInstance();
                        break;
                }
                android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, chosenFragment);
                fragmentTransaction.commit();
                return true;
            }
        });
    }
}

