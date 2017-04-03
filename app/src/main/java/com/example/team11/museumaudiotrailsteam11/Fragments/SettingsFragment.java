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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.team11.museumaudiotrailsteam11.R;

import java.util.Locale;

/**
 * Created by c1630186 on 28/03/2017.
 */
public class SettingsFragment extends Fragment {

    private boolean isNotificationsOn = true;
    private Switch mySwitch;
    private Spinner spin;
    Locale myLocale;
    String languageType;

    public static SettingsFragment newInstance() {   // See HomeFragment for explanation
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment_layout, container, false);

        Spinner spin = (Spinner)view.findViewById(R.id.language_spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (languageType.equals("Spanish")) {
                    Toast.makeText(getActivity().getApplicationContext(), "You have selected Spanish", Toast.LENGTH_SHORT).show();
                    setLocale("es");
                } else if (languageType.equals("Welsh")) {
                    Toast.makeText(getActivity().getApplicationContext(), "You have selected Welsh", Toast.LENGTH_SHORT).show();
                    setLocale("cy");
                } else if (languageType.equals("English")) {
                    Toast.makeText(getActivity().getApplicationContext(), "You have selected Welsh", Toast.LENGTH_SHORT).show();
                    setLocale("en");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity().getApplicationContext(), "You have selected English", Toast.LENGTH_SHORT).show();
                setLocale("values");
            }
        });
        languageType = String.valueOf(spin.getSelectedItem());
        mySwitch = (Switch)view.findViewById(R.id.notificationSwitch);
        mySwitch.setChecked(true);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) isNotificationsOn = true;
                else isNotificationsOn = false;
            }
        });
        return view;
    }
    

    public boolean isNotificationsOn() {
        return isNotificationsOn;
    }
    private void setLocale(String lang){
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(getActivity().getApplicationContext(), SettingsFragment.class);
        startActivity(refresh);
    }
}