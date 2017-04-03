package com.example.team11.museumaudiotrailsteam11.BeaconHistory;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team11.museumaudiotrailsteam11.Database.DBSQLiteHelper;
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
    private DBSQLiteHelper database;
    private android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_history_list);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_action_bar);
        Toolbar parent =(Toolbar) actionBar.getCustomView().getParent();
        parent.setContentInsetsAbsolute(0,0);

        TextView actionTitle = (TextView) findViewById(R.id.action_bar_title);
        actionTitle.setText("Exhibit's History");

//        create the database
        database = new DBSQLiteHelper(this);
        database.createTables();

//        add beacons to beacon vector
        addBeaconsToList();

//        Creating Adapter and displaying it
        listAdapter = new BeaconHistoryAdapter(this, beacons);
        lv = (ListView) findViewById(R.id.beaconHistoryListView);
        lv.setAdapter(listAdapter);

//        On item long click, gives option to delete a beacon from history
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                AlertDialog.Builder beaconDialog = new AlertDialog.Builder(BeaconHistory.this);
                beaconDialog.setMessage(R.string.AreYouSure).setCancelable(false)
                        .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Delete Item From database;
                                String beaconUUID = listAdapter.getBeaconUUID(position);
                                String beaconMajorNo = listAdapter.getBeaconMajorNo(position);
                                String beaconMinorNo = listAdapter.getBeaconMinorNo(position);
                                String beaconName = listAdapter.getBeaconName(position);

//                                https://www.youtube.com/watch?v=nY2bYJyGty8
                                Cursor data = database.getHistoryItemID(beaconUUID, beaconMajorNo, beaconMinorNo);
                                int itemID = -1;
                                while (data.moveToNext()){
                                    itemID = data.getInt(0);
                                }
                                if (itemID > -1){
                                    database.removeHistoryValue(itemID);
                                    listAdapter.remove(beaconUUID, beaconMajorNo, beaconMinorNo);
                                    Toast.makeText(getApplicationContext(), "You have deleted " + beaconName , Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.i(getString(R.string.app_name), "Cannot find ID associated with beacon");
                                }
                            }
                        })
                        .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = beaconDialog.create();
                alert.setTitle(getString(R.string.ItemDeletion));
                alert.show();
                return true;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                http://stackoverflow.com/questions/12013416/is-there-any-way-in-android-to-force-open-a-link-to-open-in-chrome
//                get item clicked URL

                String beaconURL = listAdapter.getBeaconURL(position);
                openChrome(beaconURL);
            }
        });
    }

    private void openChrome(String url) {
        try {
            Intent i = new Intent("android.intent.action.MAIN");
            i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
            i.addCategory("android.intent.category.LAUNCHER");
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        catch(ActivityNotFoundException e) {
            // Chrome is not installed
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(i);
        }
    }

    private void addBeaconsToList(){
        Cursor contents = database.getAllHistoryContents();
        if (contents.getCount() == 0){
            Toast.makeText(getApplicationContext(), R.string.NoBeaconsFound, Toast.LENGTH_SHORT).show();
        } else {
            while (contents.moveToNext()){
                beacons.add(new BluetoothBeacon(contents.getString(1), contents.getString(2), contents.getString(3), contents.getString(4), contents.getString(5)));
            }
        }
    }

    public void addHistoryData(String beaconUUID, int beaconMajorNo, int beaconMinorNo, String beaconName, String beaconURL){
        boolean insertData = database.insertHistoryData(beaconUUID, beaconMajorNo, beaconMinorNo, beaconName, beaconURL);
        if (insertData) Toast.makeText(getApplicationContext(), "Successfully Added Data", Toast.LENGTH_SHORT).show();
        else Toast.makeText(getApplicationContext(), "Error Inserting Data", Toast.LENGTH_SHORT).show();
    }
}
