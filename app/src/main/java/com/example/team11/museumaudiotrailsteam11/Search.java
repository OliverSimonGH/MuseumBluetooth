package com.example.team11.museumaudiotrailsteam11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        museums.add("Cardiff");
        museums.add("Cardiff Bay");
        museums.add("Newport");
        museums.add("Swansea");
        museums.add("Neath");
        museums.add("Skewen");

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

    }
}
