package com.example.team11.museumaudiotrailsteam11.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.team11.museumaudiotrailsteam11.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c1630186 on 25/03/2017.
 */
public class SearchFragment extends Fragment {

    ListView list;
    List<String> museums;
    SearchView search;
    TextView tv;

    public static SearchFragment newInstance(){
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.search_fragment_layout, container, false);

        list = (ListView)view.findViewById(R.id.lv);
        search = (SearchView)view.findViewById(R.id.sv);
        tv = (TextView)view.findViewById(R.id.tv);

        museums = new ArrayList<>();
        museums.add("Cardiff");
        museums.add("Cardiff Bay");
        museums.add("Newport");
        museums.add("Swansea");
        museums.add("Neath");
        museums.add("Skewen");

        final ArrayAdapter<String> adap = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.custom_textview, R.id.tv);
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

        return view;
    }
}
