package com.example.team11.museumaudiotrailsteam11.BeaconHistory;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.team11.museumaudiotrailsteam11.BeaconScan.BeaconSearchAdapter;
import com.example.team11.museumaudiotrailsteam11.BeaconScan.BeaconsListScreen;
import com.example.team11.museumaudiotrailsteam11.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c1633899 on 19/03/2017.
 */
public class BeaconHistory extends AppCompatActivity{
    private ListView lv;
    private BeaconHistoryAdapter listAdapter;
    private List<BluetoothBeacon> beacons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_history_list);

        listAdapter = new BeaconHistoryAdapter(this, beacons);
        lv = (ListView) findViewById(R.id.theListView);
        lv.setAdapter(listAdapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder beaconDialog = new AlertDialog.Builder(BeaconHistory.this);
                beaconDialog.setMessage("Are you sure you want to delete this item?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Delete Item From database;
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = beaconDialog.create();
                alert.setTitle("Item Deletion");
                alert.show();
                return true;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                http://stackoverflow.com/questions/12013416/is-there-any-way-in-android-to-force-open-a-link-to-open-in-chrome
//                get item clicked URL
                String url = "";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setPackage("com.android.chrome");
                try {
                    startActivity(i);
                } catch (ActivityNotFoundException e) {
                    i.setPackage(null);
                    startActivity(i);
                }
            }
        });
    }

    public void addBeaconsToList(){

    }
}
