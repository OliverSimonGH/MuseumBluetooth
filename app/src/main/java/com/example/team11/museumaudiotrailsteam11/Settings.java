package com.example.team11.museumaudiotrailsteam11;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by c1674052 on 03/04/2017.
 */
public class Settings extends AppCompatActivity {

    private boolean isNotificationsOn = true;
    private Switch mySwitch;
    private Spinner spin;
    Locale myLocale;
    String languageType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_fragment_layout);

        Spinner spin = (Spinner) findViewById(R.id.language_spinner);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (languageType.equals("Spanish")) {
                    Toast.makeText(getApplicationContext(), "You have selected Spanish", Toast.LENGTH_SHORT).show();
                    setLocale("es");
                } else if (languageType.equals("Welsh")) {
                    Toast.makeText(getApplicationContext(), "You have selected Welsh", Toast.LENGTH_SHORT).show();
                    setLocale("cy");
                } else if (languageType.equals("English")) {
                    Toast.makeText(getApplicationContext(), "You have selected Welsh", Toast.LENGTH_SHORT).show();
                    setLocale("en");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "You have selected English", Toast.LENGTH_SHORT).show();
                setLocale("values");
            }

//            public void onItemSelected(){
//
//                if (languageType.equals("Spanish")) {
//                    Toast.makeText(getActivity(), "You have selected Spanish", Toast.LENGTH_SHORT).show();
//                    setLocale("es");
//                } else if (languageType.equals("Welsh")) {
//                    Toast.makeText(getActivity(), "You have selected Welsh", Toast.LENGTH_SHORT).show();
//                    setLocale("cy");
//                } else if (languageType.equals("English")) {
//                    Toast.makeText(getActivity(), "You have selected Welsh", Toast.LENGTH_SHORT).show();
//                    setLocale("en");
//                }
//
//            }
//            public void onNothingSelected(){
//                Toast.makeText(getActivity(), "You have selected English", Toast.LENGTH_SHORT).show();
//                setLocale("values");
//            }
        });
        languageType = String.valueOf(spin.getSelectedItem());


        mySwitch = (Switch) findViewById(R.id.notificationSwitch);
        mySwitch.setChecked(true);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) isNotificationsOn = true;
                else isNotificationsOn = false;
            }
        });

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
        Intent refresh = new Intent(this, Settings.class);
        startActivity(refresh);

    }
    }

