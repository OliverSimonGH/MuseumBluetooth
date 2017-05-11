package com.example.team11.museumaudiotrailsteam11.BeaconScan;

import com.example.team11.museumaudiotrailsteam11.BeaconHistory.BluetoothBeacon;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Oliver on 5/11/2017.
 */
public class BluetoothBeaconTest {
    private List<BluetoothBeacon> list;

    @Before
    public void setup(){
        list = new ArrayList<>();
        list.add(new BluetoothBeacon("24543435435435", "11", "12", "Mona Lisa", "www.google.com"));
        list.add(new BluetoothBeacon("24543438768765", "12", "13", "Rare item", "www.youtube.com"));
        list.add(new BluetoothBeacon("24543435435435", "13", "14", "Non-Rare item", "www.facebook.com"));
    }

    @Test
    public void getItemNameAndGetItemMajorNoAndGetItemMinorNo(){
        assertEquals("24543438768765", list.get(1).getBeaconUUID());
        assertEquals("12", list.get(1).getMajorNo());
        assertEquals("13", list.get(1).getMinorNo());
    }

}