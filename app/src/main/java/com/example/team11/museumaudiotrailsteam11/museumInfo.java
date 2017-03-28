package com.example.team11.museumaudiotrailsteam11;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class museumInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_info);
    }



        public void goToSearchPage(View v) {

            Intent i = new Intent(getApplicationContext(), Search.class);
            startActivity(i);

        }






}












