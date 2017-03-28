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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_history_list);

//        create the database
        database = new DBSQLiteHelper(this);
        database.dropTables();

        for (int i = 0; i < 5 ; i++) {
            addData(getString(R.string.addData) + i, 1000, 200 + i);
        }
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

//                                https://www.youtube.com/watch?v=nY2bYJyGty8
                                Cursor data = database.getHistoryItemID(beaconUUID, beaconMajorNo, beaconMinorNo);
                                int itemID = -1;
                                while (data.moveToNext()){
                                    itemID = data.getInt(0);
                                }
                                if (itemID > -1){
                                    database.removeHistoryValue(itemID);
                                    listAdapter.remove(beaconUUID, beaconMajorNo, beaconMinorNo);
                                    Toast.makeText(getApplicationContext(), getString(R.string.YouHaveDeleted) + beaconUUID, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), R.string.NoID, Toast.LENGTH_SHORT).show();
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
                String url = getString(R.string.StringURL);
                try {
                    Intent i = new Intent(getString(R.string.AndroidIntentActionMain));
                    i.setComponent(ComponentName.unflattenFromString(getString(R.string.comAndroidChromeMain)));
                    i.addCategory(getString(R.string.AndroidIntentCategoryLauncher));
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                catch(ActivityNotFoundException e) {
                    // Chrome is not installed
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }
            }
        });
    }

    public void addBeaconsToList(){
        Cursor contents = database.getAllHistoryContents();
        if (contents.getCount() == 0){
            Toast.makeText(getApplicationContext(), R.string.NoBeaconsFound, Toast.LENGTH_SHORT).show();
        } else {
            while (contents.moveToNext()){
                beacons.add(new BluetoothBeacon(contents.getString(1), contents.getString(2), contents.getString(3)));
            }
        }
    }

    public void addData(String beaconUUID, int beaconMajorNo, int beaconMinorNo){
        boolean insertData = database.insertHistoryData(beaconUUID, beaconMajorNo, beaconMinorNo);
        if (insertData) Toast.makeText(getApplicationContext(), R.string.SuccessfullyAddedData, Toast.LENGTH_SHORT).show();
        else Toast.makeText(getApplicationContext(), R.string.ErrorInsertingData, Toast.LENGTH_SHORT).show();
    }
}
