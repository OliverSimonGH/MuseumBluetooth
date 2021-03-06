package com.example.team11.museumaudiotrailsteam11;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    ListView list;
    List<String> museums;
    SearchView search;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        list = (ListView) findViewById(R.id.lv);
        search = (SearchView) findViewById(R.id.sv);
        tv = (TextView) findViewById(R.id.tv);

        museums = new ArrayList<>();
        museums.add(getString(R.string.Cardiff));
        museums.add(getString(R.string.CardiffBay));
        museums.add(getString(R.string.Newport));
        museums.add(getString(R.string.Swansea));
        museums.add(getString(R.string.Neath));
        museums.add(getString(R.string.Skewen));

        final ArrayAdapter<String> adap = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_textview, R.id.tv);
        adap.addAll(museums);
        list.setAdapter(adap);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                adap.getFilter().filter(text);
                return false;
            }
        });

        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {



                Intent i = new Intent(getApplicationContext(), museumInfo.class);
                startActivity(i);

        }





    });
}
}
