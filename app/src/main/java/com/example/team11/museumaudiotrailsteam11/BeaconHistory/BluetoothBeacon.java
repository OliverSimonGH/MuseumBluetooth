package com.example.team11.museumaudiotrailsteam11.BeaconHistory;

/**
 * Created by c1633899 on 20/03/2017.
 */
public class BluetoothBeacon {
    private String beaconUUID;
    private String majorNo;
    private String minorNo;
    private String name;
    private String url;

    public BluetoothBeacon(String beaconUUID, String majorNo, String minorNo, String name, String url) {
        this.beaconUUID = beaconUUID;
        this.majorNo = majorNo;
        this.minorNo = minorNo;
        this.name = name;
        this.url = url;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
