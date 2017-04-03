package com.example.team11.museumaudiotrailsteam11.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import java.util.Locale;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

//import com.example.team11.museumaudiotrailsteam11.LanguageSelect;
import com.example.team11.museumaudiotrailsteam11.R;

import java.util.Locale;

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
        View view = inflater.inflate(R.layout.settings_fragment_layout, container, false);

        return view;

    }

}

