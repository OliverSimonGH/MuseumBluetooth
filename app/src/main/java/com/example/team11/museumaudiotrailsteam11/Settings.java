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
import android.widget.Button;
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
    private Button btnLang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_fragment_layout);

        final Spinner spin = (Spinner) findViewById(R.id.language_spinner);
        Button btnLang = (Button) findViewById(R.id.lang_button);
        languageType = String.valueOf(spin.getSelectedItem());


        btnLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            });
        }

        });



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

