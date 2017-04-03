package com.example.team11.museumaudiotrailsteam11.Fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

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
    WebView wv;


    public static SearchFragment newInstance() { // See HomeFragment for explanation
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.search_fragment_layout, container, false);

        list = (ListView) view.findViewById(R.id.lv);
        search = (SearchView) view.findViewById(R.id.sv);

        wv = (WebView) view.findViewById(R.id.wv);
        wv.setVisibility(View.INVISIBLE);


        museums = new ArrayList<>();
        museums.add(" ");
        museums.add(getString(R.string.Cardiff));
        museums.add(getString(R.string.CardiffBay));
        museums.add(getString(R.string.Newport));
        museums.add(getString(R.string.Swansea));
        museums.add(getString(R.string.Neath));
        museums.add(getString(R.string.Skewen));


        final ArrayAdapter<String> adap = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.custom_textview, R.id.tv);
        adap.addAll(museums);
        list.setAdapter(adap);


        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("WEB", "web clicked");
                wv.setVisibility(View.VISIBLE);
            }

        });
        wv.setWebViewClient(new WebViewClient() {
            // This method is deprecated from API 24 (Nougat) onwards
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView webview, String url) {
                Log.i("WEB", url);
                webview.loadUrl(url);
                return false;
            }

            // This method is used for API 24 and above
            // Method of maintaining compatibility derived from Henry's answer:
            // http://stackoverflow.com/questions/36484074/is-shouldoverrideurlloading-really-deprecated-what-can-i-use-instead
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView webview, WebResourceRequest request) {
                Uri url = request.getUrl();
                webview.loadUrl(url.toString());
                return false;
            }

        });
        wv.loadUrl("https://museum.wales/cardiff/");








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
