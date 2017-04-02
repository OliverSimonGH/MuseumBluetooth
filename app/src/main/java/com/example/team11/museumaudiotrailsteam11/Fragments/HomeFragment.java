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
    onFragmentClickListener fm;

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
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

        scanningBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.switchToScan();
            }
        });

        accessHistorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.switchTOHistory();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof onFragmentClickListener ){
            fm = (onFragmentClickListener) context;
        }
    }

    public interface onFragmentClickListener {  // Creating a interface.
        void switchToScan();    // Creating a method that switches to the scanning page
        void switchTOHistory(); // Creating a method that switches to the History Page.

    }


}
