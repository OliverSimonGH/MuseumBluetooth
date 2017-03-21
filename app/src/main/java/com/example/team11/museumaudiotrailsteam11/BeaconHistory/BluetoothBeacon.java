package com.example.team11.museumaudiotrailsteam11.BeaconHistory;

/**
 * Created by c1633899 on 20/03/2017.
 */
public class BluetoothBeacon {
    private String beaconUUID;
    private String majorNo;
    private String minorNo;

    public BluetoothBeacon(String beaconUUID, String majorNo, String minorNo) {
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

    public String getMajorNo() {
        return majorNo;
    }

    public void setMajorNo(String majorNo) {
        this.majorNo = majorNo;
    }

    public String getMinorNo() {
        return minorNo;
    }

    public void setMinorNo(String minorNo) {
        this.minorNo = minorNo;
    }
}
