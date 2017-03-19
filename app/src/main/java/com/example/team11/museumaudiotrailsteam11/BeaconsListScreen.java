package com.example.team11.museumaudiotrailsteam11;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconManagerScanEvents;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconRegion;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconScanManager;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCelliBeacon;


public class BeaconsListScreen extends AppCompatActivity implements GCellBeaconManagerScanEvents{

    private ListView lv;
    private BeaconAdapter listAdapter;
    private GCellBeaconScanManager mbtManager;
    private List<GCelliBeacon> beacons = new ArrayList<>();
    private int dID, beaconsOn;
    private Timer timer = new Timer();
    private final int beaconIntervalTimer = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_adapter_list);

        listAdapter = new BeaconAdapter(this, beacons);
        lv = (ListView) findViewById(R.id.theListView);
        lv.setAdapter(listAdapter);

        mbtManager = new GCellBeaconScanManager(this);
        mbtManager.enableBlueToothAutoSwitchOn(true);
        mbtManager.useBeaconRegions(false);
        mbtManager.startScanningForBeacons();


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
            for (GCelliBeacon beacon : discoveredBeacon){
                    if (!listAdapter.dataSource.equals(beacon)) {
                        beacons.add(beacon);
                    } else  {
                        beacons.remove(beacon);
                    }
                }
            listAdapter.notifyDataSetChanged();
            createNotification(getApplicationContext(), true, dID, beacons.size() + " Beacons Have Been Found!");
        }
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
}
