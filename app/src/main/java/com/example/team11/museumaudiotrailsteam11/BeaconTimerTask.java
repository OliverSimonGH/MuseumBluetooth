package com.example.team11.museumaudiotrailsteam11;

import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by c1633899 on 16/03/2017.
 */
public class BeaconTimerTask {

    private BeaconUpdater bu;
    private Timer timer = new Timer();
    private int beaconIntervalSeconds;

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
                    beaconIntervalSeconds++;
                } else {
                    bu.beaconUpdater(true);
                    beaconIntervalSeconds = 0;
                }
            }
        };
        timer.schedule(task, 0, 1000);
    }
}
