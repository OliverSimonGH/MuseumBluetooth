package com.example.team11.museumaudiotrailsteam11.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.team11.museumaudiotrailsteam11.R;

/**
 * Created by c1630186 on 28/03/2017.
 */
public class SettingsFragment extends Fragment {

    private boolean isNotificationsOn = true;
    private Switch mySwitch;
    private Spinner spin;

    public static SettingsFragment newInstance(){   // See HomeFragment for explanation
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment_layout, container, false);

//        //        adapter.setDropDowlnViewResource(android.R.layout.settings_layout);
//        Spinner spin = (Spinner)view.findViewById(R.id.language_spinner);
//        spin
////        spin.setAdapter(adapter);
//        String selected = spin.getSelectedItem().toString();
//        if (selected.equals("English")){
//        }
//        else if (selected.equals("Welsh")){
//            method();
//        }
//        else if (selected.equals("Spanish")){
//            method();
//        }
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
//    public void setNotificationsOn(boolean notificationsOn) {
//        isNotificationsOn = notificationsOn;
//    }
}
