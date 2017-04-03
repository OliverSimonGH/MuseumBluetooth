package com.example.team11.museumaudiotrailsteam11.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.team11.museumaudiotrailsteam11.R;

/**
 * Created by c1630186 on 25/03/2017.
 */
public class HomeFragment extends Fragment {
    ImageView startPic;
    Button scanningBtn;
    Button accessHistorybtn;
    Button settingBtn;
    onFragmentClickListener fm; // Instance of the interface.

    public static HomeFragment newInstance(){   // Constructor for this fragment.
        HomeFragment fragment = new HomeFragment(); // Declaring a new object fragment.
        return fragment;    // Returning the object fragment.
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.home_fragment_layout, container, false);

        startPic = (ImageView)view.findViewById(R.id.imageHome);
        startPic.setImageResource(R.drawable.lates_544x216);
        scanningBtn = (Button)view.findViewById(R.id.scanBtn);
        accessHistorybtn = (Button)view.findViewById(R.id.btnHistory);
        settingBtn = (Button)view.findViewById(R.id.btnSettings);

        scanningBtn.setOnClickListener(new View.OnClickListener() { // Setting onClickListener for the scanning button.
            @Override
            public void onClick(View v) {
                fm.switchToScan();  // Calling the method to switch to the Scan page.
            }
        });

        accessHistorybtn.setOnClickListener(new View.OnClickListener() { // Setting onClickListener for the History button.
            @Override
            public void onClick(View v) {
                fm.switchToHistory();   // Calling the method to switch to the History page.
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.switchToSettings();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context){  // onAttach Method for all methods in the onFragmentClickListener interface
        // git@gitlab.cs.cf.ac.uk:CM6122/10_fragments_lists_tabs_services.git
        super.onAttach(context);
        if (context instanceof onFragmentClickListener ){
            fm = (onFragmentClickListener) context;
        }
    }

    public interface onFragmentClickListener {  // Creating a interface.
        void switchToScan();    // Creating a method that switches to the scanning page
        void switchToHistory(); // Creating a method that switches to the History Page.
        void switchToSettings();
    }


}
