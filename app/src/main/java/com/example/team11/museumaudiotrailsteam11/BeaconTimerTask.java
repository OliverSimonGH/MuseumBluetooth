package com.example.team11.museumaudiotrailsteam11;

import android.app.Activity;
import android.content.Context;

import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconManagerScanEvents;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconScanManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by c1633899 on 16/03/2017.
 */
public class BeaconTimerTask{

    private BeaconUpdater bu;
    private Timer timer = new Timer();
    private int beaconIntervalSeconds;
    private GCellBeaconScanManager mbtManager;


    public BeaconTimerTask(Context c) {
        bu = (BeaconUpdater) c;
    }

    public void beaconRefreshRate(final int secs) {
        TimerTask task;
        task = new TimerTask() {
            @Override
            public void run() {
                if (beaconIntervalSeconds <= secs) {
                    bu.beaconUpdater(false);
                    System.out.println(beaconIntervalSeconds);
                    beaconIntervalSeconds++;
                } else {
                    bu.beaconUpdater(true);
                    beaconIntervalSeconds = 0;
                    System.out.println("on");
                }
            }
        };
        timer.schedule(task, 0, 1000);
    }
}
