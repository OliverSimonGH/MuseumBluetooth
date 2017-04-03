package com.example.team11.museumaudiotrailsteam11.Settings;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.example.team11.museumaudiotrailsteam11.R;

/**
 * Created by c1674052 on 28/03/2017.
 */
public class Settings extends AppCompatActivity {

    private boolean isNotificationsOn = true;
    private Switch mySwitch;
    private Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

//        adapter.setDropDowlnViewResource(android.R.layout.settings_layout);
        Spinner spin = (Spinner) findViewById(R.id.language_spinner);
        spin
//        spin.setAdapter(adapter);

        String selected = spin.getSelectedItem().toString();
        if (selected.equals("English")){

        }
        else if (selected.equals("Welsh")){
            method();
        }
        else if (selected.equals("Spanish")){
            method();
        }
        mySwitch = (Switch) findViewById(R.id.notificationSwitch);
        mySwitch.setChecked(true);
        mySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
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

    public void setNotificationsOn(boolean notificationsOn) {
        isNotificationsOn = notificationsOn;
    }
}

