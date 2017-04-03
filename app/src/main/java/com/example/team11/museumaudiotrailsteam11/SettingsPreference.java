package com.example.team11.museumaudiotrailsteam11;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.util.DisplayMetrics;
import android.widget.Toast;
import java.util.Locale;

/**
 * Created by c1633899 on 03/04/2017.
 */
public class SettingsPreference extends PreferenceFragment {

    private boolean notificationsChecked;
    //References
    //https:www.youtube.com/watch?v=0-7YvU9fz8k&t
//    http://stackoverflow.com/questions/26619044/listpreference-onclicklistener-takes-twice-to-execute
//    http://stackoverflow.com/questions/30656088/android-listpreference
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_screen);

        SwitchPreference switchPreference = (SwitchPreference) findPreference("notifications_switch");
        ListPreference listPreference = (ListPreference) findPreference("languages_list");

        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                switch (newValue.toString()) {
                    case "English":
                        changeLanguage("en");
                        Toast.makeText(getActivity().getApplicationContext(), "You have changed language to " + newValue.toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case "Spanish":
                        changeLanguage("es");
                        Toast.makeText(getActivity().getApplicationContext(), "You have changed language to " + newValue.toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case "Welsh":
                        changeLanguage("cy");
                        Toast.makeText(getActivity().getApplicationContext(), "You have changed language to " + newValue.toString(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getActivity().getApplicationContext(), "You have changed language to " + newValue.toString(), Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean switched = ((SwitchPreference) preference).isChecked();

                if (switched){
                    notificationsChecked = true;
                } else {
                    notificationsChecked = false;
                }
                return true;
            }
        });
    }

    private void changeLanguage(String lang){
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(getActivity().getApplicationContext(), Settings.class);
        startActivity(refresh);
        getActivity().finish();
    }

    public boolean isNotificationsChecked() {
        return notificationsChecked;
    }
}