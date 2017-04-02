package com.example.team11.museumaudiotrailsteam11.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team11.museumaudiotrailsteam11.R;

/**
 * Created by c1630186 on 28/03/2017.
 */
public class SettingsFragment extends Fragment {

    public static SettingsFragment newInstance(){   // See HomeFragment for explanation
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment_layout, container, false);
    }
}
