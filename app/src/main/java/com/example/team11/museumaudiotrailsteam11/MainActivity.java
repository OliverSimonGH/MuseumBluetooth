package com.example.team11.museumaudiotrailsteam11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.team11.museumaudiotrailsteam11.BeaconHistory.BeaconHistory;
import com.example.team11.museumaudiotrailsteam11.BeaconScan.BeaconsListScreen;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToBeaconsSearchPage (View view) {
        Intent i = new Intent(MainActivity.this, BeaconsListScreen.class);
        startActivity(i);
    }

    public void goToBeaconsHistoryPage(View view) {
        Intent i = new Intent(MainActivity.this, BeaconHistory.class);
        finish();
        startActivity(i);
    }
}
