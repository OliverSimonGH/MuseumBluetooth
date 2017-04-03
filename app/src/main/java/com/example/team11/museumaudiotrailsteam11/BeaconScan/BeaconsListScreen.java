package com.example.team11.museumaudiotrailsteam11.BeaconScan;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.team11.museumaudiotrailsteam11.BeaconHistory.BluetoothBeacon;
import com.example.team11.museumaudiotrailsteam11.Database.DBSQLiteHelper;
import com.example.team11.museumaudiotrailsteam11.MainActivity;
import com.example.team11.museumaudiotrailsteam11.R;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconManagerScanEvents;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconRegion;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconScanManager;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCelliBeacon;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class BeaconsListScreen extends AppCompatActivity implements GCellBeaconManagerScanEvents{

    private ListView lv;
    private BeaconSearchAdapter listAdapter;
    private GCellBeaconScanManager mbtManager;
    private List<BluetoothBeacon> beacons = new ArrayList<>();
    private int dID, beaconsOn;
    private Timer timer = new Timer();
    private final int beaconIntervalTimer = 5;
    private DBSQLiteHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_search_adapter_list);
        database = new DBSQLiteHelper(this);
        database.createTables();

        listAdapter = new BeaconSearchAdapter(this, beacons);
        lv = (ListView) findViewById(R.id.theListView);
        lv.setAdapter(listAdapter);

        mbtManager = new GCellBeaconScanManager(this);
        mbtManager.enableBlueToothAutoSwitchOn(true);
        mbtManager.useBeaconRegions(false);
        mbtManager.startScanningForBeacons();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String beaconUUID = listAdapter.getBeaconUUID(position);
                String beaconMajorNo = listAdapter.getBeaconMajorNo(position);
                String beaconMinorNo = listAdapter.getBeaconMinorNo(position);
                String beaconName = listAdapter.getBeaconName(position);
                String beaconURL = listAdapter.getBeaconURL(position);
//
                database.insertHistoryData(beaconUUID, Integer.parseInt(beaconMajorNo), Integer.parseInt(beaconMinorNo), beaconName, beaconURL);

//                http://stackoverflow.com/questions/12013416/is-there-any-way-in-android-to-force-open-a-link-to-open-in-chrome
//                get item clicked URL
                openChrome(beaconURL);
            }
        });

        TimerTask task;
        task = new TimerTask() {
            @Override
            public void run() {
                Log.i(getString(R.string.app_name), "Beacons: " + beaconsOn);
                beaconsOn++;
            }
        };
        timer.schedule(task, 0, 1000);
    }

    @Override
    public void onGCellUpdateBeaconList(List<GCelliBeacon> discoveredBeacon) {
        if(beaconsOn % beaconIntervalTimer == 0) {
            listAdapter.dataSource.clear();
            for (GCelliBeacon beacon : discoveredBeacon){
                addBeaconsToList(beacon);
                listAdapter.notifyDataSetChanged();
            }
            createNotification(getApplicationContext(), true, dID, beacons.size() + " Beacons Have Been Found!");
        }
    }

    private void addBeaconsToList(GCelliBeacon beacon) {
//          https://www.youtube.com/watch?v=nY2bYJyGty8
        Cursor data = database.getExhibit(beacon.getProxUuid().getStringFormattedUuid(), beacon.getMajorNo(), beacon.getMinorNo());
        if (data.getCount() == 0){
            Log.i(getString(R.string.app_name), "Could not find beacon");
        } else {
            while (data.moveToNext()){
                beacons.add(new BluetoothBeacon(data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5)));
            }
        }
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

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        timer.purge();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void didEnterBeaconRegion(GCellBeaconRegion gCellBeaconRegion) {

    }

    @Override
    public void didExitBeaconRegion(GCellBeaconRegion gCellBeaconRegion) {

    }

    @Override
    public void didRangeBeaconsinRegion(GCellBeaconRegion gCellBeaconRegion, List<GCelliBeacon> list) {

    }

    @Override
    public void bleNotSupported() {

    }

    @Override
    public void bleNotEnabled() {

    }

    @Override
    public void locationPermissionsDenied() {

    }


    public static void createNotification(Context ctx, boolean dismiss, int id, String text) {
        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(ctx);
        // You can look at other attributes to set but these three MUST be set in order to build
        notifBuilder.setSmallIcon(R.mipmap.ic_launcher).setContentTitle(ctx.getString(R.string.notification)).setContentText(text);
        //We could pass in whether it was actually dismissable and remove the need for an if statement
        notifBuilder.setOngoing(!dismiss);

        //Create an action for when the intent is clicked (just opening this activity)
        Intent resultIntent = new Intent(ctx, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        ctx,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        notifBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, notifBuilder.build());
    }

    public void addExhibitData(String beaconUUID, int beaconMajorNo, int beaconMinorNo, String beaconName, String beaconURL){
        boolean insertData = database.insertExhibitData(beaconUUID, beaconMajorNo, beaconMinorNo, beaconName, beaconURL);
        if (insertData) Toast.makeText(getApplicationContext(), "Successfully Added Data", Toast.LENGTH_SHORT).show();
        else Toast.makeText(getApplicationContext(), "Error Inserting Data", Toast.LENGTH_SHORT).show();
    }
}
