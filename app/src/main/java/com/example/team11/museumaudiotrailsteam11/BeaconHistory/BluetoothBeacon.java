package com.example.team11.museumaudiotrailsteam11.BeaconHistory;

/**
 * Created by c1633899 on 20/03/2017.
 */
public class BluetoothBeacon {
    private String beaconUUID;
    private int majorNo;
    private int minorNo;

    public BluetoothBeacon(String beaconUUID, int majorNo, int minorNo) {
        this.beaconUUID = beaconUUID;
        this.majorNo = majorNo;
        this.minorNo = minorNo;
    }

    public String getBeaconUUID() {
        return beaconUUID;
    }

    public void setBeaconUUID(String beaconUUID) {
        this.beaconUUID = beaconUUID;
    }

    public int getMajorNo() {
        return majorNo;
    }

    public void setMajorNo(int majorNo) {
        this.majorNo = majorNo;
    }

    public int getMinorNo() {
        return minorNo;
    }

    public void setMinorNo(int minorNo) {
        this.minorNo = minorNo;
    }
}
