package com.example.team11.museumaudiotrailsteam11.BeaconScan;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.team11.museumaudiotrailsteam11.MainActivity;
import com.example.team11.museumaudiotrailsteam11.R;
import com.example.team11.museumaudiotrailsteam11.Settings.Settings;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconManagerScanEvents;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconRegion;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconScanManager;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCelliBeacon;


public class BeaconsListScreen extends AppCompatActivity implements GCellBeaconManagerScanEvents{

    private ListView lv;
    private BeaconSearchAdapter listAdapter;
    private GCellBeaconScanManager mbtManager;
    private List<GCelliBeacon> beacons = new ArrayList<>();
    private int dID, beaconsOn;
    private Timer timer = new Timer();
    private final int beaconIntervalTimer = 10;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_search_adapter_list);

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
//                need to add items clicked to the database
//                http://stackoverflow.com/questions/12013416/is-there-any-way-in-android-to-force-open-a-link-to-open-in-chrome
//                get item clicked URL
                String url = "http://www.google.com";
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
            for (GCelliBeacon beacon : discoveredBeacon){
                if (!listAdapter.dataSource.contains(beacon)) {
                    beacons.add(beacon);
                } else  {
                    beacons.remove(beacon);
                }
            }
            listAdapter.notifyDataSetChanged();
            if (settings.isNotificationsOn()){
                createNotification(getApplicationContext(), true, dID, beacons.size() + " Beacons Have Been Found!");
            }

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
