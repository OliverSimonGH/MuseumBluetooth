package com.example.team11.museumaudiotrailsteam11;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by c1630186 on 29/03/2017.
 */
public class MapFragment extends Fragment{

        public static MapFragment newInstance(){
            MapFragment fragment = new MapFragment();
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.map_fragment_layout, container, false);
        }

}
